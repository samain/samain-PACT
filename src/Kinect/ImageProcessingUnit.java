package Kinect;

import java.util.*;
import synchroniseur.*;

public class ImageProcessingUnit implements ImageProcessingInterface {

	private ArrayList<int[][]> matrixList;
	
    private ImageReceptionUnit imageReceptionUnit;
    
    private Synchronizer synchronizer;
	
	public ImageProcessingUnit(ImageReceptionUnit imageReceptionUnit, Synchronizer synchronizer){
        this.imageReceptionUnit = imageReceptionUnit;	
        this.synchronizer = synchronizer;
	};
	
	public void receiveImage(){
	
	};
	
}
