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
		System.out.println("Code touche pressée : " + event.getKeyCode()
				+ " - caractère touche pressée : " + event.getKeyChar());
		pause();
	}

	public void keyReleased(KeyEvent event) {
		System.out.println("Code touche relâchée : " + event.getKeyCode()
				+ " - caractère touche relâchée : " + event.getKeyChar());
		pause();
	}

	public void keyTyped(KeyEvent event) {
		System.out.println("Code touche tapée : " + event.getKeyCode()
				+ " - caractère touche tapée : " + event.getKeyChar());
		pause();
	}
}
