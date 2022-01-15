package com.mustafabaser.resto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class Icecek_Sarap extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icecek__sarap);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Backbutton
        setTitle(getString(R.string.menu_sarap));


        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.ic_wine_bottle, "Kırmızı Şarap", "Özel peynir tabağı ile servis edilir."));
        exampleList.add(new ExampleItem(R.drawable.ic_wine_bottle, "Roze Şarap", "Özel peynir tabağı ile servis edilir."));
        exampleList.add(new ExampleItem(R.drawable.ic_wine_bottle, "Beyaz Şarap", "Özel peynir tabağı ile servis edilir."));
        exampleList.add(new ExampleItem(R.drawable.ic_wine_bottle, "Sparkling Şarap", "Özel peynir tabağı ile servis edilir."));
        exampleList.add(new ExampleItem(R.drawable.ic_wine_bottle, "Dessert Şarap", "Özel peynir tabağı ile servis edilir."));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //back button
        startActivity(new Intent(this, Menu.class));
        finish();
        return super.onOptionsItemSelected(item);
    }
}