package com.example.juegosinsprites_carlosprez.Clases;

public class HiloMovHor extends Thread{
    private PantallaJuego pantallaJuego;
    private Jugador jugador;
    private boolean continuar = true;

    public void finalizar(){
        this.continuar = false;
    }

    public HiloMovHor(PantallaJuego pantallaJuego) {
        this.pantallaJuego = pantallaJuego;
    }

    @Override
    public synchronized void run() {
        while (continuar){
            try {
                this.jugador = pantallaJuego.getServicio().getListaNiveles().get(pantallaJuego.getServicio().getNivelActual()).getJugador();
                this.jugador.moverPersonaje();
                sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
