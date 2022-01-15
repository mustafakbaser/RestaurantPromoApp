package com.mustafabaser.resto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Rezervasyon2 extends AppCompatActivity {

    ImageView imageView;
    EditText editTextAd, editTextTarih;
    Button saveButton, updateButton, deleteButton;
    Bitmap selectedImage;
    String nameBeforeUpdate, dateBeforeUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezervasyon2);
        setTitle(getString(R.string.Rezervasyon_Title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Backbutton

        imageView = findViewById(R.id.imageView);
        editTextTarih = findViewById(R.id.editTextTarih);
        editTextAd = findViewById(R.id.editTextAd);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);
        saveButton = findViewById(R.id.saveButton);

        Intent intent = getIntent();
        String info = intent.getStringExtra("info");

        if (info.matches("new")) {
            Bitmap background = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.image_background);
            imageView.setImageBitmap(background);
            editTextAd.setText("");
            editTextTarih.setText("");
            saveButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.INVISIBLE);
            updateButton.setVisibility(View.INVISIBLE);
        } else {
            String name = intent.getStringExtra("name");
            editTextAd.setText(name);
            nameBeforeUpdate = name;
            String date = intent.getStringExtra("date");
            dateBeforeUpdate = date;
            editTextTarih.setText(date);
            int position = intent.getIntExtra("position", 0);
            imageView.setImageBitmap(Rezervasyon.reservationImageList.get(position));
            saveButton.setVisibility(View.INVISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            updateButton.setVisibility(View.VISIBLE);

        }
    }

    public void updateRecord(View view) {
        String reservationName = editTextAd.getText().toString();
        String reservationDate = editTextTarih.getText().toString();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
        byte[] bytes = outputStream.toByteArray();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ContentProvider.NAME, reservationName);
        contentValues.put(ContentProvider.DATE, reservationDate);
        contentValues.put(ContentProvider.IMAGE, bytes);
        String[] selectionArguments = {nameBeforeUpdate};
        getContentResolver().update(ContentProvider.CONTENT_URI, contentValues, "name=?", selectionArguments);
        Intent intent = new Intent(getApplicationContext(),Rezervasyon.class);
        startActivity(intent);
    }

    public void deleteRecord(View view) {
        String recordName = editTextAd.getText().toString();
        String[] selectedArguments = {recordName};
        getContentResolver().delete(ContentProvider.CONTENT_URI, "name=?", selectedArguments);
        Intent intent = new Intent(getApplicationContext(), Rezervasyon.class);
        startActivity(intent);

    }

    public void saveRecord(View view) {
        String reservationName = editTextAd.getText().toString();
        String reservationDate = editTextTarih.getText().toString();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.PNG, 50, outputStream); // fotoğrafı sıkıştırma işlemi
        byte[] bytes = outputStream.toByteArray();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ContentProvider.NAME, reservationName);
        contentValues.put(ContentProvider.DATE, reservationDate);
        contentValues.put(ContentProvider.IMAGE, bytes);

        getContentResolver().insert(ContentProvider.CONTENT_URI, contentValues);
        Intent intent = new Intent(getApplicationContext(), Rezervasyon.class);
        startActivity(intent);
    }

    public void selectImage(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 2);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Uri image = data.getData();
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), image);
                imageView.setImageBitmap(selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //back button
        startActivity(new Intent(this, Rezervasyon.class));
        Toast.makeText(this, R.string.Rezervasyon_Cancel, Toast.LENGTH_SHORT).show();
        finish();
        return super.onOptionsItemSelected(item);
    }
}
