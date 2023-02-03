package com.example.juegosinsprites_carlosprez;

public class HiloTrampa extends Thread{
    private Trampa trampa;
    private PantallaJuego pantalla;

    public HiloTrampa(Trampa trampa, PantallaJuego pantalla) {
        this.trampa = trampa;
        this.pantalla = pantalla;
    }

    @Override
    public void run() {
        super.run();

        while (true){
            try {
                Proyectil p = trampa.disparar();
                pantalla.getListaNiveles().get(pantalla.getNivelActual()).getListaProyectiles().add(p);
                HiloProyectil hiloP = new HiloProyectil(p,pantalla);
                hiloP.start();
                sleep(1500);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
