package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class Utilities {

    public static Color blink(int blinkInterval, int blinkSpeedLimit, Color background, Color object){
        Color randomBlackorWhite = Color.WHITE;

        blinkInterval++;
        if (blinkInterval >= 10) {
            blinkInterval = 0;
            blinkSpeedLimit++;
            if (blinkSpeedLimit >= 3) {
                blinkSpeedLimit = 0;
                randomBlackorWhite = new Random().nextBoolean() ? Color.white : Color.black;
                System.out.println(blinkSpeedLimit + ": " + randomBlackorWhite.toString());
            }
        }
        return randomBlackorWhite;
    }

}
