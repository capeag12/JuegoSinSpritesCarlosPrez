package com.example.juegosinsprites_carlosprez.Clases;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.juegosinsprites_carlosprez.Activities.ActivityRegistrarResultado;
import com.example.juegosinsprites_carlosprez.Clases.Controles.Boton;
import com.example.juegosinsprites_carlosprez.Clases.Controles.Joystick;
import com.example.juegosinsprites_carlosprez.Clases.Hilos.HiloGravedad;
import com.example.juegosinsprites_carlosprez.Clases.Hilos.HiloJuego;
import com.example.juegosinsprites_carlosprez.Clases.Hilos.HiloMovHor;
import com.example.juegosinsprites_carlosprez.Clases.Hilos.HiloProyectil;
import com.example.juegosinsprites_carlosprez.Clases.Hilos.HiloTrampa;
import com.example.juegosinsprites_carlosprez.Clases.POJOS.Plataforma;
import com.example.juegosinsprites_carlosprez.Clases.POJOS.Proyectil;
import com.example.juegosinsprites_carlosprez.Clases.POJOS.ServicioJuego;
import com.example.juegosinsprites_carlosprez.Clases.POJOS.SoundPlayer;
import com.example.juegosinsprites_carlosprez.Clases.POJOS.TimerSaltando;
import com.example.juegosinsprites_carlosprez.Clases.POJOS.Trampa;
import com.example.juegosinsprites_carlosprez.R;

import java.util.ArrayList;

public class PantallaJuego extends SurfaceView implements SurfaceHolder.Callback {
    private HiloJuego hilo;
    private Boton btnSalta;
    private Joystick joystick;
    private HiloGravedad hiloGravedad;
    private TimerSaltando timer;
    private ServicioJuego servicio;
    private ArrayList<HiloTrampa> hiloTrampas;
    private ArrayList<HiloProyectil> hiloProyectiles;
    private MediaPlayer cancionJuego;
    private HiloMovHor hilomov;

