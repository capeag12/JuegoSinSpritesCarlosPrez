package com.example.juegosinsprites_carlosprez;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Objects;

public class Trampa {
    private int id;
    private static int idAutoInc = 0;
    private float x,y,width,height;
    private Direccion direccion;

    public Trampa(float x, float y, float width, float height, Direccion direction) {
        this.id = idAutoInc;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direccion = direction;
        idAutoInc++;
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

    public Proyectil disparar(){
        Proyectil p = null;
        if (direccion == Direccion.Arriba){
            p = new Proyectil((this.x+(this.width/2)),(this.y+(width/2)), (width/2),0,-7);
        } else if (direccion == Direccion.Abajo) {
            p = new Proyectil((this.x+(this.width/2)),(this.y+(width/2)), (width/2),0,7);
        } else if (direccion == Direccion.Derecha) {
            p = new Proyectil((this.x+(this.width/2)),(this.y+(width/2)), (width/2),7,0);
        } else if (direccion == Direccion.Izquierda) {
            p = new Proyectil((this.x+(this.width/2)),(this.y+(width/2)), (width/2),-7,0);
        }
        return p;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void dibujarTrampa(Canvas c) {
        Paint p = new Paint();
        p.setColor(Color.CYAN);
        c.drawRect(new Rect((int)getX(),(int)getY(),(int)(getX()+width),(int)(getY()+width)),p);

    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trampa trampa = (Trampa) o;
        return id == trampa.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
