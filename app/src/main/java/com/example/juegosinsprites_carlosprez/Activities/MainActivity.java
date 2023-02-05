package com.example.juegosinsprites_carlosprez.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.juegosinsprites_carlosprez.Clases.ServicioJuego;
import com.example.juegosinsprites_carlosprez.R;
import com.example.juegosinsprites_carlosprez.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private MediaPlayer cancionMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        cancionMenu = MediaPlayer.create(getApplicationContext(), R.raw.mainmenu);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityJuegp.class);
                finishAffinity();
                startActivity(i);
                cancionMenu.stop();
                cancionMenu.release();
                ServicioJuego.getInstance().reiniciarJuego();

            }
        });
        binding.btnMarcadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityClasificacion.class);
                finishAffinity();
                startActivity(i);
                cancionMenu.stop();
                cancionMenu.release();

            }
        });

        cancionMenu.start();

    }


}