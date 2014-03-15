package Synchronisation;

import java.util.*;

import affichage.VisualUnit;
import augmentedPage.AugmentedPage;
import Classification.*;
import son.SoundUnit;

import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.*;

//classe à l'articulation entre l'utilisateur, l'analyse lexicale et les sorties audios et vidéos. 

public class Synchronizer implements SynchronizerInterface  {
	
	private Classifier classifier;
	private VisualUnit visualUnit;
	private SoundUnit soundUnit;
	private DocumentCreator documentCreator;
	
     //Buffers de pages
    public ArrayList<AugmentedPage> augmentedPageList;
	
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
		this.visualUnit = new VisualUnit();
		
//		this.soundUnit = new SoundUnit();
		this.soundUnit = null;
		this.classifier = null;
		this.documentCreator = null;
		
		this.fontType = "Arial";
		
		this.height = visualUnit.getResolution()[1];
		this.width = visualUnit.getResolution()[0];
		
		this.augmentedPageList = null;
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
		
		this.augmentedPageList = this.classifier.firstAugmentedPages();
		
		Document doc = documentCreator.createDocument(this.augmentedPageList.get(1));
		
		JSVGCanvas canvas = new JSVGCanvas(null, true, false);
		canvas.setDocument(doc);
		menuActive = false;
		if(soundUnit != null) soundUnit.stop();
		if(augmentedPageList.get(1).getAmbiance()[1] != null) soundUnit = new SoundUnit("Ressources\\" + augmentedPageList.get(1).getAmbiance()[1]);

		visualUnit.display(canvas);
	}
//---------------------------------------------------------------------------------------------------------------		
	//décide des actions à faire en fonstion du contexte (menu utilisateur ou bien 
	//lecture d'un livre)
	public void receiveMouvement (String mouvement) {
		createPage(mouvement);
		Document doc = null;
		JSVGCanvas canvas = new JSVGCanvas(null, true, false);
		
		switch(mouvement){
		case "left":
			if ((augmentedPageList.get(2).getText() !=  "")&&(augmentedPageList.get(1).getText() !=  "")){
			doc = documentCreator.createDocument(augmentedPageList.get(2), augmentedPageList.get(1));
			canvas.setDocument(doc);
			menuActive = false;
			if(soundUnit != null) soundUnit.stop();
			if(augmentedPageList.get(1).getAmbiance()[1] != null) soundUnit = new SoundUnit("Ressources\\" + augmentedPageList.get(1).getAmbiance()[1]);
			visualUnit.display(canvas);
			}
			break;
		
		case "right" :
			if ((augmentedPageList.get(0).getText() !=  "")&&(augmentedPageList.get(1).getText() !=  "")){
			doc = documentCreator.createDocument(augmentedPageList.get(0), augmentedPageList.get(1));
			canvas.setDocument(doc);
			menuActive = false;
			if(soundUnit != null) soundUnit.stop();
			if(augmentedPageList.get(1).getAmbiance()[1] != null) soundUnit = new SoundUnit("Ressources\\" + augmentedPageList.get(1).getAmbiance()[1]);
			visualUnit.display(canvas);
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
	public void createPage(String mouvement){
		
		AugmentedPage augmentedPage = this.classifier.sendAugmentedPage(mouvement);
		switch(mouvement) {
			case "right" : 
				this.augmentedPageList.add(augmentedPage);
				this.augmentedPageList.remove(0);
				break;
			
			case "left" : 
				this.augmentedPageList.add(0, augmentedPage);
				this.augmentedPageList.remove(3);
				break;			
		}
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
}
