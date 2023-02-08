package com.example.juegosinsprites_carlosprez.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.juegosinsprites_carlosprez.Clases.Puntuacion;
import com.example.juegosinsprites_carlosprez.Clases.PuntuacionesDAO;
import com.example.juegosinsprites_carlosprez.Clases.ServicioJuego;
import com.example.juegosinsprites_carlosprez.databinding.ActivityRegistrarResultadoBinding;

public class ActivityRegistrarResultado extends AppCompatActivity {
    private ActivityRegistrarResultadoBinding binding;

    private ServicioJuego s;
    private PuntuacionesDAO bbdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrarResultadoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        s = ServicioJuego.getInstance();
        binding.txtNumEliminaciones.setText("Te han eliminado "+s.getNumCaidas()+" veces");
        bbdd = new PuntuacionesDAO(this);
        binding.btnAddScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.editTextTextPersonName.getText().toString();
                Puntuacion p = new Puntuacion(nombre,s.getNumCaidas()-1);
                bbdd.insertarPuntuación(p);

                Intent i = new Intent(ActivityRegistrarResultado.this, ActivityClasificacion.class);
                finishAffinity();
                startActivity(i);

            }
        });

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityRegistrarResultado.this, ActivityClasificacion.class);
                finishAffinity();
                startActivity(i);
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ActivityRegistrarResultado.this, MainActivity.class);
        finishAffinity();
        startActivity(intent);

    }
}