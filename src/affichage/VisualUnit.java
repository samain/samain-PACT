package affichage;

import java.awt.image.BufferedImage;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import java.awt.*;
import java.io.*;
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
	public VisualUnit(InputStream in) {
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
		final Frame frame = new Frame("ImmersiveReading");
		animator = new Animator(canvas);
		this.tDP = new TourneDePage(in);
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
	public void display(InputStream in, String direction){
		tDP.turnPage(in, direction);
	}
}