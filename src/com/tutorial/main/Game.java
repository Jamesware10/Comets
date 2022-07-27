package com.tutorial.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    //Defines the dimensions
    public static final int WIDTH = 720, HEIGHT = WIDTH / 12 * 9;

    //
    @Serial
    private static final long serialVersionUID = 913977903647400664L;
    private Thread thread;
    private boolean running = false;
    public static int frames;

    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;

    private Player player1;
    private Player player2;
    private BasicEnemy randomEnenmies;
    private BasicEnemy basicEnemy1;

    public Audio audioObject;
    private String filePath;

    public enum STATE {
        Menu,
        Game
    }

    public STATE gameState = STATE.Menu;

    //game constructor
    public Game() {

        handler = new Handler();
        menu = new Menu(this, handler);
        //Key Event Listener
        this.addKeyListener(new KeyInputHandler(handler));
        this.addMouseListener(menu);

        new Window(WIDTH, HEIGHT, "Comets", this);

        hud = new HUD();



        r = new Random();

        spawner = new Spawn(handler, hud);

        player1 = new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler);
        player2 = new Player(WIDTH / 2 + 64, HEIGHT / 2 - 32, ID.Player2, handler);
        basicEnemy1 = new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler);


        if (gameState == STATE.Game) {

            //plays music
            filePath = "/com/tutorial/main/resources/Sounds/Tower of God OST - Ma by Kevin Penkin.wav";
            audioObject = new Audio();
            audioObject.playMusic(filePath);

            //stars
            //for (int i = 0; i < 150; i++) {
                //handler.addObject(new Star(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Star, handler));
            //}

            //players and enemies
            //handler.addObject(player1);

            //handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));


        }if (gameState == STATE.Menu) {

            for (int i = 0; i < 150; i++) {
                handler.addObject(new Star(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Star, handler));
            }

            for (int i = 0; i < 4; i++) {
                handler.addObject(new MenuEnemy(r.nextInt(Game.WIDTH-48), r.nextInt(Game.HEIGHT-48), ID.MenuEnemy,
                        handler));
            }


            //handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-48), r.nextInt(Game.HEIGHT-48), ID.BasicEnemy,
                    //handler));


            //remove game objects from scene
            /*for (int i = 0; i < handler.objects.size(); i++) {
                GameObject tempObject = handler.objects.get(i);
                if (tempObject.getId() == ID.Player || tempObject.getId() == ID.Star || tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy) {
                    handler.removeObject(tempObject);
                }

            }*/
        }

    }

    //Sets and maximum and minimum value for a variable(has clever applictions)
    public static int clamp(int var, int min, int max) {
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else return var;
    }

    //Main method
    public static void main(String[] args) {
        new Game();
    }

    //starts the game
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    //stops the game
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //game's logic
    public void run() {
        //starts game in focused window
        this.requestFocus();

        //current system time in nanoseconds
        long lastTime = System.nanoTime();

        //current time in milliseconds
        long timer = System.currentTimeMillis();

        //ticks per second
        double amountOfTicks = 60.0;

        //nanoseconds per tick
        double ns = 1000000000 / amountOfTicks;

        //time difference between previous current and previous frame
        double delta = 0;

        //frames drawn or displayed
        frames = 0;

        while (running) {
            //current system time in nanoseconds
            long now = System.nanoTime();

            // delta time is equal to delta plus the difference between in time prev and current frame divided by
            // nanoseconds to get time in seconds
            delta += (now - lastTime) / ns;
            lastTime = now;

            //game time runs as long as delta time is one or higher, decrements delta time
            while (delta >= 1) {
                tick();
                delta--;
            }

            //renders frames
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }

        stop();

    }

    //game's internal time loop
    private void tick() {
        handler.tick();
        if (gameState == STATE.Game) {
            hud.tick();
            spawner.tick();
        } else if (gameState == STATE.Menu) {
            menu.tick();
        }
        if (HUD.HEALTH <= 0) {
            this.gameState = STATE.Menu;
            handler.clearEverything();

            //stars
            for (int i = 0; i < 150; i++) {
                handler.addObject(new Star(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Star, handler));
            }

            for (int i = 0; i < 3; i++) {
                handler.addObject(new MenuEnemy(r.nextInt(Game.WIDTH-48), r.nextInt(Game.HEIGHT-48), ID.MenuEnemy,
                        handler));
            }

            audioObject= new Audio();
            audioObject.stopMusic(Audio.clip1);
            HUD.HEALTH = 200;
            HUD.score = 0;
            HUD.level=1;
            /*for (int i = 0; i < handler.objects.size(); i++) {
                GameObject tempObject = handler.objects.get(i);
                if (tempObject.getId()==ID.Player||tempObject.getId()==ID.BasicEnemy){
                    handler.clearEverything();

                }
            }*/
        }

    }

    //draws graphics to the display
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(new Color(0, 0, 0));

        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        if (gameState == STATE.Game) {
            hud.render(g);
        } else if (gameState == STATE.Menu) {
            menu.render(g);
        }


        g.dispose();
        bs.show();
    }


}
