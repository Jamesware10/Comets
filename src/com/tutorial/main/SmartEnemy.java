package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class SmartEnemy extends GameObject {

    Random rand = new Random();

    //Randomizes rgb values
    int r = (int) (rand.nextFloat() * 256);
    int g = (int) (rand.nextFloat() * 256);
    int b = (int) (rand.nextFloat() * 256);

    //instance of handler and player
    private Handler handler;
    private GameObject player;

    //plays sound
    String filePath = "src\\com\\tutorial\\main\\Sounds\\hit-00_freesound.wav";
    private Audio audioObject = new Audio();


    //Creates a smart enemy
    public SmartEnemy(int x, int y, ID id, Handler handler) {

        //inherits position X,Y and ID attribute from Game Object class
        super(x, y, id/*, handler*/);
        this.handler = handler;

        for (int i = 0; i < handler.objects.size(); i++) {
            if (handler.objects.get(i).getId() == ID.Player) player = handler.objects.get(i);
        }

        int a = 1;
        int b = -1;
        int randomOfTwoInts = new Random().nextBoolean() ? a : b;

        //velocityX = 5 * randomOfTwoInts;
        //velocityY = 5 * randomOfTwoInts;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    public void tick() {
        x += velocityX;
        y += velocityY;

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance =
                (float) (Math.sqrt( Math.pow((x - player.getX()), 2.0) + Math.pow((y - player.getY()), 2.0) ));

        velocityX = (int) (Math.round((-1.0 / distance) * diffX));
        velocityY = (int) (Math.round((-1.0 / distance) * diffY));


        if ((y <= 0) || (y >= (Game.HEIGHT) - 48)) velocityY *= -1;
        if ((x <= 0) || (x >= (Game.WIDTH) - 32)) velocityX *= -1;

        //handler.addObject(new Trail(x, y, ID.Trail, new Color(r, g, b)/*Color.red*/, 16, 16, 0.0007f, handler));

    }

    public void render(Graphics gr) {

        Graphics2D g2d = (Graphics2D) gr;
        g2d.draw((getBounds()));

        gr.setColor(new Color(r, g, b)/*Color.red*/);
        //gr.setColor(Color.RED);
        gr.fillRect(x, y, 16, 16);
    }
}
