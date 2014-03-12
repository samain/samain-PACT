package Son;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

// classe s'occupant de diffuser le son 

public class SoundUnit implements SoundInterface {
	private String filename;
    private Player player; 

    public SoundUnit(String filename) {
        this.filename = filename;
        play();
    }

    public void stop() { if (player != null) player.close(); }

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
                try { player.play(); }
                catch (Exception e) { System.out.println(e); }
            }
        }.start();
    }

	public void playSound(String uri) {
		
	}
}
