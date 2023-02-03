package com.example.juegosinsprites_carlosprez;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Proyectil {
    private float x,y,radio, desX, desY;

    public Proyectil(float x, float y, float radio, float desX, float desY) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.desX = desX;
        this.desY = desY;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRadio() {
        return radio;
    }

    public void dibujarFigura(Canvas c) {
        Paint p = new Paint();
        p.setColor(Color.CYAN);
        c.drawCircle(this.getX(),getY(),radio,p);
    }

    public void moverFigura(){
        this.x = this.x+desX;
        this.y = this.y+desY;
    }

    public void moverFigura(float x, float y){
        this.x = x;
        this.y = y;

    }

    public boolean estaDentro(float x, float y) {
        float distanciax = x - this.getX();
        float distanciaY = y- getY();
        if (Math.pow(radio,2) > (Math.pow(distanciax,2)+Math.pow(distanciaY,2))){
            return true;
        }
        else return false;
    }

}
