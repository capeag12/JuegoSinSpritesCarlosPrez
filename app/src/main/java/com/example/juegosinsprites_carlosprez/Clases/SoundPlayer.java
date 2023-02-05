package com.example.juegosinsprites_carlosprez.Clases;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import com.example.juegosinsprites_carlosprez.R;

public class SoundPlayer {

    private static SoundPool soundPool;
    private static int jumpSound;

    private static int sonidoHit;

    public SoundPlayer(Context c) {
        soundPool = new SoundPool.Builder().setMaxStreams(2).build();

        jumpSound = soundPool.load(c, R.raw.jump,1);
        sonidoHit = soundPool.load(c,R.raw.hitmarker,1);
    }

    public static void sonidoSaltar(){
        soundPool.play(jumpSound,1f,1f,1,0,1);
    }
    public static void sonidoHit(){
        soundPool.play(sonidoHit,1f,1f,1,0,1);
    }
}
