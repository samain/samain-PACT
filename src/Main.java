import affichage.*;
import augmentedPage.*;
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
       Classifier classifier =  new Classifier(pageMaker);
       Synchronizer synchronizer = new Synchronizer(classifier, visualUnit, soundUnit);
       Menu menu = new Menu(synchronizer);
       ImageReceptionUnit receptionUnit = new ImageReceptionUnit();
       ImageProcessingUnit processingUnit = new ImageProcessingUnit(receptionUnit, menu);
       
       while(true){
    	   processingUnit.receiveImage();
       }
	}

}
