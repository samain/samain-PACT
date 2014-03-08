package Synchronisation;

import java.util.*;

import Affichage.VisualUnit;
import AugmentedPage.AugmentedPage;
import Classification.*;
import Son.SoundUnit;

import java.awt.*;

import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.dom.svg.*;
import org.w3c.dom.*;

public class Synchronizer implements SynchronizerInterface  {
	
	private Classifier classifier;
	private VisualUnit visualUnit;
	private SoundUnit soundUnit;
	private DocumentCreator documentCreator;
	
     //Buffers de pages
    public ArrayList<AugmentedPage> augmentedPageList;
	public ArrayList<Document> pagesList;
	
	//Taille police en String et int
	private String fontStr; //police
	private int font;
	
	//Livre en cours de lecture
	private String textURI;
	
	//Infos écran/projecteur
	private int height;
	private int width;
	
	//Mode d'affichage
	// false - affichage d'un livre en plein écran
	// true - affichage du menu
	private boolean menuActive;
	
	//OPTIONS EVENTUELLES
	private String fontType;
//------------------------------------------------------------------------------------------------------------	
	public Synchronizer(){
		this.visualUnit = new VisualUnit();
		this.soundUnit = new SoundUnit();
		this.classifier = null;
		this.documentCreator = null;
		
		this.fontType = "Arial";
		
		this.height = visualUnit.getResolution()[1];
		this.width = visualUnit.getResolution()[0];
		
		this.augmentedPageList = null;
		this.pagesList = null;
		this.font = 0;
		this.textURI = null;
		this.menuActive=true;
	}
//-------------------------------------------------------------------------------------------------------------	
	//crée les documents svg de chaque écran de l'interface graphique
	public void initialiseBook(String textURI, int font){
		this.font = font;
		this.textURI = textURI;
		this.classifier = new Classifier(textURI, font);
		this.documentCreator = new DocumentCreator(font, height, width, fontType);
		
		this.augmentedPageList = this.classifier.firstAugmentedPages();
		this.pagesList = new ArrayList<Document>();
		for(int i = 0; i<3; i++){
			Document doc = documentCreator.toDocument(this.augmentedPageList.get((i)));
			this.pagesList.add(doc);
		}
		JSVGCanvas canvas = new JSVGCanvas(null, true, false);
		canvas.setDocument(pagesList.get(1));
		menuActive = false;
		visualUnit.display(canvas);
	}
//-------------------------------------------------------------------------------------------------------------		
	//décide des actions à faire en fonstion du contexte (menu utilisateur ou bien 
	//lecture d'un livre)
	public void receiveMouvement (String mouvement) {
		createPage(mouvement);
		switch(mouvement){
		case "left":
		documentCreator.transition(augmentedPageList.get(2), augmentedPageList.get(1));
		break;
		
		case "right" :
		documentCreator.transition(augmentedPageList.get(0), augmentedPageList.get(1));
		break;
		}
			/* JSVGCanvas canvas = new JSVGCanvas(null, true, false);
		    canvas.setDocument(pagesList.get(1));
		    visualUnit.display(canvas); */ 
	}
//-------------------------------------------------------------------------------------------------------------	
	public void createPage(String mouvement){
		
		AugmentedPage augmentedPage = this.classifier.sendAugmentedPage(mouvement);
		Document doc = documentCreator.toDocument(augmentedPage);
		switch(mouvement) {
			case "right" : turnRight(augmentedPage, doc);
			break;
			
			case "left" : turnLeft(augmentedPage, doc);
			break;			
		}
	}
//--------------------------------------------------------------------------------------------------		
	 private void turnLeft(AugmentedPage page, Document doc){
		 this.augmentedPageList.add(0, page);
		 this.augmentedPageList.remove(3);
		 System.out.println(augmentedPageList.size());
		 this.pagesList.add(0, doc);
		 this.pagesList.remove(3);
		 System.out.println(pagesList.size());
	 }
//--------------------------------------------------------------------------------------------------	 
	 private void turnRight(AugmentedPage page, Document doc){
		 this.augmentedPageList.add(page);
		 this.augmentedPageList.remove(0);
		 System.out.println(augmentedPageList.size());
		 this.pagesList.add(doc);
		 this.pagesList.remove(0);
		 System.out.println(pagesList.size());
	 }	 
}
