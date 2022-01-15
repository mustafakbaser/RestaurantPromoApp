package com.mustafabaser.resto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Rezervasyon extends AppCompatActivity {

    ListView listView;
    static ArrayList<Bitmap> reservationImageList;
    static ArrayList<String> reservationNameList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_profile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_profile) {
            Intent intent = new Intent(getApplicationContext(), Rezervasyon2.class);
            intent.putExtra("info","new"); // yeni bir kayda mı yoksa eski kaydı görüntülemeye mi
            startActivity(intent);
        }else {
            startActivity(new Intent(this, MainActivity.class));
            finish();

        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezervasyon);

        setTitle(getString(R.string.Rezervasyon_Title));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Backbutton

        listView = findViewById(R.id.listView);
        reservationNameList = new ArrayList<String>();
        final ArrayList<String> reservationDateList = new ArrayList<String>();
        reservationImageList = new ArrayList<Bitmap>();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, reservationNameList);
        listView.setAdapter(arrayAdapter);

        String Url = "content://com.mertyanik.resto.ContentProvider";
        Uri reservationUri = Uri.parse(Url);

        ContentResolver contentResolver = getContentResolver();

        @SuppressLint("Recycle") Cursor cursor = contentResolver.query(reservationUri, null, null, null, "name");

        if (cursor != null) {
            while (cursor.moveToNext()) {
                reservationNameList.add(cursor.getString(cursor.getColumnIndex(ContentProvider.NAME)));
                reservationDateList.add(cursor.getString(cursor.getColumnIndex(ContentProvider.DATE)));
                byte[] bytes = cursor.getBlob(cursor.getColumnIndex(ContentProvider.IMAGE));
                Bitmap image = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                reservationImageList.add(image);
                arrayAdapter.notifyDataSetChanged();
            }
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),Rezervasyon2.class);
                intent.putExtra("info", "old"); // yeni bir kayda mı yoksa eski kaydı görüntülemeye mi
                intent.putExtra("name", reservationNameList.get(position));
                intent.putExtra("date", reservationDateList.get(position));
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

    }
}