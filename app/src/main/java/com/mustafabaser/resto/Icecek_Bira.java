package com.mustafabaser.resto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class Icecek_Bira extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icecek__bira);
        setTitle(getString(R.string.menu_bira));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Backbutton


        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.ic_beer, "Lager", "Daha düşük sıcaklıklarda (7-16 °C) ve hazırlanan şıranın alt kısmında aktif olan bir maya çeşididir."));
        exampleList.add(new ExampleItem(R.drawable.ic_beer, "Ale", "Nispeten daha yüksek sıcaklıklarda (15-25 °C) ve hazırlanan şıranın üst kısmında aktif olan bir maya çeşididir. "));
        exampleList.add(new ExampleItem(R.drawable.ic_beer, "Lambic ve Funky", "Funky mayalanma türü, lambic olarak başlayan fermantasyona diğer mayaların (ale veya lager) eklenmesi ile üretilmektedir. "));
        exampleList.add(new ExampleItem(R.drawable.ic_beer, "Pale Malt", "İngiliz Pale Ale ve Indian Pale Ale (IPA) tipi biralarda kullanılır. Kısa süreli, nispeten düşük sıcaklıklarda fırınlanmış malttır."));
        exampleList.add(new ExampleItem(R.drawable.ic_beer, "Amber Malt", "Pale Malt'ın biraz daha kavrulmuşudur. Acı bir aroma verir. Brown Porter tipi biraların hammaddesidir."));
        exampleList.add(new ExampleItem(R.drawable.ic_beer, "Black Malt", "Arpa maltının 200 °C'de fırınlanması ile (yakılarak) elde edilir. Küllü tadı ile biralara renk ve aroma katar."));

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
