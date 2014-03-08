package Son;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MP3 extends Thread{  
    private String filename;
    private Player player; 
    private boolean loop;
  
    public MP3(String filename) {  
        this.filename = filename;
        this.loop = true;
    }  
    
    public void run() {  
    	try {
    		do{
    			FileInputStream fs = new FileInputStream(filename);  
    			BufferedInputStream bs = new BufferedInputStream(fs);  
    			player = new Player(bs);
    			player.play();
    		} while (loop);
    		
    	}  
        catch (Exception e) {e.printStackTrace();}  
    }
    
    public void close() { 
    	loop = false;
    	player.close();
    	this.interrupt();
    }
}