package Kinect;

import java.util.*;
import menu.*;
import synchroniseur.*;

public class ImageProcessingUnit implements ImageProcessingInterface {

	private ArrayList<int[][]> matrixList;
	
    private ImageReceptionUnit imageReceptionUnit;
    
    private Menu menu;
	
	public ImageProcessingUnit(ImageReceptionUnit imageReceptionUnit, Menu menu){
        this.imageReceptionUnit = imageReceptionUnit;	
        this.menu = menu;
	};
	
	public void receiveImage(){
	
	};
	
}
