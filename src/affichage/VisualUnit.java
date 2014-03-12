package Affichage;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.*;

import org.apache.batik.swing.JSVGCanvas;

public class VisualUnit implements TextAndBackgroundInterface {

	private GraphicsDevice screen;
	private JFrame frame;
	private JSVGCanvas currentCanvas;
//----------------------------------------------------------------------------------------------------	
	public VisualUnit() {
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] list = environment.getScreenDevices();
		screen = list[0]; // 0 for screen, 1 for projection
		this.currentCanvas = new JSVGCanvas(null, true, false);
		currentCanvas.setDisableInteractions(true);
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setResizable(false);
	    screen.setFullScreenWindow(frame);
	    frame.setVisible(false);
	}
//----------------------------------------------------------------------------------------------------
	//méthode pour afficher le contenu d'un canvas.
	public void display(JSVGCanvas canvas){
		frame.invalidate();
		frame.getContentPane().remove(currentCanvas);
		currentCanvas = canvas;
		canvas.setDisableInteractions(true);
		frame.getContentPane().add(currentCanvas);
		frame.validate();
		frame.setVisible(true);
	}
//----------------------------------------------------------------------------------------------------
	public int[] getResolution() {
		int[] res = {screen.getDisplayMode().getWidth(), screen.getDisplayMode().getHeight()};
		return res;
	}
}

