package com.example.juegosinsprites_carlosprez;

public class HiloProyectil extends Thread{
    private Proyectil proyectil;
    private PantallaJuego pantallaJuego;

    public HiloProyectil(Proyectil proyectil, PantallaJuego pantallaJuego) {
        this.proyectil = proyectil;
        this.pantallaJuego = pantallaJuego;
    }

    @Override
    public void run() {
        super.run();
        boolean continuar = true;

        while(continuar==true){

            try {
                proyectil.moverFigura();

                if (proyectil.estaDentro(pantallaJuego.getListaNiveles().get(pantallaJuego.getNivelActual()).getJugador().getX(), pantallaJuego.getListaNiveles().get(pantallaJuego.getNivelActual()).getJugador().getY())){
                    pantallaJuego.getListaNiveles().get(pantallaJuego.getNivelActual()).getJugador().mover(this.pantallaJuego.getxInicial(), this.pantallaJuego.getyInicial());
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
    }
}
