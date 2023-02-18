package com.example.juegosinsprites_carlosprez.Clases.POJOS;

public class Puntuacion {
    private String nombre;
    private int caidas;

    public Puntuacion(String nombre, int caidas) {
        this.nombre = nombre;
        this.caidas = caidas;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCaidas() {
        return caidas;
    }
}
