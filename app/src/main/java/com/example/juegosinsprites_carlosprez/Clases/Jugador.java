package com.example.juegosinsprites_carlosprez.Clases;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Jugador extends Personaje {
    private float velocidadX = 0;
    private float velocidadY = 0;
    private Estado estado;

    private boolean jugando;

    public Jugador(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.estado = Estado.CAYENDO;
        jugando = true;
    }


    public void moverPersonaje() {
        this.setX(this.getX()+velocidadX);
    }

    public void cambiarVelX(float velX){
        this.velocidadX = velX;
    }


    public void saltar(){
        if (this.estado==Estado.PARADO){
            this.estado = Estado.SALTANDO;
        }

    }

    public boolean isJugando() {
        return jugando;
    }

    public void setJugando(boolean jugando) {
        this.jugando = jugando;
    }

    public void modificarY(){
        this.setY((this.getY())+velocidadY);
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public float getVelocidadX() {
        return velocidadX;
    }

    public void setVelocidadX(float velocidadX) {
        this.velocidadX = velocidadX;
    }

    public float getVelocidadY() {
        return velocidadY;
    }

    public void setVelocidadY(float velocidadY) {
        this.velocidadY = velocidadY;
    }

    @Override
    public void dibujarPersonaje(Canvas c) {
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        c.drawRect(new Rect((int)getX(),(int)getY(),(int)(getX()+getWidth()),(int)(getY()+getHeight())),p);
    }

    public void mover(float x, float y){
        this.setX(x);
        this.setY(y);
    }
}
