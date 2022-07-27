package com.tutorial.main;

import javax.sound.sampled.*;
import java.net.URL;


public class Audio {

    //public static AudioInputStream audioInput;
    public AudioFormat format;
    public DataLine.Info info;
    public static Clip clip1;
    public static Clip clip2;
    public FloatControl gainControl;
    //public File musicPath;

    void playMusic(String musicLocation) {

        try {

            URL musicPath = getClass().getResource(musicLocation);
            //URL musicURL = new URL(musicLocation);

            //if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                //Clip clip = AudioSystem.getClip();

                //gets audio file location, format and data line info of audiofile
                //audioInput = AudioSystem.getAudioInputStream(getClass().getResource(musicLocation));
                format = audioInput.getFormat();
                info = new DataLine.Info(Clip.class, format);

                //stores audio file as a clip to played
                clip1 = (Clip) AudioSystem.getLine(info);

                //AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(getClass().getResource(musicLocation));

                clip1.open(audioInput);

                //Controls the volume of the audio file
                gainControl = (FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-30.0f);

                clip1.start();
                clip1.loop(Clip.LOOP_CONTINUOUSLY);

                //System.out.println(musicLocation);
                //Thread.sleep(clip.getMicrosecondLength() / 1000);
                //JOptionPane.showMessageDialog(null, " ");

            //} else {
                //System.out.println("File missing");
            //}

        } catch (Exception ex) {
            System.out.println("Error");
            ex.printStackTrace();
        }
    }

    void playSound(String musicLocation) {

        try {

            URL musicPath = getClass().getResource(musicLocation);

            //URL musicURL = getClass().getClassLoader().getResource(musicLocation);
            //if (musicPath.()) {
                AudioInputStream audioInput1 = AudioSystem.getAudioInputStream(musicPath);
                //Clip clip = AudioSystem.getClip();

                //gets audio file location, format and data line info of audiofile
                //AudioInputStream audioInput1 =
                        //AudioSystem.getAudioInputStream(getClass().getResource(musicLocation));
                format = audioInput1.getFormat();
                info = new DataLine.Info(Clip.class, format);

                //stores audio file as a clip to played
                clip2 = (Clip) AudioSystem.getLine(info);

                //AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(getClass().getResource(musicLocation));

                clip2.open(audioInput1);

                //Controls the volume of the audio file
                gainControl = (FloatControl) clip2.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-40.0f);

                clip2.start();
                //clip.loop(Clip.LOOP_CONTINUOUSLY);


                //Thread.sleep(clip.getMicrosecondLength() / 1000);
                //JOptionPane.showMessageDialog(null, " ");
            //} else {
                //System.out.println("File missing");
            //}

        } catch (Exception ex) {
            System.out.println("Error");
            ex.printStackTrace();
        }
    }

    void stopMusic(Clip clip) {


        try {

            clip.stop();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
