package com.tutorial.main;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class HUD extends Thread{

    public int r;
    public int g;
    public int b;
    private int smallIterator = 1;

    public static int HEALTH = 100;

    public static int level = 1;
    public static int score = 0;
    private int smallTime = 0;

    Timer timer = new Timer();
    private int fps;

    public void tick() {
        //HEALTH--;
        HEALTH = Game.clamp(HEALTH, 0, 100);

        if (HEALTH > 60) {
            r = 0;
            g = 255;
            b = 0;
        }
        if ((HEALTH <= 60)) {
            r = 255;
            b = 0;
            if ((HEALTH <= 25)) {
                g = 0;
            }
        }

        smallTime += smallIterator;
    }

    public void render(Graphics gr) {

        //Health bar background color
        gr.setColor(Color.GRAY);
        gr.fillRect(15, 15, 200, 16);

        //health bar foreground colors
        gr.setColor(new Color(r, g, b));
        gr.fillRect(15, 15, HEALTH * 2, 16);
        gr.setColor(Color.WHITE);
        gr.drawRect(15, 15, 200, 16);

        Font normalFont = gr.getFont();
        Font hpFont = new Font("Arial", Font.PLAIN, 14);

        //HUD Score and Level
        gr.setFont(hpFont);
        gr.setColor(Player.color);
        gr.drawString("HP: " + HEALTH + "%", 80, 28);

        gr.setColor(Color.white);
        gr.setFont(normalFont);
        gr.drawString("Level: " + level, 15, 48);
        gr.drawString("Score: " + score, 15, 64);

        gr.drawString("FPS: "+Game.frames,15,80);


    }


    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setSmallTime(int smallTime) {
        this.smallTime = score;
    }

    public int getSmallTime() {
        return smallTime;
    }

    public void setFPS(int fps) {
        this.fps = fps;
    }


    public int getFPS() {
        return fps;
    }
}
