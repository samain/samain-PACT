package Synchronisation;

import java.awt.image.BufferedImage;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.*;

import java.io.*;

import affichage.*;
import augmentedPage.AugmentedPage;
import Classification.*;
import son.*;

import org.w3c.dom.*;

//classe à l'articulation entre l'utilisateur, l'analyse lexicale et les sorties audios et vidéos. 

public class Synchronizer implements SynchronizerInterface  {
	
	private ClassifierInterface classifier;
	private VisualUnit visualUnit;
	private SoundInterface soundUnit;
	private DocumentCreatorInterface documentCreator;
	
     
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
	
	//Adresse complete des ressources
	private String ressourcesAdress = "file:///".concat(reverseSlash(System.getProperty("user.dir")).concat("/Ressources/"));
	
	//OPTIONS EVENTUELLES
	private String fontType;
	
//-----------------------------------------------------------------------------------------------------------------	
	public Synchronizer(){
		this.visualUnit = null;
		
//		this.soundUnit = new SoundUnit();
		this.soundUnit = null;
		this.classifier = null;
		this.documentCreator = null;
		
		this.fontType = "Arial";
		
		int[] res = this.getResolution();
		
		this.height = res[1];
		this.width = res[0];
		
	//	this.augmentedPageList = null;
		this.font = 0;
		this.textURI = null;
		this.menuActive=true;
	}
//------------------------------------------------------------------------------------------------------------------	
	//crée les documents svg de chaque écran de l'interface graphique
	public void initialiseBook(String textURI, int font){
		this.font = font;
		this.textURI = textURI;
		this.classifier = new Classifier(textURI, font);
		this.documentCreator = new DocumentCreator(font, height, width, fontType, ressourcesAdress);
		
	//	this.augmentedPageList = this.classifier.firstAugmentedPages();
		
		AugmentedPage aP = createPage("this");
		
		InputStream in = documentCreator.createDocument(aP);
		
	//	JSVGCanvas canvas = new JSVGCanvas(null, true, false);
	//	canvas.setDocument(doc);
 		menuActive = false;
		if(soundUnit != null) soundUnit.stop();
		if(aP.getAmbiance()[1] != null) soundUnit = new SoundUnit("Ressources\\" + aP.getAmbiance()[1]);

		this.visualUnit = new VisualUnit(in);
	}
//---------------------------------------------------------------------------------------------------------------		
	//décide des actions à faire en fonstion du contexte (menu utilisateur ou bien 
	//lecture d'un livre)
	public void receiveMouvement (String mouvement) {
	    AugmentedPage aP = null;
		
		switch(mouvement){
		case "left":
			if (!classifier.isFirst()){
			aP = createPage(mouvement);
			InputStream in = documentCreator.createDocument(aP);
			menuActive = false;
			if(soundUnit != null) soundUnit.stop();
			if(aP.getAmbiance()[1] != null) soundUnit = new SoundUnit("Ressources\\" + aP.getAmbiance()[1]);
			visualUnit.display(in, mouvement);
			}
			break;
		
		case "right" :
			if (!classifier.isLast()){	
			aP = createPage(mouvement);	
			InputStream in = documentCreator.createDocument(aP);
			menuActive = false;
			if(soundUnit != null) soundUnit.stop();
			if(aP.getAmbiance()[1] != null) soundUnit = new SoundUnit("Ressources\\" + aP.getAmbiance()[1]);
			visualUnit.display(in, mouvement);
			}
			break;
			
		case "select" :
			menuActive = true;
			if(soundUnit != null) soundUnit.stop();
			break;
			
		}
		
			
	}
//--------------------------------------------------------------------------------------------------------------
	//reçoit la page demandée par le lecteur et la place au bon endroit. 
	public AugmentedPage createPage(String mouvement){
		return this.classifier.sendAugmentedPage(mouvement);
	}
//-----------------------------------------------------------------------------------------------------------------	
	// renverse les slash dans une adresse uri.
	public String reverseSlash(String path){
		int length = path.length();
		if (System.getProperty("os.name").startsWith("Windows")){
			char[] array = path.toCharArray();
			for(int i = 0; i<length; i++){
				if(array[i] == '\\'){
					array[i] = '/';
				}
			}
			path = String.valueOf(array);
		}
		return path;
	}
	
//--------------------------------------------------------------------------------------------------------------------	
	public int[] getResolution() {
		
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] list = environment.getScreenDevices();
		GraphicsDevice screen = null;
		if (list.length == 1){
		screen = list[0];
		}
		else{
		screen = list[1];	
		}
			
		int[] res = {screen.getDisplayMode().getWidth(), screen.getDisplayMode().getHeight()};
		return res;
	}
}
