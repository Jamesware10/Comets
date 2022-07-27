package com.tutorial.main;

import java.awt.*;

public class Trail extends GameObject {

    private BasicEnemy sprite;
    private float alpha = 1;
    private float life;

    Handler handler;
    private Color color;

    private int width;
    private int height;

    //life = 0,001 -0.1

    public Trail(int x, int y, ID id, Color color, int width, int height, float life, Handler handler) {
        super(x, y, id /*, handler*/);
        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
    }

    public Trail(BasicEnemy sprite, ID id,Color color, int width, int height, float life){
        this.sprite = sprite;
        this.id = id;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
    }

    public void tick() {

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);

            if (alpha > life) {
                alpha -= (life - 0.0001f);
            } else handler.removeObject(this);

        }
    }

    public void render(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setComposite(makeTransparent(alpha));

        g.setColor(color);
        g.fillRect(x, y, width, height);

        g2D.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type, alpha);
    }

    public Rectangle getBounds() {
        return null;
    }
}
