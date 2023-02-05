package com.example.juegosinsprites_carlosprez.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.juegosinsprites_carlosprez.Clases.HiloJuego;
import com.example.juegosinsprites_carlosprez.Clases.PantallaJuego;

public class ActivityJuegp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PantallaJuego(this));
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onBackPressed() {
        HiloJuego.finalizar();
        Intent intent = new Intent(ActivityJuegp.this, MainActivity.class);
        finishAffinity();
        startActivity(intent);

    }
}