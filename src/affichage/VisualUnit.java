package Affichage;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.*;
import org.apache.batik.swing.JSVGCanvas;

public class VisualUnit implements TextAndBackgroundInterface {

	private GraphicsDevice screen;
	private JFrame frame;
	private JSVGCanvas currentCanvas;
	
	public VisualUnit() {
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] list = environment.getScreenDevices();
		screen = list[0]; // 0 for screen, 1 for projection
		this.currentCanvas = new JSVGCanvas(null, true, false);
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setResizable(false);
	    screen.setFullScreenWindow(frame);
	}
	
	public void display(JSVGCanvas canvas){
		frame.invalidate();
		frame.getContentPane().remove(currentCanvas);
		currentCanvas = canvas;
		frame.getContentPane().add(currentCanvas);// currentframe.pack();
		frame.validate();
		frame.setVisible(true);
	}
	
	public void setToBackground(boolean b) {
		frame.setVisible(b);
	}

	public int[] getResolution() {
		int[] res = {screen.getDisplayMode().getWidth(), screen.getDisplayMode().getHeight()};
		return res;
	}
	
}

