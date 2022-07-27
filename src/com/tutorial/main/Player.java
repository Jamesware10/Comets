package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    private Handler handler;

    Random rand = new Random();
    int r = (int) (rand.nextFloat() * 256);
    int g = (int) (rand.nextFloat() * 256);
    int b = (int) (rand.nextFloat() * 256);

    public static Color color;

    String filePath = "src\\com\\tutorial\\main\\Sounds\\hit-01_freesound.wav";
    private Audio audioObject = new Audio();

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        //velocityX = 10;
        //velocityY = 5;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    public void tick() {

        x += velocityX;
        y += velocityY;

        x = Game.clamp(x, 0, Game.WIDTH - 48);
        y = Game.clamp(y, 0, Game.HEIGHT - 64);


        collision();

        handler.addObject(new Trail(x, y, ID.Trail, new Color(r, g, b)/*Color.red*/, 32, 32, 0.0008f, handler));

    }

    private void collision() {

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);

            if ((tempObject.getId() == ID.BasicEnemy) || (tempObject.getId() == ID.FastEnemy) || (tempObject.getId() == ID.SmartEnemy)) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 2;

                }
            }
        }



    }


    public void render(Graphics gr) {

        //Box collider bounds
        Graphics2D g2d = (Graphics2D) gr;
        g2d.draw((getBounds()));

        color = new Color(r, g, b);

        if (id == ID.Player) gr.setColor(color);
        else if (id == ID.Player2) gr.setColor(new Color(255, 0, 127));

        gr.fillRect(x, y, 32, 32);




    }


}
