package com.example.juegosinsprites_carlosprez;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Jugador extends Personaje {
    private float velocidadX = 0;
    private float velocidadY = 0;
    private Estado estado;

    public Jugador(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.estado = Estado.PARADO;
    }

    @Override
    public void moverPersonaje(float desX) {
        if(desX > 0){
            this.estado = Estado.MOVDerecha;
        }
        else{
            this.estado = Estado.MOVIzquierda;
        }

        this.setX(this.getX()+desX);
    }


    public void saltar(float salto){
        this.estado = Estado.SALTANDO;
        this.setY(this.getY()-salto);
    }


    @Override
    public void dibujarPersonaje(Canvas c) {
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        c.drawRect(new Rect((int)getX(),(int)getY(),(int)(getX()+getWidth()),(int)(getY()+getHeight())),p);
    }
}
