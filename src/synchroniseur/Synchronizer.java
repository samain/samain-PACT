package synchroniseur;

import java.util.*;

import augmentedPage.*;
import son.*;
import affichage.*;
import Classification.*;

import java.io.*;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.apache.batik.swing.JSVGCanvas;

import org.apache.batik.dom.*;
import org.apache.batik.dom.svg.*;
import org.w3c.dom.*;

public class Synchronizer implements SynchronizerInterface  {
	
	private Classifier classifier;
	
	private VisualUnit visualUnit;
	
	private SoundUnit soundUnit;
	
	// private ArrayList<Document> menuScreensList;
    
	private ArrayList<AugmentedPage> augmentedPageList;
	
	private ArrayList<Document> pagesList;
	
	private int font; //police
	
	private String[][] stringMap;
	
	public Synchronizer(Classifier classifier, VisualUnit visualUnit, SoundUnit soundUnit){
		this.classifier = classifier;
		this.visualUnit = visualUnit;
		this.soundUnit = soundUnit;
	}
	
	//décide des actions à faire en fonstion du contexte (menu utilisateur ou bien 
	//lecture d'un livre)
	public void receiveMouvement (String mouvement) {
		switch (mouvement){
			case "right" : break;
			case "left" : break;
			}
	};
	
	
	//crée les documents svg de chaque écran de l'interface graphique
	private void initialiseBook(){
		
	};
	
	
	private void createPage(){
		
	};
	
	 private String reverseSlash(String path){
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
