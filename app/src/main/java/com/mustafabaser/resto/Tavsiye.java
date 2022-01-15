package com.mustafabaser.resto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class Tavsiye extends AppCompatActivity {
    CoordinatorLayout tavsiyeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tavsiye);
        tavsiyeLayout = findViewById(R.id.bilgi);
        setTitle(getResources().getText(R.string.tavsiye_baslik));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Backbutton
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //back button
        startActivity(new Intent(this, MainActivity.class));
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void description(View v) {

        Snackbar.make(tavsiyeLayout, R.string.tavsiye_snackbar, Snackbar.LENGTH_LONG)
                .setAction(R.string.Tavsiye_Close, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                })
                .setActionTextColor(getResources().getColor(R.color.colorAccent))
                .show();
    }
}
