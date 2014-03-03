package menu;

import synchroniseur.*;


public class Menu {

	private Synchronizer synchronizer;
	
	public Menu (Synchronizer synchronizer){
		this.synchronizer = synchronizer;
	}
	
	public void transferMovement(String movement){
		switch(movement){
		case "select" : System.exit(0);
		                break;
	    default : synchronizer.receiveMouvement(movement);
	              break;
		}
	}
	
	
}
