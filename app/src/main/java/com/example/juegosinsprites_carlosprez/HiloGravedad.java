package com.example.juegosinsprites_carlosprez;

import android.graphics.Rect;

public class HiloGravedad extends Thread{

    private PantallaJuego pantalla;
    private boolean jugando;
    private Jugador jugador;

    public HiloGravedad(PantallaJuego pantalla) {
        jugando = true;
        this.pantalla = pantalla;
    }

    @Override
    public synchronized void run() {
        super.run();
        while(jugando == true){
            jugador = this.pantalla.getServicio().getListaNiveles().get(pantalla.getServicio().getNivelActual()).getJugador();
            if (jugador.getEstado()== Estado.CAYENDO || jugador.getEstado()== Estado.PARADO){
                try {
                    boolean avanzar = true;
                    for (PlataformaRect p: this.pantalla.getServicio().getListaNiveles().get(pantalla.getServicio().getNivelActual()).getListaPlataformas()){
                        if (jugador.getX() >= p.getX() && (jugador.getX()+jugador.getWidth())<=(p.getX()+p.getAnchura())
                                && ((jugador.getY()  + jugador.getHeight())>= p.getY()) && (jugador.getY()<=p.getY()) ){
                            avanzar = false;
                            jugador.setEstado(Estado.PARADO);
                        }
                    }
                    if (avanzar==true){
                        this.jugador.setVelocidadY(5);
                        this.jugador.modificarY();
                        sleep(15);
                        jugador.setEstado(Estado.CAYENDO);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if(jugador.getEstado()==Estado.SALTANDO ){
                System.out.println(jugador.getEstado());
                try {
                    this.jugador.setVelocidadY(-10);
                    this.jugador.modificarY();
                    sleep(15);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if (jugador.getY()>pantalla.getHeight() || (jugador.getX()+jugador.getWidth()) > pantalla.getWidth() || jugador.getX() < 0){
                pantalla.getServicio().eliminarJugador();
            }

        }
    }




}
