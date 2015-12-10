package com.example.alec.myfirstgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Player extends GameObject {
    private Bitmap spritesheet;
    private int score;
    private boolean up;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;
    private ArrayList<String> codon = new ArrayList<>();
    private String[] amino_acids = {"Phenylalanine","Leucine","Isoleucine","Methionine","Valine",
            "Serine","Proline","Threonine","Alanine","Tyrosine","Histine","Glutamine","Asparagine",
            "Lysine","Aspartate","Glutamate","Cysteine","Arginine","Serine","Glycine"};
    private ArrayList<ArrayList<String>> amino_codons = new ArrayList<>();
    private String acid;
    private int acid_index;
    private String target_codon;
    private Random r = new Random();


    public Player(Bitmap res, int w, int h, int numFrames){

        amino_codons.add(new ArrayList<String>(Arrays.asList("UUU", "UUC")));
        amino_codons.add(new ArrayList<String>(Arrays.asList("UUA", "UUG","CUU","CUC","CUA","CUG")));
        amino_codons.add(new ArrayList<String>(Arrays.asList("AUU", "AUC","AUA")));
        amino_codons.add(new ArrayList<String>(Arrays.asList("AUG")));
        amino_codons.add(new ArrayList<String>(Arrays.asList("GUU", "GUC","GUA","GUG")));
        amino_codons.add(new ArrayList<String>(Arrays.asList("UCU", "UCC","UCA","UCG")));
        amino_codons.add(new ArrayList<String>(Arrays.asList("CUU", "CCC","CCA","CCG")));
        amino_codons.add(new ArrayList<String>(Arrays.asList("ACU", "ACC","ACA","ACG")));
        amino_codons.add(new ArrayList<String>(Arrays.asList("GCU", "GCC","GCA","GCG")));
        amino_codons.add(new ArrayList<String>(Arrays.asList("UAU", "UAC")));
        amino_codons.add(new ArrayList<String>(Arrays.asList("CAU", "CAC")));
        amino_codons.add(new ArrayList<String>(Arrays.asList("CAA", "CAG")));
        amino_codons.add(new ArrayList<String>(Arrays.asList("AUU", "AAC")));
        amino_codons.add(new ArrayList<String>(Arrays.asList("AAA", "AAG")));
        amino_codons.add(new ArrayList<String>(Arrays.asList("GAU", "GAC")));
        amino_codons.add(new ArrayList<String>(Arrays.asList("GAA", "GAG")));
        amino_codons.add(new ArrayList<String>(Arrays.asList("UGU", "UGC")));
        amino_codons.add(new ArrayList<String>(Arrays.asList("CGU", "CGC","CGA","CGG","AGA","AGG")));
        amino_codons.add(new ArrayList<String>(Arrays.asList("AGU", "AGC")));
        amino_codons.add(new ArrayList<String>(Arrays.asList("GGU", "GGC","GGA","GGG")));

        acid_index = r.nextInt(amino_acids.length);
        acid = amino_acids[acid_index];
        target_codon = amino_codons.get(acid_index).get(r.nextInt(amino_codons.get(acid_index).size()));

        x = 100;
        y = GamePanel.HEIGHT/2;
        dy = 0;
        score = 0;
        height = h;
        width = w;
        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;
        for (int i = 0; i < image.length; ++i){
            image[i] = Bitmap.createBitmap(spritesheet, i*width,0,width,height);
        }
        animation.setFrames(image);
        animation.setDelay(10);
        startTime = System.nanoTime();
    }

    public String getTargetCodon(){
        return target_codon;
    }

    public String getAcid(){
        return acid;
    }

    public String getCodon(){
        String full = "";
        for (String c: codon){
            full += c;
            full += " ";
        }
        return full;
    }

    public void addScore(int a){
        score += a;
    }

    public String getCodonCompact(){
        String full = "";
        for (String c: codon){
            full += c;
        }
        return full;
    }

    public void newAcid(){
        acid_index = r.nextInt(amino_acids.length);
        acid = amino_acids[acid_index];
        target_codon = amino_codons.get(acid_index).get(r.nextInt(amino_codons.get(acid_index).size()));
    }

    public void addLetter(Letter l){
        codon.add(l.getAcid());
    }

    public void clearCodon(){
        codon.clear();
    }

    public void setUp(boolean b){
        up = b;
    }

    public void update(){
        long elapsed = (System.nanoTime()-startTime)/1000000;
        if (elapsed > 100){
            score++;
            startTime = System.nanoTime();
        }
        animation.update();
        if(up){
            dy -= 1;
        }
        else {
            dy += 1;
        }

        if(dy>14){
            dy = 14;
        }
        if (dy < -14){
            dy = -14;
        }

        y+= dy*1.1;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(animation.getImage(),x,y,null);
    }

    public int getScore(){
        return score;
    }

    public boolean getPlaying(){
        return playing;
    }

    public void setPlaying(boolean b){
        playing = b;
    }

    public void resetDY(){
        dy = 0;
    }

    public void resetScore(){
        score = 0;
    }

}
