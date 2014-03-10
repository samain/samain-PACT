package Menu;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import Synchronisation.Synchronizer;


public class Menu{

	static Synchronizer synchronizer;
	
	public Menu (){
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
		
		Menu.synchronizer = new Synchronizer();
		
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException e) {
			System.err.println("There was a problem registering the native hook.");
			e.printStackTrace();
			System.exit(1);
		}
		GlobalScreen.getInstance().addNativeKeyListener(new NativeKeyListener(){
			public void nativeKeyReleased(NativeKeyEvent e) {
				int code = e.getKeyCode();
				switch(code){
				case NativeKeyEvent.VK_RIGHT : 
					transferMovement("right");
					break;
				case NativeKeyEvent.VK_LEFT : 
					transferMovement("left");
					break;
				case NativeKeyEvent.VK_UP :	
					transferMovement("up");
					break;
				case NativeKeyEvent.VK_DOWN :	
					transferMovement("down");
					break;
				case NativeKeyEvent.VK_ENTER :
					transferMovement("select");
				break;
				}
				
			}

			@Override
			public void nativeKeyPressed(NativeKeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void nativeKeyTyped(NativeKeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
//----------------------------------------------------------------------------------------------------------	
	public void transferMovement(String movement){
		switch(movement){
		case "select" :
			synchronizer.receiveMouvement(movement);
//			System.exit(0);
			break;
	    default : 
	    	synchronizer.receiveMouvement(movement);
	    	break;
		}
	}
//----------------------------------------------------------------------------------------------------------
	public void close(){
		GlobalScreen.unregisterNativeHook();
	}
//----------------------------------------------------------------------------------------------------------
	private static void createAndShowGUI() {
		// On fabrique et on met en forme une fenêtre JFrame
		JFrame principalFrame = new JFrame("Immersive Reading");
		
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] list = environment.getScreenDevices();
		GraphicsDevice screen = list[0]; // 0 for screen, 1 for projection
		
		principalFrame.setSize(screen.getDisplayMode().getWidth(), screen.getDisplayMode().getHeight());
		principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On fabrique le contentPane
		Fenetreclayout nappe = new Fenetreclayout();
		nappe.addComponentToPane(principalFrame.getContentPane());
		
		principalFrame.setUndecorated(true);
		principalFrame.setResizable(false);
	    screen.setFullScreenWindow(principalFrame);
		
		principalFrame.setVisible(true);
	}
}
