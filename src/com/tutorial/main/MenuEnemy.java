package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class MenuEnemy extends GameObject {

    Random rand = new Random();
    int r = (int) (rand.nextFloat() * 256);
    int g = (int) (rand.nextFloat() * 256);
    int b = (int) (rand.nextFloat() * 256);

    private Handler handler;

    String filePath = "/com/tutorial/main/resources/Sounds/hit-00_freesound.wav";
    private Audio audioObject = new Audio();


    public MenuEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id/*, handler*/);

        this.handler = handler;

        int a = 1;
        int b = -1;
        int randomOfTwoInts = new Random().nextBoolean() ? a : b;

        velocityX = (rand.nextInt(9 - 3) + 3) * randomOfTwoInts;
        velocityY = (rand.nextInt(9 - 3) + 3) * randomOfTwoInts;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 15, 15);
    }

    public void tick() {
        x += velocityX;
        y += velocityY;

        if ((y <= 0) || (y >= (Game.HEIGHT) - 48)) {
            audioObject.playSound(filePath);
            velocityY *= -1;
        }
        if ((x <= 0) || (x >= (Game.WIDTH) - 32)) {
            audioObject.playSound(filePath);
            velocityX *= -1;
        }

        handler.addObject(new Trail(x, y, ID.Trail, new Color(r, g, b)/*Color.red*/, 16, 16, 0.0003f, handler));

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);

            int a = 1;
            int b = -1;
            int randomOfTwoInts = new Random().nextBoolean() ? a : b;

            if (tempObject.getId() == ID.BasicEnemy) {
                if ((tempObject.velocityY == 0)) {
                    tempObject.setVelocityY(5 * randomOfTwoInts);
                }
                if ((tempObject.velocityX == 0)) {
                    tempObject.setVelocityX(5 * randomOfTwoInts);
                }
            }

        }
    }

    public void render(Graphics gr) {

        Graphics2D g2d = (Graphics2D) gr;
        g2d.draw((getBounds()));

        gr.setColor(new Color(r, g, b)/*Color.BLACK*/);
        //gr.setColor(Color.RED);
        gr.fillRect(x, y, 16, 16);
    }
}
