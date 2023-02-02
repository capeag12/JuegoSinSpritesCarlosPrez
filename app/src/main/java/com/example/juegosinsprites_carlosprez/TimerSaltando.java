package com.example.juegosinsprites_carlosprez;

import java.util.Timer;
import java.util.TimerTask;

public class TimerSaltando extends Thread {
    private Jugador jugador;

    public TimerSaltando(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public void run() {
        while (jugador.isJugando() == true){
            if (jugador.getEstado()==Estado.SALTANDO){
                try {
                    sleep(400);
                    jugador.setEstado(Estado.CAYENDO);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }


        }



    }
}
