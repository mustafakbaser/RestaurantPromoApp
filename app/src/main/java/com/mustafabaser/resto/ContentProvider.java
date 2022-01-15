package com.mustafabaser.resto;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Objects;

public class ContentProvider extends android.content.ContentProvider {

    static final String PROVIDER_NAME = "com.mertyanik.resto.ContentProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/reservation";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String NAME = "name";
    static final String DATE = "date";
    static final String IMAGE = "image";

    static final int RESERVATION = 1;
    static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME,"reservation", RESERVATION);
    }



    //----------------------- DB -----------------------

    private SQLiteDatabase sqLiteDatabase;
    static final String DATABASE_NAME = "Reservation";
    static final String TABLE_NAME = "reservation";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DATABASE_TABLE = "CREATE TABLE " + TABLE_NAME +
            "(name TEXT NOT NULL, " +
            "date TEXT NOT NULL, " +
            "image BLOB);";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION );
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DATABASE_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    private static HashMap<String, String> RESERVATION_PROJECTION_MAP;

    //----------------------- DB -----------------------

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper databaseHelper = new DatabaseHelper(context);

        sqLiteDatabase = databaseHelper.getWritableDatabase();

        return sqLiteDatabase != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(TABLE_NAME);

        switch (uriMatcher.match(uri)){
            case RESERVATION:
                sqLiteQueryBuilder.setProjectionMap(RESERVATION_PROJECTION_MAP);
                break;

            default:
                //
        }

        if(sortOrder == null || sortOrder.matches("")){
            sortOrder = NAME;
        }

        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase,projection,selection,selectionArgs,null,null,sortOrder); // Cursor

        cursor.setNotificationUri(Objects.requireNonNull(getContext()).getContentResolver(),uri); //bir şey değişirse querymizde bunu izleyebilecek
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long rowID = sqLiteDatabase.insert(TABLE_NAME,"",values);
        if(rowID > 0){
            Uri newUri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(newUri,null);
            return newUri;
        }
        throw new SQLException("Database Error!");
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowCount =0;

        switch (uriMatcher.match(uri)){
            case RESERVATION:
                //delete
                rowCount = sqLiteDatabase.delete(TABLE_NAME,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Failed");
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return rowCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowCount = 0;
        switch (uriMatcher.match(uri)){
            case RESERVATION:
                rowCount = sqLiteDatabase.update(TABLE_NAME,values,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Failed");
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return rowCount;
    }
}
