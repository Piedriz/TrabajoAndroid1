package com.example.juegoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.jugar);
        play.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jugar:
                Intent i = new Intent(getApplicationContext(),GameActivity.class);
                startActivity(i);
        }
    }
}