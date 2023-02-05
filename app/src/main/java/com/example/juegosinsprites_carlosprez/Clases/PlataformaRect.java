package com.example.juegosinsprites_carlosprez.Clases;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class PlataformaRect extends Plataforma {
    private int anchura, altura;

    public PlataformaRect(float x, float y, int anchura, int altura) {
        super(x, y);
        this.anchura = anchura;
        this.altura = altura;
    }

    public int getAnchura() {
        return anchura;
    }

    public int getAltura() {
        return altura;
    }

    @Override
    public void dibujarPlataforma(Canvas c) {
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        c.drawRect(new Rect((int)getX(),(int)getY(),(int)(getX()+anchura),(int)(getY()+altura)),p);
    }
}
