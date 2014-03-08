package Menu;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import Synchronisation.Synchronizer;


public class Menu{

	private Synchronizer synchronizer;
	
	public Menu (Synchronizer synchronizer){
		this.synchronizer = synchronizer;
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
//---------------------------------------------------------------------------------------------------------	
	public void transferMovement(String movement){
		switch(movement){
		case "select" : System.exit(0);
		                break;
	    default : synchronizer.receiveMouvement(movement);
	              break;
		}
	}
//----------------------------------------------------------------------------------------------------------
	public void close(){
		GlobalScreen.unregisterNativeHook();
	}
	
}
