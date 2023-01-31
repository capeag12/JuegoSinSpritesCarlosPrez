package com.example.juegosinsprites_carlosprez;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Objects;

public abstract class Plataforma {
    private float x,y;

    public Plataforma(float x, float y) {

        this.x = x;
        this.y = y;

    }
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }


    abstract public void dibujarPlataforma(Canvas c);

}
