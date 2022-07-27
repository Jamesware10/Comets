package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class Star extends GameObject {

    Random rand = new Random();
    int r = (int) (rand.nextFloat() * 256);
    int g = (int) (rand.nextFloat() * 256);
    int b = (int) (rand.nextFloat() * 256);

    Color randomBlackorWhite = Color.WHITE;

    int blinkKeep=0;
    int smallBlinkKeep = 0;

    Handler handler;

    public Star(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        int a = 1;
        int b = -1;
        int randomOfTwoInts = new Random().nextBoolean() ? a : b;

        //velocityX = 1;
        //velocityY = 1;
    }



    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    public void tick() {


        x += velocityX;
        y += velocityY;

        //for (int i = 0; i < this.handler.objects.size(); i++) {
           // GameObject tempObject = this.handler.objects.get(i);

        //}



    }

    public void render(Graphics gr) {

        gr.setColor(randomBlackorWhite);
        gr.fillRect(x, y, 1, 1);

    }
}