package com.example.juegosinsprites_carlosprez;

public class HiloProyectil extends Thread{
    private Proyectil proyectil;
    private PantallaJuego pantallaJuego;
    private Nivel nivelActual;

    public boolean continuar = true;

    public HiloProyectil(Proyectil proyectil, PantallaJuego pantallaJuego) {
        this.proyectil = proyectil;
        this.pantallaJuego = pantallaJuego;
    }

    @Override
    public void run() {
        super.run();


        while(continuar==true){
            nivelActual = pantallaJuego.getServicio().getListaNiveles().get(pantallaJuego.getServicio().getNivelActual());
            try {
                proyectil.moverFigura();

                if (proyectil.estaDentro(nivelActual.getJugador().getX(), nivelActual.getJugador().getY())){
                    pantallaJuego.getServicio().eliminarJugador();
                    continuar=false;
                    this.proyectil.moverFigura(-500,-500);

                }

                if (this.proyectil.getX()>this.pantallaJuego.getWidth() ||this.proyectil.getX()<0 ||this.proyectil.getY()>this.pantallaJuego.getHeight() || this.proyectil.getY()<0){
                    continuar = false;

                }

                sleep(25);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
        if (nivelActual.getListaProyectiles()!=null){
            synchronized (nivelActual.getListaProyectiles()){
                nivelActual.getListaProyectiles().remove(proyectil);
            }
        }


    }
}
