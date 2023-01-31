package com.example.juegosinsprites_carlosprez;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class HiloJuego extends Thread{
    private SurfaceHolder holder;
    private PantallaJuego pantallaJuego;
    private boolean run;


    public HiloJuego(PantallaJuego pantalla) {
        this.pantallaJuego = pantalla;
        this.holder = this.pantallaJuego.getHolder();
        run = false;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    @Override
    public void run() {
        System.out.println("Pintado");
        super.run();
        while (run==true){
            Canvas canvas = null;

            try{
                canvas = holder.lockCanvas();
                if (canvas!=null){
                    synchronized (holder){

                        pantallaJuego.getJugador().dibujarPersonaje(canvas);
                        for (Plataforma platform: pantallaJuego.getListaPlataformas()) {
                            platform.dibujarPlataforma(canvas);
                        }
                        pantallaJuego.getJoystick().dibujarControl(canvas);
                        pantallaJuego.getBtnSalta().dibujarControl(canvas);

                    }
                }
            } finally {
                if (canvas!=null){
                    holder.unlockCanvasAndPost(canvas);
                }
            }

            canvas = holder.lockCanvas();
            pantallaJuego.postInvalidate();
            holder.unlockCanvasAndPost(canvas);
        }
    }
}
