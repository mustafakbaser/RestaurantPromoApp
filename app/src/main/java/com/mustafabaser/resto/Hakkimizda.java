package com.mustafabaser.resto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.mustafabaser.resto.fragments.AdresFragment;
import com.mustafabaser.resto.fragments.HakkimizdaBilgiFragment;
import com.mustafabaser.resto.fragments.HakkimizdaBilgiFragment2;

public class Hakkimizda extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hakkimizda);

        setTitle(getString(R.string.hakkimizda));

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.menu:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);

            case R.id.anasayfa:
                startActivity(new Intent(this, MainActivity.class));
                break;

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.share_button:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody=getString(R.string.Hakkimizda_Share_Body);
                String shareSubject =getString(R.string.Hakkimizda_Share_Subject);

                sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);

                startActivity(Intent.createChooser(sharingIntent, getString(R.string.Hakkimizda_Share_With)));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void adresGoster(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        AdresFragment adresFragment = new AdresFragment();
        fragmentTransaction.replace(R.id.frame_layout, adresFragment).commit();

    }

    public void anasayfa(MenuItem item) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void bilgiGoster(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HakkimizdaBilgiFragment hakkimizdaBilgiFragment = new HakkimizdaBilgiFragment();
        fragmentTransaction.replace(R.id.frame_layout,hakkimizdaBilgiFragment).commit();
    }

    public void devaminiGoster(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HakkimizdaBilgiFragment2 hakkimizdaBilgiFragment2 = new HakkimizdaBilgiFragment2();
        fragmentTransaction.replace(R.id.frame_layout, hakkimizdaBilgiFragment2).commit();
    }
}
