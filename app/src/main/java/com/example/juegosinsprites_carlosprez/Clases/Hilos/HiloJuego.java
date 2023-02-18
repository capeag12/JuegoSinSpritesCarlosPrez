package com.example.juegosinsprites_carlosprez.Clases.Hilos;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.example.juegosinsprites_carlosprez.Clases.POJOS.PantallaJuego;

public class HiloJuego extends Thread{
    private SurfaceHolder holder;
    private PantallaJuego pantallaJuego;
    public static boolean run;


    public HiloJuego(PantallaJuego pantalla) {
        this.pantallaJuego = pantalla;
        this.holder = this.pantallaJuego.getHolder();
        run = false;
    }

    public static void finalizar(){
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
