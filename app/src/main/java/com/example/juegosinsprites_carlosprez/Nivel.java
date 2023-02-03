package com.example.juegosinsprites_carlosprez;

import java.util.ArrayList;

public class Nivel {
    private Jugador jugador;
    private ArrayList<PlataformaRect> listaPlataformas;
    private Trigger trigger;
    private ArrayList<Trampa> listaTrampas;
    private ArrayList<Proyectil> listaProyectiles;
    private float xInicial, yInicial;

    public Nivel(ArrayList<PlataformaRect> listaPlataformas, Trigger trigger, ArrayList<Trampa> listaTrampas, float xInicial, float yInicial) {

        this.listaPlataformas = listaPlataformas;
        this.trigger = trigger;
        this.listaTrampas = listaTrampas;
        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.listaProyectiles = new ArrayList<>();
        this.jugador = new Jugador(this.xInicial, this.yInicial, 20,30);
    }

    public Jugador getJugador() {
        return jugador;
    }

    public ArrayList<PlataformaRect> getListaPlataformas() {
        return listaPlataformas;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public ArrayList<Trampa> getListaTrampas() {
        return listaTrampas;
    }

    public float getxInicial() {
        return xInicial;
    }

    public float getyInicial() {
        return yInicial;
    }

    public ArrayList<Proyectil> getListaProyectiles() {
        return listaProyectiles;
    }
}
