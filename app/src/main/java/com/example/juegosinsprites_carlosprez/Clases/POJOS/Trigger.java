package com.example.juegosinsprites_carlosprez.Clases.POJOS;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Trigger {
    private float x,y,width, height;

    public Trigger(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void dibujarTrigger(Canvas c) {
        Paint p = new Paint();
        p.setColor(Color.GREEN);
        c.drawRect(new Rect((int)getX(),(int)getY(),(int)(getX()+getWidth()),(int)(getY()+getHeight())),p);
    }

    public boolean puntoAlcanzado(Jugador jugador){
        boolean alcanzado = false;
        Rect rectJugador = new Rect();
        rectJugador.set((int) jugador.getX(), (int) jugador.getY(), (int) (jugador.getX()+ jugador.getWidth()), (int) (jugador.getY()+jugador.getHeight()));
        Rect rectTrigger = new Rect();
        rectTrigger.set((int) getX(), (int) getY(), (int) (getX()+ getWidth()), (int) (getY()+getHeight()));
        if (Rect.intersects(rectJugador,rectTrigger)){
            alcanzado=true;
        }
        return alcanzado;
    }
}
