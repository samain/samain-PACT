package affichage;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.*;
import org.apache.batik.swing.JSVGCanvas;

public class VisualUnit implements TextAndBackgroundInterface {

	GraphicsDevice screen;
	JFrame currentFrame;
	JSVGCanvas currentCanvas;
	
	public VisualUnit() {
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] list = environment.getScreenDevices();
		screen = list[1];
		currentFrame = new JFrame();	
		screen.setFullScreenWindow(currentFrame);
	};
	
	public void display(JSVGCanvas canvas){
		currentFrame.invalidate();
		currentFrame.getContentPane().remove(currentCanvas);
		currentCanvas = canvas;
		currentFrame.getContentPane().add(currentCanvas);
		currentFrame.validate();
	}; //affiche le texte et l'ambiance l'accompagnant grâce au(x) projecteur(s).
	
	}

