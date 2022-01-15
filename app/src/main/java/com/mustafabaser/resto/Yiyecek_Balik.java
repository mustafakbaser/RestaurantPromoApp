package com.mustafabaser.resto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class Yiyecek_Balik extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yiyecek__balik);
        setTitle(getString(R.string.menu_balik));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Backbutton



        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.ic_fish, "Lüfer", "Deniz börülcesi ile servis edilir."));
        exampleList.add(new ExampleItem(R.drawable.ic_fish, "Tekir", "Deniz börülcesi ile servis edilir."));
        exampleList.add(new ExampleItem(R.drawable.ic_fish, "Karagöz", "Deniz börülcesi ile servis edilir."));
        exampleList.add(new ExampleItem(R.drawable.ic_fish, "Palamut", "Deniz börülcesi ile servis edilir."));
        exampleList.add(new ExampleItem(R.drawable.ic_shrimp, "Jumbo Karides", "Deniz börülcesi ile servis edilir."));
        exampleList.add(new ExampleItem(R.drawable.ic_shrimp, "Istakoz", "Deniz börülcesi ile servis edilir."));


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