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
	
	private ArrayList<Document> menuScreensList;

	private ArrayList<Document> pagesList;
	
	private int context; //context d'utilisation du logiel (menu ou lecture)
	
	private int shelfNumber; //num�ro d'�tag�re
	
	private int bookNumber; //num�ro de livre
	
	private int font; //police
	
	private boolean isReading; //indique si l'on a bien charg� les trois premi�res pages.
	
	private String[][] stringMap;
	
	public Synchronizer(Classifier classifier, VisualUnit visualUnit, SoundUnit soundUnit){
		this.classifier = classifier;
		this.visualUnit = visualUnit;
		this.soundUnit = soundUnit;
		initialiseMenuDocuments();
		this.context = 0;
		modifyDocument();
	}
	
	//d�cide des actions � faire en fonstion du contexte (menu utilisateur ou bien 
	//lecture d'un livre)
	public void receiveMouvement (String mouvement) {
		switch (context){
		case 0 : 
			switch(mouvement){
			case "right" : break;
			case "left" : break;
			case "up" : break;
			case "down" : break;
			case "selection" :break;
			}
			break;
		case 1 : 
			switch(mouvement){
			case "right" : break;
			case "left" : break;
			case "up" : break;
			case "down" : break;
			case "selection" :break;
			}
			break;
		case 2 : 
			switch(mouvement){
			case "right" : break;
			case "left" : break;
			case "up" : break;
			case "down" : break;
			case "selection" :break;
			}
			break;
		case 3 : 
			switch(mouvement){
			case "right" : break;
			case "left" : break;
			case "up" : break;
			case "down" : break;
			case "selection" :break;
			}
			break;
		}
	};
	
	
	//cr�e les documents svg de chaque �cran de l'interface graphique
	private void initialiseMenuDocuments(){
		
	};
	
	//selon le conntexte, 
	private void modifyDocument(){
		switch(context){
		case 3 : break;
		default :
		Document doc = menuScreensList.get(context);
		
		//faire les modifications du document ici
		
		JSVGCanvas canvas = new JSVGCanvas();
		canvas.setDocument(doc);
		visualUnit.display(canvas);
		break;
		}
	};
	
	private void createPage(){
		
	};
}
