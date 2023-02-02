package com.example.juegosinsprites_carlosprez;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class PantallaJuego extends SurfaceView implements SurfaceHolder.Callback {
    private HiloJuego hilo;
    private Boton btnSalta;
    private Joystick joystick;
    private Jugador jugador;

    private ArrayList<PlataformaRect> listaPlataformas;
    private HiloGravedad hiloGravedad;
    private TimerSaltando timer;

    public PantallaJuego(Context context) {
        super(context);
        getHolder().addCallback(this);
        setBackgroundColor(Color.WHITE);
        this.listaPlataformas = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setAntiAlias(true);

        jugador.dibujarPersonaje(canvas);
        for (Plataforma platform: listaPlataformas) {
            platform.dibujarPlataforma(canvas);
        }
        btnSalta.dibujarControl(canvas);
        joystick.dibujarControl(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("Pulsado");

        float x = event.getX();
        float y = event.getY();

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
                        jugador.saltar(200);

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
                        jugador.moverPersonaje(des[0]);
                    }

                    if (btnSalta.estaDentro(xDedo,yDedo)){
                        jugador.saltar(200);

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
        this.jugador = new Jugador((getWidth()/2),(getHeight()/2)-500, (float) (getWidth()*0.01), (float) (getHeight()*0.03));
        this.listaPlataformas.add(new PlataformaRect(500,700, 700,100));

        hilo = new HiloJuego(this);
        hilo.setRun(true);
        hilo.start();
        this.hiloGravedad = new HiloGravedad(jugador,this);
        hiloGravedad.start();
        timer = new TimerSaltando(this.jugador);
        timer.start();

    }

    public Boton getBtnSalta() {
        return btnSalta;
    }

    public Joystick getJoystick() {
        return joystick;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public ArrayList<PlataformaRect> getListaPlataformas() {
        return listaPlataformas;
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
    }


}
