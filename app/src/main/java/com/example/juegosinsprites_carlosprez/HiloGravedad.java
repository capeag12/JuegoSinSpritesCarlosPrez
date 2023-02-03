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
            if (jugador.getEstado()== Estado.CAYENDO){
                try {

                    boolean avanzar = true;
                    for (PlataformaRect p: pantalla.getListaPlataformas()){
                        if (jugador.getX() > p.getX() && (jugador.getX()+jugador.getWidth())<(p.getX()+p.getAnchura())
                                && (jugador.getY()+ jugador.getHeight())>= p.getY() ){
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
            else if(jugador.getEstado()==Estado.SALTANDO){
                try {
                    this.jugador.setVelocidadY(-10);
                    this.jugador.modificarY();

                    sleep(15);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }


        }

    }

    private boolean compararRectangulos(Rect r1, Rect r2){
        return r1.left < r2.left+ r2.width() && r1.left + r1.width() > r2.left && r1.top < r2.top + r2.height() && r1.top + r1.height() > r2.top;

    }


}
