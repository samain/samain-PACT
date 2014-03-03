package affichage;

import menu.*;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.*;
import javax.swing.*;
import org.apache.batik.swing.JSVGCanvas;

public class VisualUnit implements TextAndBackgroundInterface {

	private GraphicsDevice screen;
	private JFrame currentFrame;
	private JSVGCanvas currentCanvas;
	
	public VisualUnit() {
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] list = environment.getScreenDevices();
		screen = list[0]; // 0 for screen, 1 for projection
		this.currentCanvas = new JSVGCanvas(null, true, false);
		currentFrame = new JFrame();
	    screen.setFullScreenWindow(currentFrame);
	};
	
	public void addKeyListener(final Menu menu){
		currentFrame.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e){
			    final Menu menu2 = menu;
				int code = e.getKeyCode();
				switch(code){
				case KeyEvent.VK_RIGHT : 
					menu2.transferMovement("right");
					break;
				case KeyEvent.VK_LEFT : 
					menu2.transferMovement("left");
					break;
				case KeyEvent.VK_UP :	
					menu2.transferMovement("up");
					break;
				case KeyEvent.VK_DOWN :	
					menu2.transferMovement("down");
					break;
				case KeyEvent.VK_ENTER :
				menu2.transferMovement("select");
				break;	
				}
				
			}
			
			public void keyTyped(KeyEvent e){
				
			}
			
			public void keyReleased(KeyEvent e){
				
			}
	    }	
	);
		
	}
	
	public void display(JSVGCanvas canvas){
		currentFrame.invalidate();
		currentFrame.getContentPane().remove(currentCanvas);
		currentCanvas = canvas;
		currentFrame.getContentPane().add(currentCanvas);// currentFrame.pack();
		currentFrame.validate();
	}; //affiche le texte et l'ambiance l'accompagnant grâce au(x) projecteur(s).
	
	}

