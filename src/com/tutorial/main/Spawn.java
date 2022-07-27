package com.tutorial.main;

import java.util.Random;

public class Spawn {
    private Handler handler;
    private HUD hud;

    private Random r = new Random();

    private int scoreKeep = 0;
    private int smallKeep = 0;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;

    }

    public void tick() {
        smallKeep++;
        int nextLevel = 0;

        if (smallKeep >= 10) {
            smallKeep = 0;

            hud.setScore(hud.getScore() + 1);
            hud.setFPS(Game.frames);

            //System.out.println();

            scoreKeep++;



            if (scoreKeep >= 200) {
                scoreKeep = 0;
                int lastScore = hud.getLevel();
                hud.setLevel(hud.getLevel() + 1);


                if (hud.getLevel() == 2) {
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-16), r.nextInt(Game.HEIGHT-32), ID.BasicEnemy,
                            handler));
                }

                else if (hud.getLevel() == 3) {
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-32), ID.FastEnemy,
                        handler));
                }

                else if (hud.getLevel() == 4) {
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-32), ID.SmartEnemy,
                            handler));
                }else{
                        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-16), r.nextInt(Game.HEIGHT-16), ID.BasicEnemy,
                                handler));
                }

            }
        }
    }
}