package com.tutorial.main;

import javax.imageio.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

    Random r = new Random();

    public Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;

    }


    public void mousePressed(MouseEvent e) {

        int mouseX = e.getX();
        int mouseY = e.getY();

        if (game.gameState == Game.STATE.Menu) {
            if (mouseOver(mouseX, mouseY, 490, 270, 150, 40)) {

                if (game.gameState == Game.STATE.Menu) {
                    handler.clearEverything();
                }

                //start game
                game.gameState = Game.STATE.Game;

                //plays music
                String filePath = "/com/tutorial/main/resources/Sounds/Tower of God OST - Ma by Kevin Penkin.wav";
                Audio audioObject = new Audio();
                audioObject.playMusic(filePath);

                //stars
                for (int i = 0; i < 150; i++) {
                    handler.addObject(new Star(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Star, handler));
                }

                //players and enemies
                handler.addObject(new Player(r.nextInt(Game.WIDTH - 48), r.nextInt(Game.HEIGHT - 48), ID.Player, handler));

                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 48), r.nextInt(Game.HEIGHT - 48), ID.BasicEnemy,
                        handler));


            } else if (mouseOver(mouseX, mouseY, 510, 320, 110, 40)) {
                //Options
            } else if (mouseOver(mouseX, mouseY, 530, 370, 70, 40)) {
                //Exit game
                this.game.setCursor(cursor);
                System.exit(0);
            }
        }
    }


    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        //If mousePosX is within selected area, retrun true else false
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else {
                return false;
            }

        } else return false;
    }


    public void tick() {

    }

    public void render(Graphics g) {
        Font titleFont = new Font("Arial", Font.BOLD, 45);
        Font menuFont = new Font("Arial", Font.BOLD, 35);
        Font menuOptionsFont = new Font("Arial", Font.PLAIN, 25);

        BufferedImage image = null;
        try {
            //URL url = new URL("/com/tutorial/main/img/comet_2604.png");
                image = ImageIO.read(getClass().getResource("/com/tutorial/main/resources/img/comet_2604.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }



        g.drawImage(image, 50, 60, null);

        g.setColor(Color.RED);
        g.setFont(titleFont);
        g.drawString("Comets", 160, 120);

        g.setColor(Color.white);
        g.setFont(menuFont);
        g.drawString("Main Menu", 470, 220);

        g.setFont(menuOptionsFont);
        //g.drawRect(490, 270, 150, 40);
        g.drawString("Start Game", 500, 300);

        //g.drawRect(510, 320, 110,40);
        g.drawString("Options", 520, 350);

        //g.drawRect(530, 370, 70,40);
        g.drawString("Quit", 540, 400);
    }
}
