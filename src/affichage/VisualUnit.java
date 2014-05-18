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

public class VisualUnit implements TextAndBackgroundInterface {

	private GraphicsDevice screen;
	private TourneDePage tDP; 
	private final Animator animator;
//----------------------------------------------------------------------------------------------------	
	public VisualUnit() {
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] list = environment.getScreenDevices();
		
		// s'il n'y a qu'un seul �cran, on choisit dd'afficher sur celui-ci
		if (list.length ==1){
			screen = list[0];
		}
		/*sinon, on affiche sur le premier autre que l'�cran, 
		qui est a priori un projecteur*/
		else{
			screen = list[1];
		}
		
		 //initialisation de param�tres de l'affichage
		 GLProfile profile = GLProfile.get(GLProfile.GL2); 
		 GLCapabilities capabilities = new GLCapabilities(profile); 
		 capabilities.setRedBits(8); 
		 capabilities.setBlueBits(8); 
		 capabilities.setGreenBits(8); 
		 capabilities.setAlphaBits(8); 
		 capabilities.setSampleBuffers(true); 
		
		/* on cr�e un canvas auquel on assigne un GLEventListener et un Animator 
		   appelant en permanence la m�thode diplay du GLEventListener */
		final GLCanvas canvas = new GLCanvas();
		animator = new Animator(canvas);
		this.tDP = new TourneDePage();
		canvas.addGLEventListener(tDP);
		
		// on cr�e une nouvelle fen�tre
		final Frame frame = new Frame("ImmersiveReading");
		
		//on ajoute le canvas � frame
		frame.add(canvas);
		
		//on sp�cifie les dimensions de la fen�tre
		frame.setSize(640, 480);
		
		//la fen�tre ne pet �tre redimensionn�e
		frame.setResizable(false);
		
		/*on ajoute un listener permettant de quitter l'application
		 lorsque l'on appuie sur la croix*/
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				animator.stop();
				frame.dispose();
				System.exit(0);
			}
		});
		//on rend la fen�tre visible
		frame.setVisible(true);
		
		//la fen�tre prend la totalit� de l'�cran
	    screen.setFullScreenWindow(frame);
		
	    //on d�marre l'Animator et on appelle une fonction qui acc�l�re les calculs 3D
		animator.start();
		canvas.requestFocus();
	}
//----------------------------------------------------------------------------------------------------
	//m�thode pour afficher la page demand�e par l'utilisateur
	public void display(String direction){
		tDP.turnPage(direction);
	}
}