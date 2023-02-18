package com.example.juegosinsprites_carlosprez.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.juegosinsprites_carlosprez.Clases.POJOS.AdapterPuntuaciones;
import com.example.juegosinsprites_carlosprez.Clases.POJOS.Puntuacion;
import com.example.juegosinsprites_carlosprez.Clases.POJOS.PuntuacionesDAO;
import com.example.juegosinsprites_carlosprez.databinding.ActivityClasificacionBinding;

import java.util.ArrayList;

public class ActivityClasificacion extends AppCompatActivity {
    private ActivityClasificacionBinding binding;
    private LinearLayoutManager layoutManager;
    private AdapterPuntuaciones adapterPuntuaciones;
    private PuntuacionesDAO bbdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClasificacionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        bbdd = new PuntuacionesDAO(this);
        bbdd.getWritableDatabase();
        ArrayList<Puntuacion> listaPuntuaciones = bbdd.devolverPuntuaciones();
        layoutManager = new LinearLayoutManager(this);
        adapterPuntuaciones = new AdapterPuntuaciones(listaPuntuaciones,this);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(adapterPuntuaciones);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(ActivityClasificacion.this, MainActivity.class);
        finishAffinity();
        startActivity(i);
    }
}