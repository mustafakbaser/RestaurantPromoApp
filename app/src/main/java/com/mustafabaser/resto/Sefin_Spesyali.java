package com.mustafabaser.resto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.mustafabaser.resto.fragments.Page1;
import com.mustafabaser.resto.fragments.Page2;
import com.mustafabaser.resto.fragments.Page3;
import com.mustafabaser.resto.fragments.Slider_Pager_Adapter;

import java.util.ArrayList;
import java.util.List;

public class Sefin_Spesyali extends AppCompatActivity {
    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sefin__spesyali);
        setTitle(getString(R.string.chefs_special));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Backbutton

        List<Fragment> list = new ArrayList<>();
        list.add(new Page1());
        list.add(new Page2());
        list.add(new Page3());

        pager = findViewById(R.id.pager);
        pagerAdapter = new Slider_Pager_Adapter(getSupportFragmentManager(),list);
        pager.setAdapter(pagerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //back button
        startActivity(new Intent(this, MainActivity.class));
        finish();
        return super.onOptionsItemSelected(item);
    }
}