    private SoundPlayer sonidosFX;
    public PantallaJuego(Context context) {
        super(context);
        getHolder().addCallback(this);
        setBackgroundColor(Color.WHITE);
        servicio = ServicioJuego.getInstance();
        this.hiloTrampas = new ArrayList<>();
        this.hiloProyectiles = new ArrayList<>();
        sonidosFX = new SoundPlayer(context);
        cancionJuego = MediaPlayer.create(context, R.raw.juegoost);
        cancionJuego.start();
        cancionJuego.setLooping(true);

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setAntiAlias(true);
        servicio.getListaNiveles().get(servicio.getNivelActual()).getJugador().dibujarPersonaje(canvas);
        for (Plataforma platform: servicio.getListaNiveles().get(servicio.getNivelActual()).getListaPlataformas()) {
            platform.dibujarPlataforma(canvas);
        }
        for (Trampa t:servicio.getListaNiveles().get(servicio.getNivelActual()).getListaTrampas() ) {
            t.dibujarTrampa(canvas);
        }
        btnSalta.dibujarControl(canvas);
        joystick.dibujarControl(canvas);
        servicio.getListaNiveles().get(servicio.getNivelActual()).getTrigger().dibujarTrigger(canvas);
        for (Proyectil proyectil:servicio.getListaNiveles().get(servicio.getNivelActual()).getListaProyectiles()) {
            proyectil.dibujarFigura(canvas);
        }
        String txtEliminaciones = "Veces eliminado:"+servicio.getNumCaidas();
        Paint pTXT = new Paint();
        pTXT.setColor(Color.BLACK);
        pTXT.setTextSize(45);
        canvas.drawText(txtEliminaciones,49,42,pTXT);



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("Pulsado");
        float x = event.getX();
        float y = event.getY();
        System.out.println(x+" "+y);
        int countDedos = event.getPointerCount();
        System.out.println(countDedos);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                for (int i=0;i<countDedos;i++){
                    float xDedo = event.getX(i);
                    float yDedo = event.getY(i);
                    if (joystick.estaDentro(xDedo,yDedo)){
                        joystick.actualizarJoyStick(xDedo,yDedo);
                        joystick.setPulsado(true);
                    }
                    if (btnSalta.estaDentro(xDedo,yDedo)){
                        servicio.getListaNiveles().get(servicio.getNivelActual()).getJugador().saltar();

                    }
                    System.out.println(xDedo+", "+yDedo);
                }
                return true;
            }
            case MotionEvent.ACTION_MOVE:{
                for(int i=0; i<countDedos; i++){
                    float xDedo = event.getX(i);
                    float yDedo = event.getY(i);
                    if (joystick.isPulsado()==true){
                        joystick.actualizarJoyStick(xDedo,yDedo);
                        joystick.setPulsado(true);
                        float[] des = joystick.devolverMov();
                        synchronized (servicio.getListaNiveles().get(servicio.getNivelActual()).getJugador()){
                            servicio.getListaNiveles().get(servicio.getNivelActual()).getJugador().cambiarVelX(des[0]);
                        }
                        if (servicio.getListaNiveles().get(servicio.getNivelActual()).getTrigger().puntoAlcanzado(servicio.getListaNiveles().get(servicio.getNivelActual()).getJugador())){
                            System.out.println("Has llegado al trigger");
                            for (HiloTrampa hilo:hiloTrampas) {
                                hilo.continuar = false;
                            }

                            hiloTrampas.clear();

                            for (HiloProyectil hilo:hiloProyectiles) {
                                hilo.continuar = false;
                            }
                            hiloProyectiles.clear();




                            boolean pasado = this.servicio.pasarNivel();
                            if (pasado == false){
                                hilo.finalizar();
                                cancionJuego.stop();
                                Intent intent = new Intent(getContext(), ActivityRegistrarResultado.class);
                                Activity act = (Activity) getContext();
                                act.finishAffinity();
                                getContext().startActivity(intent);

                            } else{
                                for (Trampa t: servicio.getListaNiveles().get(servicio.getNivelActual()).getListaTrampas()) {
                                    HiloTrampa hilo = new HiloTrampa(t,this);
                                    hiloTrampas.add(hilo);
                                    hilo.start();
                                }
                            }



                        }
                    }

                    if (btnSalta.estaDentro(xDedo,yDedo)){
                        servicio.getListaNiveles().get(servicio.getNivelActual()).getJugador().saltar();

                    }
                }


                break;
            }
            case MotionEvent.ACTION_UP:{
                joystick.actualizarJoyStick((float) (0+(getWidth()*0.1)),getHeight()-300);
                joystick.setPulsado(false);
                System.out.println("Levantado");
                servicio.getListaNiveles().get(servicio.getNivelActual()).getJugador().cambiarVelX(0);
                break;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        btnSalta = new Boton("Texto",getWidth()-400, getHeight()-300, (float) (getHeight()*0.1), Color.GREEN);
        this.joystick = new Joystick((float) (0+(getWidth()*0.1)),getHeight()-300,(float) (getHeight()*0.20));

        hilo = new HiloJuego(this);
        hilo.setRun(true);
        hilo.start();
        this.hiloGravedad = new HiloGravedad(this);
        hiloGravedad.start();
        timer = new TimerSaltando(this);
        timer.start();
        for (Trampa t: servicio.getListaNiveles().get(servicio.getNivelActual()).getListaTrampas()) {
            HiloTrampa hilo = new HiloTrampa(t,this);
            hiloTrampas.add(hilo);
            hilo.start();
        }

        hilomov = new HiloMovHor(this);
        hilomov.start();

    }

    public Boton getBtnSalta() {
        return btnSalta;
    }

    public Joystick getJoystick() {
        return joystick;
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }


    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        boolean retry = true;
        hilo.setRun(false);

        while (retry){
            try {
                hilo.join();
                retry=false;
            }catch (InterruptedException e){

            }
        }
        if (cancionJuego.isPlaying()){
            cancionJuego.stop();
        }
        cancionJuego.release();
        hiloGravedad.finalizar();
        hilomov.finalizar();

    }

    public ServicioJuego getServicio() {
        return servicio;
    }

    public ArrayList<HiloTrampa> getHiloTrampas() {
        return hiloTrampas;
    }

    public ArrayList<HiloProyectil> getHiloProyectiles() {
        return hiloProyectiles;
    }


}
