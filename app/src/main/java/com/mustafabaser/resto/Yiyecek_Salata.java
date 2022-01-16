package com.mustafabaser.resto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class Yiyecek_Salata extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yiyecek__salata);
        setTitle(getString(R.string.menu_salata));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Backbutton


        ArrayList<ExampleItem> saladMenu = new ArrayList<>();
        saladMenu.add(new ExampleItem(R.drawable.ic_salad, "Mevsim Salata", "Mevsim yeşillikleri, havuç, kırmızı lahana, domates, saltalık, soya filizi, siyah zeytin, baby mısır, beyaz peynir, zeytinyağı, limon sos ile servis edilir."));
        saladMenu.add(new ExampleItem(R.drawable.ic_salad, "Ton Balıklı Salata", "Iceberg, Akdeniz yeşillikleri üzerinde ton balığı, haşlanmış yumurta, kırmızı közlenmiş biber, soğan, domates, salatalık, siyah zeytin ve turşu ile servis edilir."));
        saladMenu.add(new ExampleItem(R.drawable.ic_salad, "Klasik Tavuklu Sezar Salata", "Iceberg, marine edilmiş ızgara tavuk parçaları, kroton ekmek, tane mısır, sezar sos ve parmesan peyniri ile servis edilir."));
        saladMenu.add(new ExampleItem(R.drawable.ic_salad, "Izgara Biftekli Salata", "Dilimlenmiş ve marine edilmiş ızgara biftek, Iceberg ve Akdeniz yeşillikleri üzerine domates, salatalık, turşu, zeytin, havuç ve pesto sos ile servis edilir."));
        saladMenu.add(new ExampleItem(R.drawable.ic_salad, "Tam Buğdaylı Yeşil Salata", "Iceberg, Akdeniz yeşillikleri, havuç, mısır, zeytin, cherry domates, turşu, salatalık, taze otlu haşlanmış buğday, nar ekşisi ve kızarmış peynir topları ile servis edilir."));


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(saladMenu);
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