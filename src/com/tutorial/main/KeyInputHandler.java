package com.tutorial.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputHandler implements KeyListener {

    private final Handler handler;

    //variables to check if key is pressed
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public static int key;


    public KeyInputHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        key = e.getKeyCode();

        //for each player object listen for key inputs
        for (GameObject i : handler.objects) {
            if (i.getId() == ID.Player) {

                if (key == KeyEvent.VK_W) {
                    upPressed = true;
                    i.setVelocityY(-5);
                }
                if (key == KeyEvent.VK_A) {
                    leftPressed = true;
                    i.setVelocityX(-5);
                }
                if (key == KeyEvent.VK_S) {
                    downPressed = true;
                    i.setVelocityY(5);
                }
                if (key == KeyEvent.VK_D) {
                    rightPressed = true;
                    i.setVelocityX(5);
                }


            }

            if (i.getId() == ID.Player) {

                if (key == KeyEvent.VK_UP) {
                    upPressed = false;
                    i.setVelocityY(-5);
                }
                if (key == KeyEvent.VK_LEFT) {
                    leftPressed = false;
                    i.setVelocityX(-5);
                }
                if (key == KeyEvent.VK_DOWN) {
                    downPressed = false;
                    i.setVelocityY(5);
                }
                if (key == KeyEvent.VK_RIGHT) {
                    rightPressed = false;
                    i.setVelocityX(5);
                }

            }
        }

        if (key == KeyEvent.VK_ESCAPE) System.exit(0);

        //System.out.println(key);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (GameObject i : handler.objects) {

            //Stops player movement upon key release
            if (i.getId() == ID.Player) {

                if (key == KeyEvent.VK_W) {
                    upPressed = false;
                    if (downPressed) {
                        i.setVelocityY(-5);
                    } else {
                        i.setVelocityY(0);
                    }
                }

                if (key == KeyEvent.VK_A) {
                    leftPressed = false;
                    if (rightPressed) {
                        i.setVelocityX(5);
                    } else {
                        i.setVelocityX(0);
                    }
                }

                if (key == KeyEvent.VK_S) {
                    downPressed = false;
                    if (upPressed) {
                        i.setVelocityY(5);
                    } else {
                        i.setVelocityY(0);
                    }
                }

                if (key == KeyEvent.VK_D) {
                    rightPressed = false;
                    if (leftPressed) {
                        i.setVelocityX(-5);
                    } else {
                        i.setVelocityX(0);
                    }
                }
            }

            //stops player 2 movement upon release
            if (i.getId() == ID.Player) {

                if (key == KeyEvent.VK_UP) {
                    upPressed = false;
                    if (downPressed) {
                        i.setVelocityY(-5);
                    } else {
                        i.setVelocityY(0);
                    }
                }

                if (key == KeyEvent.VK_LEFT) {
                    leftPressed = false;
                    if (rightPressed) {
                        i.setVelocityX(5);
                    } else {
                        i.setVelocityX(0);
                    }
                }

                if (key == KeyEvent.VK_DOWN) {
                    downPressed = false;
                    if (upPressed) {
                        i.setVelocityY(5);
                    } else {
                        i.setVelocityY(0);
                    }
                }

                if (key == KeyEvent.VK_RIGHT) {
                    rightPressed = false;
                    if (leftPressed) {
                        i.setVelocityX(-5);
                    } else {
                        i.setVelocityX(0);
                    }
                }
            }

        }

        if (key == KeyEvent.VK_ESCAPE) System.exit(0);
    }
}
