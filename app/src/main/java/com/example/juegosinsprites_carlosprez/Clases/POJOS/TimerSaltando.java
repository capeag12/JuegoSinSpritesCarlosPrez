package com.example.juegosinsprites_carlosprez.Clases.POJOS;

import com.example.juegosinsprites_carlosprez.Clases.PantallaJuego;

public class TimerSaltando extends Thread {
    private Jugador jugador;
    private PantallaJuego pantalla;

    public TimerSaltando(PantallaJuego p) {
        this.pantalla = p;
    }

    @Override
    public void run() {

        while (true){
            jugador = pantalla.getServicio().getListaNiveles().get(pantalla.getServicio().getNivelActual()).getJugador();
            if (jugador.getEstado()==Estado.SALTANDO){
                try {
                    SoundPlayer.sonidoSaltar();
                    sleep(400);
                    jugador.setEstado(Estado.CAYENDO);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }


        }




    }
}
