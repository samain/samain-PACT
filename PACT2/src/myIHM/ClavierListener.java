package myIHM;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ClavierListener implements KeyListener {
	private void pause() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void keyPressed(KeyEvent event) {
		System.out.println("Code touche press�e : " + event.getKeyCode()
				+ " - caract�re touche press�e : " + event.getKeyChar());
		pause();
	}

	public void keyReleased(KeyEvent event) {
		System.out.println("Code touche rel�ch�e : " + event.getKeyCode()
				+ " - caract�re touche rel�ch�e : " + event.getKeyChar());
		pause();
	}

	public void keyTyped(KeyEvent event) {
		System.out.println("Code touche tap�e : " + event.getKeyCode()
				+ " - caract�re touche tap�e : " + event.getKeyChar());
		pause();
	}
}
