package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sound {
    //Esto se ocupa para abrir el archivo de sonido
    Clip clip;
    ArrayList<String> sounds = new ArrayList<String>();

    public Sound(){
        sounds.add("././././res/sound/musicmain.wav"); //0
        sounds.add("././././res/sound/coin.wav");//1
        sounds.add("././././res/sound/powerup.wav");//2
        sounds.add("././././res/sound/unlock.wav");//3
        sounds.add("././././res/sound/fanfare.wav");//4
    }
/*     public Sound(){
            
        soundURL[0] = AudioInputStream("/Generation/Juego Java/res/sound/musicmain.wav");
        soundURL[1] =  this.getClass().getClassLoader().getResource("/Generation/Juego Java/res/sound/coin.wav");
        soundURL[2] =  this.getClass().getClassLoader().getResource("/Generation/Juego Java/res/sound/powerup.wav");
        soundURL[3] =  this.getClass().getClassLoader().getResource("/Generation/Juego Java/res/sound/unlock.wav");
        soundURL[4] =  this.getClass().getClassLoader().getResource("/Generation/Juego Java/res/sound/fanfare.wav");
    } */
    
    public void setFile(int i){
        try{
            AudioInputStream ais  = AudioSystem.getAudioInputStream(new File (sounds.get(i)).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
        }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }
}
