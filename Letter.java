package com.example.alec.myfirstgame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import java.util.Random;

import java.util.Random;


public class Letter extends GameObject{
    private int score;
    private int speed;
    private Random rand = new Random();
    private String c;
    private Paint p;
    private Random r = new Random();
    private String[] nt = {"A","G","C","U"};


    public Letter(int x, int y, int s){
        super.x = x;
        super.y = y;
        width = 40;
        height = 40;
        score = s;
        p = new Paint();
        p.setTextSize(40);
        p.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        c = nt[r.nextInt(nt.length)];

        speed = 7 + (int) (rand.nextDouble()*score/30);

        if(speed >= 40){
            speed = 40;
        }

    }

    public String getAcid(){
        return c;
    }

    public void update(){
        x -= speed;
    }

    public void draw(Canvas canvas){
        try{
            canvas.drawText(c, x, y, p);
        } catch(Exception e){}
    }

    @Override
    public int getWidth(){
        return width-10;
    }
}

