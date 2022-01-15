package com.mustafabaser.resto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.mustafabaser.resto.fragments.SefinSpesyaliTabbed;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("Menu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Backbutton
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //back button
        startActivity(new Intent(this, MainActivity.class));
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void sefin_spesyali_tabbed (View view) {
        Intent intent = new Intent(Menu.this, SefinSpesyaliTabbed.class);
        startActivity(intent);
    }

    public void yiyecek_et(View view) {
        Intent intent = new Intent(Menu.this, Yiyecek_Et.class);
        startActivity(intent);
    }

    public void yiyecek_balik(View view) {
        Intent intent = new Intent(Menu.this, Yiyecek_Balik.class);
        startActivity(intent);
    }

    public void yiyecek_salata(View view) {
        Intent intent = new Intent(Menu.this, Yiyecek_Salata.class);
        startActivity(intent);
    }

    public void icecek_kokteyl(View view) {
        Intent intent = new Intent(Menu.this, Icecek_Kokteyl.class);
        startActivity(intent);
    }

    public void icecek_bira(View view) {
        Intent intent = new Intent(Menu.this, Icecek_Bira.class);
        startActivity(intent);
    }

    public void icecek_sarap(View view) {
        Intent intent = new Intent(Menu.this, Icecek_Sarap.class);
        startActivity(intent);
    }
}
