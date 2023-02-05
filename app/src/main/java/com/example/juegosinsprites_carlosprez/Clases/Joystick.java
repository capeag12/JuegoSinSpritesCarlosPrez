package com.example.juegosinsprites_carlosprez.Clases;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Joystick extends Control {
    private float xCirculoDentro, yCirculoDentro, radioCirculoDentro;
    private boolean pulsado;

    public Joystick(float x, float y, float radio) {
        super(x, y, radio);
        this.xCirculoDentro = this.getX();
        this.yCirculoDentro = this.getY();
        this.radioCirculoDentro = (float) (this.getRadio()/2.3);
        pulsado = false;
    }

    public boolean estaDentro(float x, float y) {
        float distanciax = x - this.getX();
        float distanciaY = y- this.getY();
        if (Math.pow(getRadio(),2) > (Math.pow(distanciax,2)+Math.pow(distanciaY,2))){
            return true;
        }
        else return false;
    }

    public void dibujarControl(Canvas c){
        Paint pGrande = new Paint();
        pGrande.setColor(Color.GRAY);
        pGrande.setAlpha(80);
        Paint pChico = new Paint();
        pChico.setColor(Color.RED);
        pChico.setAlpha(80);
        c.drawCircle(this.getX(), this.getY(), this.getRadio(), pGrande);
        c.drawCircle(this.xCirculoDentro, this.yCirculoDentro, this.radioCirculoDentro, pChico);
    }

    public float[] devolverMov(){
        float x = xCirculoDentro-this.getX() ;
        float y = yCirculoDentro-this.getY()  ;
        float[] des = {x/15,y/15};
        return  des;

    }

    public void actualizarJoyStick(float x, float y){
        if (estaDentro(x,y)== false){


        }
        else{
            this.xCirculoDentro = x;
            this.yCirculoDentro = y;
        }


    }

    public boolean isPulsado() {
        return pulsado;
    }

    public void setPulsado(boolean pulsado) {
        this.pulsado = pulsado;
    }

    @Override
    public String toString() {
        return "Joystick{" +
                "x=" + getX() +
                ", y=" + getY() +
                ", radio=" + getRadio() +
                ", xCirculoDentro=" + xCirculoDentro +
                ", yCirculoDentro=" + yCirculoDentro +
                ", radioCirculoDentro=" + radioCirculoDentro +
                '}';
    }
}
