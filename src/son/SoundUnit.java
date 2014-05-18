package son;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

// classe s'occupant de diffuser le son 

public class SoundUnit implements SoundInterface {
	private String filename;
    private Player player;
    private boolean looping;
//-------------------------------------------------------------------------------------
    //constructeur de la classe SounUnit
    public SoundUnit(String filename) {
    	this.looping = true;
    	this.filename = filename;
    	play();
    }
//--------------------------------------------------------------------------------------    
    //méthode arrêtant la diffusion de l'extrait sonore joué
    public void stop() { 
    	looping = false;
    	if (player != null) player.close(); 
    }
//--------------------------------------------------------------------------------------
    //méthode jouant un extrait sonore
    public void play() {
        try {
            FileInputStream fis     = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        }
        catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }

        new Thread() {
            public void run() {
            	try { 
                	while(looping){player.play(); }
                }
                catch (Exception e) { System.out.println(e); }
            }
        }.start();
    }
}
