package com.example.juegosinsprites_carlosprez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PantallaJuego(this));
        getSupportActionBar().hide();
    }
}