package com.example.juegosinsprites_carlosprez;

import android.graphics.Rect;

public class HiloGravedad extends Thread{
    private Jugador jugador;
    private PantallaJuego pantalla;
    private boolean jugando;

    public HiloGravedad(Jugador jugador, PantallaJuego pantalla) {
        this.jugador = jugador;
        jugando = true;
        this.pantalla = pantalla;
    }

    @Override
    public void run() {
        super.run();
        while(jugando == true){
            try {
                boolean avanzar = true;
                for (PlataformaRect p: pantalla.getListaPlataformas()){
                    if (jugador.getX() > p.getX() && (jugador.getX()+jugador.getWidth())<(p.getX()+p.getAnchura())
                    && (jugador.getY()+ jugador.getHeight())>= p.getY() ){
                        avanzar = false;
                    }

                }
                if (avanzar==true){
                    this.jugador.saltar(-5);
                    sleep(15);
                }


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }

    private boolean compararRectangulos(Rect r1, Rect r2){
        return r1.left < r2.left+ r2.width() && r1.left + r1.width() > r2.left && r1.top < r2.top + r2.height() && r1.top + r1.height() > r2.top;

    }


}
