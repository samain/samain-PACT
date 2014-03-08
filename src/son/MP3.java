package Son;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MP3 {  
    private String filename;  
    private Player player;  
  
    public MP3(String filename) {  
        this.filename = filename;  
    }  
  
    public void play() {  
        try {  
            FileInputStream fs = new FileInputStream(filename);  
            BufferedInputStream bs = new BufferedInputStream(fs);  
            player = new Player(bs);  
            player.play();  
        }  
        catch (Exception e) {   
            e.printStackTrace();
        }  
          
    }  
}