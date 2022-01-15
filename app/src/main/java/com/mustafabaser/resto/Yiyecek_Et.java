package com.mustafabaser.resto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class Yiyecek_Et extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yiyecek__et);
        setTitle(getString(R.string.menu_et));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Backbutton

        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.ic_cook, "Fırın Antrikot", "Etlerin üzerine 2-3 adet defne yaprağı koyulur ve önceden ısıtılmış 200° fırında, 35-40 dakika pişirilir."));
        exampleList.add(new ExampleItem(R.drawable.ic_cook, "Hünkar Beğendi", "Patates püresi, kuşkonmaz ve pancar turşusu ile dekore edilir"));
        exampleList.add(new ExampleItem(R.drawable.ic_cook, "Macar Gulaş", "Buğulanmış pastırma köfte veya haşlanmış kalın hamur parçalarıyla servis yapılır."));
        exampleList.add(new ExampleItem(R.drawable.ic_cook, "Dana Madalyon", "Patates püresi, kuşkonmaz ve pancar turşusu ile dekore edilir"));
        exampleList.add(new ExampleItem(R.drawable.ic_cook, "Dana Külbastı", "Etlerin üzerine 2-3 adet defne yaprağı koyulur ve önceden ısıtılmış 200° fırında, 35-40 dakika pişirilir."));
        exampleList.add(new ExampleItem(R.drawable.ic_cook, "Biberli Biftek", "Buğulanmış pastırma köfte veya haşlanmış kalın hamur parçalarıyla servis yapılır."));
        exampleList.add(new ExampleItem(R.drawable.ic_cook, "Mantar Soslu Bonfile", "Etlerin üzerine 2-3 adet defne yaprağı koyulur ve önceden ısıtılmış 200° fırında, 35-40 dakika pişirilir."));


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