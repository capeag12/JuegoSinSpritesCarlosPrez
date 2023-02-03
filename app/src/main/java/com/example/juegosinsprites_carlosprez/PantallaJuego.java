package com.example.juegosinsprites_carlosprez;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class PantallaJuego extends SurfaceView implements SurfaceHolder.Callback {
    private HiloJuego hilo;
    private Boton btnSalta;
    private Joystick joystick;
    private HiloGravedad hiloGravedad;
    private TimerSaltando timer;
    private Trigger trigger;
    private ArrayList<Nivel> listaNiveles;
    private int nivelActual;

    private float xInicial;
    private float yInicial;

    public PantallaJuego(Context context) {
        super(context);
        getHolder().addCallback(this);
        setBackgroundColor(Color.WHITE);

        crearNiveles();
        nivelActual = 0;
    }

    private void crearNiveles(){
        this.listaNiveles = new ArrayList<>();
        ArrayList<Trampa> listaTrampa = new ArrayList<>();
        listaTrampa.add(new Trampa(1039,777,80,80,Direccion.Arriba));

        ArrayList<PlataformaRect> listaPlataforma = new ArrayList<>();
        listaPlataforma.add(new PlataformaRect(100,400, 700,50));
        listaPlataforma.add(new PlataformaRect(1195,400, 700,50));
        Trigger trigger = new Trigger(1788,300, 100,100);
        listaNiveles.add(new Nivel(listaPlataforma,trigger,listaTrampa,285,378));


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setAntiAlias(true);
        listaNiveles.get(nivelActual).getJugador().dibujarPersonaje(canvas);
        for (Plataforma platform: listaNiveles.get(nivelActual).getListaPlataformas()) {
            platform.dibujarPlataforma(canvas);
        }
        for (Trampa t:listaNiveles.get(nivelActual).getListaTrampas() ) {
            t.dibujarTrampa(canvas);
        }
        btnSalta.dibujarControl(canvas);
        joystick.dibujarControl(canvas);
        listaNiveles.get(nivelActual).getTrigger().dibujarTrigger(canvas);
        for (Proyectil proyectil:listaNiveles.get(nivelActual).getListaProyectiles()) {
            proyectil.dibujarFigura(canvas);
        }
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
                    }
                    if (btnSalta.estaDentro(xDedo,yDedo)){
                        listaNiveles.get(nivelActual).getJugador().saltar();
                    }
                    System.out.println(xDedo+", "+yDedo);
                }
                return true;
            }
            case MotionEvent.ACTION_MOVE:{
                for(int i=0; i<countDedos; i++){
                    float xDedo = event.getX(i);
                    float yDedo = event.getY(i);
                    if (joystick.estaDentro(xDedo,yDedo)){
                        joystick.actualizarJoyStick(xDedo,yDedo);
                        float[] des = joystick.devolverMov();
                        listaNiveles.get(nivelActual).getJugador().moverPersonaje(des[0]);

                        if (listaNiveles.get(nivelActual).getTrigger().puntoAlcanzado(listaNiveles.get(nivelActual).getJugador().getX(), listaNiveles.get(nivelActual).getJugador().getY(),listaNiveles.get(nivelActual).getJugador().getWidth())){
                            System.out.println("Has llegado al trigger");

                        }
                    }

                    if (btnSalta.estaDentro(xDedo,yDedo)){
                        listaNiveles.get(nivelActual).getJugador().saltar();

                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP:{
                joystick.actualizarJoyStick((float) (0+(getWidth()*0.1)),getHeight()-300);
                System.out.println("Levantado");
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
        this.hiloGravedad = new HiloGravedad(listaNiveles.get(nivelActual).getJugador(),this);
        hiloGravedad.start();
        timer = new TimerSaltando(listaNiveles.get(nivelActual).getJugador());
        timer.start();
        for (Trampa t: listaNiveles.get(nivelActual).getListaTrampas()) {
            HiloTrampa hilo = new HiloTrampa(t,this);
            hilo.start();
        }

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

    public float getxInicial() {
        return xInicial;
    }

    public float getyInicial() {
        return yInicial;
    }

    public ArrayList<Nivel> getListaNiveles() {
        return listaNiveles;
    }

    public int getNivelActual() {
        return nivelActual;
    }

    public ArrayList<PlataformaRect> getListaPlataformas(){
        return listaNiveles.get(nivelActual).getListaPlataformas();
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
    }


}
