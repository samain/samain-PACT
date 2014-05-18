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

//classe � l'articulation entre l'utilisateur, l'analyse lexicale et les sorties audios et vid�os. 

public class Synchronizer implements SynchronizerInterface  {
	
	private ClassifierInterface classifier;
	private TextAndBackgroundInterface visualUnit;
	private SoundInterface soundUnit;
	private DocumentCreatorInterface documentCreator;
	
     
	//Taille police en String et int
	private int font;
	
	
	//Infos �cran/projecteur
	private int height;
	private int width;
	
	//Adresse complete des ressources
	private String ressourcesAdress = "file:///".concat(reverseSlash(System.getProperty("user.dir")).concat("/Ressources/"));
	
	//OPTIONS EVENTUELLES
	private String fontType;
	
//-----------------------------------------------------------------------------------------------------------------	
	public Synchronizer(){
		this.visualUnit = null;
		
		this.soundUnit = null;
		this.classifier = null;
		this.documentCreator = null;
		
		this.fontType = "Arial";
		
		int[] res = this.getResolution();
		
		this.height = res[1];
		this.width = res[0];
		
		this.classifier = new ClassifierTFIDF();
	}
//------------------------------------------------------------------------------------------------------------------	
	//cr�e les documents svg de chaque �cran de l'interface graphique
	public void initialiseBook(String textURI, int font){
		classifier.setBook(font, textURI);
		this.documentCreator = new DocumentCreator(font, height, width, fontType, ressourcesAdress);
		AugmentedPage aP = createPage("this");
		documentCreator.createDocument(aP);
	    if(soundUnit != null) soundUnit.stop();
	    if(aP.getAmbiance()[1] != null){
	    	soundUnit = new SoundUnit("Ressources\\" + aP.getAmbiance()[1]);
	    }
		this.visualUnit = new VisualUnit();
	}
//--------------------------------------------------------------------------------------------------------------	
	//m�thode d�marrant la lecture du livre correspondant le mieux � l'ambiance choisie par le lecteur
	public void initialiseAtmosphere(String atmosphereURI, int font){		
		classifier.chooseBook(font, atmosphereURI);
		this.documentCreator = new DocumentCreator(font, height, width, fontType, ressourcesAdress);		
		AugmentedPage aP = createPage("this");		
		documentCreator.createDocument(aP);	
		if(soundUnit != null) soundUnit.stop();
		if(aP.getAmbiance()[1] != null){
			soundUnit = new SoundUnit("Ressources\\" + aP.getAmbiance()[1]);
		}
		this.visualUnit = new VisualUnit();
	}
	
	
//---------------------------------------------------------------------------------------------------------------		
	//d�cide des actions � faire en fonstion du contexte (menu utilisateur ou bien 
	//lecture d'un livre)
	public void receiveMouvement (String mouvement) {
	    AugmentedPage aP = null;
		
		switch(mouvement){
		//cas o� le lecteur souhaite revenir d'une page en arri�re
		case "left":
			//si la page lue n'est pas la premi�re
			if (!classifier.isFirst()){
				aP = createPage(mouvement);
				documentCreator.createDocument(aP);
				if(soundUnit != null) soundUnit.stop();
				if(aP.getAmbiance()[1] != null){
					soundUnit = new SoundUnit("Ressources\\" + aP.getAmbiance()[1]);
				}
				visualUnit.display(mouvement);
			}
			
			break;
		
		//si le lecteur souhaite avancer d'une page	
		case "right" :
			//si la page lue n'est pas la derni�re
			if (!classifier.isLast()){	
				aP = createPage(mouvement);	
				documentCreator.createDocument(aP);
				if(soundUnit != null) soundUnit.stop();
				if(aP.getAmbiance()[1] != null){
					soundUnit = new SoundUnit("Ressources\\" + aP.getAmbiance()[1]);
				}
				visualUnit.display(mouvement);
			}
			break;
			
		//si le lecteur souhaite quitter l'application	
		case "select" :
			if(soundUnit != null) soundUnit.stop();
			
			break;
			
		}
		
			
	}
//--------------------------------------------------------------------------------------------------------------
	//re�oit la page demand�e par le lecteur et la place au bon endroit. 
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
	//m�thode renvoyant les dimensions de l'�cran sur lequel le livre est affich�
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
	
//-----------------------------------------------------------------------------------------------------------------------
	//m�thode modifiant la taille de la police utilis�e
	public void setFont(int font){
		this.font = font;
	}
}
