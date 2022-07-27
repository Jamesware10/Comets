package com.tutorial.main;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {
    //
    private static final long serialVersionUID = -7335092558177032959L;

    //Builds game window with a width, height, title
    public Window(int width, int height, String title, Game game) {

        JFrame frame = new JFrame(title);

        //defines the size of the window
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        //set important properties to window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);

        //custom icon image
        ImageIcon cometLogo = new ImageIcon(this.getClass().getResource("/com/tutorial/main/comet_2604.png"));
        frame.setIconImage(cometLogo.getImage());

        game.start();
    }
}
