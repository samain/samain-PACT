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
	
	
	private void transition(AugmentedPage first, AugmentedPage second){
		String text1 = first.getText();
		String text2 = second.getText();
		
		String img1 = completePath("Ressource" + first.getAmbiance());
		String img2 = completePath("Ressource" + first.getAmbiance());
		
		String svgNS = "http://www.w3.org/2000/svg";
		String xlinkNS = "http://www.w3.org/1999/xlink";
		
		// Implement the SVG DOM and create a SVG Document
		DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
		Document doc1 = impl.createDocument(svgNS, "svg", null);
		Document doc2 = impl.createDocument(svgNS, "svg", null);
		
		// Get the root element of the SVG doc and configure it
		Element svgRoot1 = doc1.getDocumentElement();
		svgRoot1.setAttributeNS(null, "width", "1600");
		svgRoot1.setAttributeNS(null, "height", "900");
		
		Element svgRoot2 = doc2.getDocumentElement();
		svgRoot2.setAttributeNS(null, "width", "1600");
		svgRoot2.setAttributeNS(null, "height", "900");
		
		// Add elements
		
		Element imgA = doc1.createElementNS(svgNS, "image");
		imgA.setAttributeNS(xlinkNS, "href", img1);
		imgA.setAttributeNS(null, "x", "0");
		imgA.setAttributeNS(null, "y", "0");
		imgA.setAttributeNS(null, "width", "1600");
		imgA.setAttributeNS(null, "height", "900");
		svgRoot1.appendChild(imgA);
		
		Element rectangleA = doc1.createElementNS(svgNS, "rect");
	    rectangleA.setAttributeNS(null, "x", "600");
	    rectangleA.setAttributeNS(null, "y", "200");
	    rectangleA.setAttributeNS(null, "width", "400");
	    rectangleA.setAttributeNS(null, "height", "500");
	    rectangleA.setAttributeNS(null, "fill", "white");
	    svgRoot1.appendChild(rectangleA);

		Element textA = doc1.createElementNS(svgNS, "text");
		textA.setAttributeNS(null, "x", "50");
		textA.setAttributeNS(null, "y", "150");
		textA.setAttributeNS(null, "font-family", "Arial");
		textA.setAttributeNS(null, "font-size", "40");
		textA.appendChild(doc1.createTextNode(text1));
		svgRoot1.appendChild(textA);
		
		
		Element imgB = doc2.createElementNS(svgNS, "image");
		imgB.setAttributeNS(xlinkNS, "href", img2);
		imgB.setAttributeNS(null, "x", "0");
		imgB.setAttributeNS(null, "y", "0");
		imgB.setAttributeNS(null, "width", "1600");
		imgB.setAttributeNS(null, "height", "900");
		svgRoot2.appendChild(imgB);
		
		Element rectangleB = doc2.createElementNS(svgNS, "rect");
	    rectangleB.setAttributeNS(null, "x", "600");
	    rectangleB.setAttributeNS(null, "y", "200");
	    rectangleB.setAttributeNS(null, "width", "400");
	    rectangleB.setAttributeNS(null, "height", "500");
	    rectangleB.setAttributeNS(null, "fill", "white");
	    svgRoot2.appendChild(rectangleB);

		Element textB = doc2.createElementNS(svgNS, "text");
		textB.setAttributeNS(null, "x", "50");
		textB.setAttributeNS(null, "y", "150");
		textB.setAttributeNS(null, "font-family", "Arial");
		textB.setAttributeNS(null, "font-size", "40");
		textB.appendChild(doc2.createTextNode(text2));
		svgRoot2.appendChild(textB);
	    
		JSVGCanvas c = new JSVGCanvas(null,true,false);
		c.setDocument(doc1);
		visualUnit.display(c);
		
		try {
		    Thread.sleep(1000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	    
	}
	
	private String completePath(String path){
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
     }; 
	
}
