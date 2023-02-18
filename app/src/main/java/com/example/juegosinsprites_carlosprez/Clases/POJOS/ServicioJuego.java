package com.example.juegosinsprites_carlosprez.Clases.POJOS;

import java.util.ArrayList;

public class ServicioJuego {
    private ArrayList<Nivel> listaNiveles;
    private int numCaidas;
    private int nivelActual;

    private static ServicioJuego servicio;

    private ServicioJuego() {
        this.listaNiveles = new ArrayList<>();
        ArrayList<Trampa> listaTrampa = new ArrayList<>();
        listaTrampa.add(new Trampa(1039,777,80,80,Direccion.Arriba));
        listaTrampa.add(new Trampa(900,777,80,80,Direccion.Arriba));
        ArrayList<PlataformaRect> listaPlataforma = new ArrayList<>();
        listaPlataforma.add(new PlataformaRect(100,400, 700,50));
        listaPlataforma.add(new PlataformaRect(1195,400, 700,50));
        Trigger trigger = new Trigger(1788,300, 100,100);
        listaNiveles.add(new Nivel(listaPlataforma,trigger,listaTrampa,285,378));

        listaTrampa = new ArrayList<>();
        listaPlataforma = new ArrayList<>();
        listaPlataforma.add(new PlataformaRect(552,750, 350,50));
        listaPlataforma.add(new PlataformaRect(945,533, 350,50));
        listaPlataforma.add(new PlataformaRect(610,375, 350,50));
        listaPlataforma.add(new PlataformaRect(1200,375, 350,50));
        listaTrampa.add(new Trampa(1355,662,80,80,Direccion.Izquierda));
        listaTrampa.add(new Trampa(135,300,80,80,Direccion.Derecha));
        trigger = new Trigger(1788,300, 100,100);
        listaNiveles.add(new Nivel(listaPlataforma,trigger,listaTrampa,669,700));
        this.numCaidas = 0;
        this.nivelActual = 0;
    }

    public boolean pasarNivel(){

        if ((this.nivelActual+1)==listaNiveles.size()){
            return false;
        }

        this.nivelActual++;
        return true;

    }

    public void reiniciarJuego(){
        servicio = new ServicioJuego();
    }

    private void devolverJugadorAInicio(){
        this.listaNiveles.get(this.nivelActual).getJugador().setX(this.listaNiveles.get(this.nivelActual).getxInicial());
        this.listaNiveles.get(this.nivelActual).getJugador().setY(this.listaNiveles.get(this.nivelActual).getyInicial());

    }

    public void eliminarJugador(){
        numCaidas++;
        devolverJugadorAInicio();
    }

    public ArrayList<Nivel> getListaNiveles() {
        return listaNiveles;
    }

    public int getNumCaidas() {
        return numCaidas;
    }

    public int getNivelActual() {
        return nivelActual;
    }

    public static ServicioJuego getInstance(){
        if (servicio==null){
            servicio = new ServicioJuego();
        }
        return servicio;
    }

}
