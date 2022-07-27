package com.tutorial.main;

import java.awt.*;

public abstract class GameObject {

    protected int x, y, velocityX, velocityY, velocity;
    protected ID id;
    //Handler handler;

    public GameObject(int x, int y, ID id /*, Handler handler*/) {
        //this.handler=handler;
        this.x = x;
        this.y = y;
        this.id = id;

        //handler.addObject(this) ;
    }

    public GameObject() {

    }

    //abstract methods
    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocityX, int velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

}
