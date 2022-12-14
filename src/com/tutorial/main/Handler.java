package com.tutorial.main;

import java.awt.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class Handler {

    LinkedList<GameObject> objects = new LinkedList<GameObject>();


    public void tick() {

        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);

            tempObject.render(g);
        }
    }

    public void addObject(GameObject object) {
        objects.add(object);
    }

    public void removeObject(GameObject object) {
        this.objects.remove(object);
    }

    public void clearEverything() {
        ListIterator<GameObject> x = this.objects.listIterator(0);
        while (x.hasNext()) {
            x.next();
            x.remove();
        }

    }

}
