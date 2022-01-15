package com.mustafabaser.resto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button inviteButton;
    Intent intentShare;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        //service çağırı
        Intent intent = new Intent(this, HelloService.class);
        //startService(intent);
        ContextCompat.startForegroundService(getApplicationContext(), intent);

        inviteButton = (Button) findViewById(R.id.inviteButton);

        inviteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentShare = new Intent(Intent.ACTION_SEND);
                intentShare.setType("text/plain");
                intentShare.putExtra(Intent.EXTRA_TEXT, R.string.MainActivity_Invite_Message);
                startActivity(Intent.createChooser(intentShare, getString(R.string.MainActivity_Invite_Where)));
            }
        });
    }

    public void tavsiye(View view) {
        Intent intent = new Intent(MainActivity.this,Tavsiye.class);
        startActivity(intent);
    }

    public void sefin_spesyali(View view) {
        Intent intent = new Intent(MainActivity.this,Sefin_Spesyali.class);
        startActivity(intent);
    }

    public void hakkimizda(View view) {
        Intent intent = new Intent(MainActivity.this,Hakkimizda.class);
        startActivity(intent);
    }

    public void rezervasyon(View view) {
        Intent intent = new Intent(MainActivity.this, Rezervasyon.class);
        startActivity(intent);
    }

    public void menu(View view) {
        Intent intent = new Intent(MainActivity.this, Menu.class);
        startActivity(intent);
    }
}

