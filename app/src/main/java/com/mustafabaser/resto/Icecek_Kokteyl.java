package com.mustafabaser.resto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class Icecek_Kokteyl extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icecek__kokteyl);
        setTitle(getString(R.string.menu_kokteyl));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Backbutton

        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.ic_cocktail, "Margarita", "İçerisinde ayrıca buz ve cam bir bardağın kenarında meyve dilimi olacak şekilde servis edilir."));
        exampleList.add(new ExampleItem(R.drawable.ic_cocktail, "Martini", "Dry Martini kadehine kürdan batırılmış bir yeşil zeytin veya limon kabuğu ilave edilir."));
        exampleList.add(new ExampleItem(R.drawable.ic_cocktail, "Mojito", "Mojito şeker ve canlandırıcı narenciye ve yeşil nane kombinasyonuyla romun potansiyel sertliğini maskeler."));
        exampleList.add(new ExampleItem(R.drawable.ic_cocktail, "Sangria", "İçinde kırmızı şarap, meyve parçaları, şeker veya bal gibi bir tatlandırıcı ve rom ya da votka gibi kuvvetli bir içki bulunur."));

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
