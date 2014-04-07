package affichage;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import java.awt.*;

import java.awt.event.*;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;


import com.jogamp.opengl.util.Animator;


import affichage.TourneDePage;

public class VisualUnit {

	private GraphicsDevice screen;
	private TourneDePage tDP; 
	private final Animator animator;
//----------------------------------------------------------------------------------------------------	
	public VisualUnit() {
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] list = environment.getScreenDevices();
		
		if (list.length ==1){
			screen = list[0]; // 0 for screen, 1 for projection
		}
		else{
			screen = list[1];
		}
		
		GLProfile profile = GLProfile.get(GLProfile.GL2); 
		 GLCapabilities capabilities = new GLCapabilities(profile); 
		 capabilities.setRedBits(8); 
		 capabilities.setBlueBits(8); 
		 capabilities.setGreenBits(8); 
		 capabilities.setAlphaBits(8); 
		 capabilities.setSampleBuffers(true); 
		
		final GLCanvas canvas = new GLCanvas();
		final Frame frame = new Frame("Jogl Quad drawing");
		animator = new Animator(canvas);
		this.tDP = new TourneDePage();
		canvas.addGLEventListener(tDP);
		frame.add(canvas);
		frame.setSize(640, 480);
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				animator.stop();
				frame.dispose();
				System.exit(0);
			}
		});
		frame.setVisible(true);
		frame.setResizable(false);
	    screen.setFullScreenWindow(frame);
		
		animator.start();
		canvas.requestFocus();
	}
//----------------------------------------------------------------------------------------------------
	//méthode pour afficher le contenu d'un canvas.
	public void display(String direction){
		tDP.turnPage(direction);
	}
}