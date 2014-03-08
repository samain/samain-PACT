package Affichage;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.*;
import org.apache.batik.swing.JSVGCanvas;

public class VisualUnit implements TextAndBackgroundInterface {

	private GraphicsDevice screen;
	private JFrame Frame;
	private JSVGCanvas currentCanvas;
	
	public VisualUnit() {
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] list = environment.getScreenDevices();
		screen = list[0]; // 0 for screen, 1 for projection
		this.currentCanvas = new JSVGCanvas(null, true, false);
		Frame = new JFrame();
		Frame.setUndecorated(true);
		Frame.setResizable(false);
	    screen.setFullScreenWindow(Frame);
	}
	
	public void display(JSVGCanvas canvas){
		Frame.invalidate();
		Frame.getContentPane().remove(currentCanvas);
		currentCanvas = canvas;
		Frame.getContentPane().add(currentCanvas);// currentFrame.pack();
		Frame.validate();
	}

	public int[] getResolution() {
		int[] res = {screen.getDisplayMode().getWidth(), screen.getDisplayMode().getHeight()};
		return res;
	}
	
}

