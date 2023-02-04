package com.example.juegosinsprites_carlosprez;

public class HiloTrampa extends Thread{
    private Trampa trampa;
    private PantallaJuego pantalla;
    private Nivel nivelActual;
    public boolean continuar = true;

    public HiloTrampa(Trampa trampa, PantallaJuego pantalla) {
        this.trampa = trampa;
        this.pantalla = pantalla;
    }

    @Override
    public void run() {
        super.run();

        while (continuar==true){
            nivelActual = pantalla.getServicio().getListaNiveles().get(pantalla.getServicio().getNivelActual());
            try {
                Proyectil p = trampa.disparar();
                synchronized (nivelActual.getListaProyectiles()){
                    nivelActual.getListaProyectiles().add(p);
                }



                HiloProyectil hiloP = new HiloProyectil(p,pantalla);
                synchronized (pantalla.getHiloProyectiles()){
                    pantalla.getHiloProyectiles().add(hiloP);
                }

                hiloP.start();
                sleep(1500);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (nivelActual.getListaTrampas()!=null){
            synchronized (nivelActual.getListaTrampas()){
                nivelActual.getListaTrampas().remove(trampa);
            }
        }



    }
}
