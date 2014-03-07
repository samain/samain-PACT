import affichage.*;
import Classification.*;
import decoupage.*;
import Kinect.*;
import menu.*;
import son.*;
import synchroniseur.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       VisualUnit visualUnit = new VisualUnit();
       SoundUnit soundUnit = new SoundUnit();
       PageMaker pageMaker = new PageMaker();
       Classifier2 classifier =  new Classifier2(pageMaker);
       Synchronizer synchronizer = new Synchronizer(classifier, visualUnit, soundUnit, 40, "Arial", false, 0);
       final Menu menu = new Menu(synchronizer);
       visualUnit.addKeyListener(menu);
       ImageReceptionUnit receptionUnit = new ImageReceptionUnit();
       ImageProcessingUnit processingUnit = new ImageProcessingUnit(receptionUnit, menu);
       
       while(true){
    	   processingUnit.receiveImage();
       }
	}

}
