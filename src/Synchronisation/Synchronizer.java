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
		case "left" : transition(augmentedPageList.get(2), augmentedPageList.get(1));
		break;
		
		case "right" : transition(augmentedPageList.get(0), augmentedPageList.get(1));
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
	
//-------------------------------------------------------------------------------------------------------------	
	private void transition(AugmentedPage first, AugmentedPage second){
		
		String text1 = first.getText();
		String text2 = second.getText();
		
		String img1 = resourcesAdress.concat(first.getAmbiance());
		String img2 = resourcesAdress.concat(second.getAmbiance());
		
		String svgNS = "http://www.w3.org/2000/svg";
		String xlinkNS = "http://www.w3.org/1999/xlink";
		
		// Implement the SVG DOM and create a SVG Document
		DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
		Document doc1 = impl.createDocument(svgNS, "svg", null);
		Document doc2 = impl.createDocument(svgNS, "svg", null);
		
		// Get the root element of the SVG doc and configure it
		Element svgRoot1 = doc1.getDocumentElement();
		svgRoot1.setAttributeNS(null, "width", widthStr);
		svgRoot1.setAttributeNS(null, "height", heightStr);
		
		Element svgRoot2 = doc2.getDocumentElement();
		svgRoot2.setAttributeNS(null, "width", widthStr);
		svgRoot2.setAttributeNS(null, "height", heightStr);
		
		// Add elements
		
		Element imgA = doc1.createElementNS(svgNS, "image");
		imgA.setAttributeNS(xlinkNS, "href", img1);
		imgA.setAttributeNS(null, "x", "0");
		imgA.setAttributeNS(null, "y", "0");
		imgA.setAttributeNS(null, "width", widthStr);
		imgA.setAttributeNS(null, "height", heightStr);
		
		Element animation11 = doc1.createElementNS(svgNS, "animate");
	    animation11.setAttributeNS(null, "attributeType", "CSS");
	    animation11.setAttributeNS(null, "attributeName", "opacity");
	    animation11.setAttributeNS(null, "from", "1");
	    animation11.setAttributeNS(null, "to", "0");
	    animation11.setAttributeNS(null, "dur", "2s");
	    animation11.setAttributeNS(null, "fill", "freeze");
	    
	    imgA.appendChild(animation11);
	    
	    svgRoot1.appendChild(imgA);
		

	    
		Element textA = doc1.createElementNS(svgNS, "text");
		textA.setAttributeNS(null, "x", "0");
		textA.setAttributeNS(null, "y", "200");
		textA.setAttributeNS(null, "font-family", fontType);
		textA.setAttributeNS(null, "font-size", fontStr);
		textA.appendChild(doc1.createTextNode(text1));
		
		Element animation13 = doc1.createElementNS(svgNS, "animate");
	    animation13.setAttributeNS(null, "attributeType", "CSS");
	    animation13.setAttributeNS(null, "attributeName", "opacity");
	    animation13.setAttributeNS(null, "from", "1");
	    animation13.setAttributeNS(null, "to", "0");
	    animation13.setAttributeNS(null, "dur", "2s");
		animation13.setAttributeNS(null, "fill", "freeze");
		
	    textA.appendChild(animation13);
	    
	    svgRoot1.appendChild(textA);
		
		Element imgB = doc2.createElementNS(svgNS, "image");
		imgB.setAttributeNS(xlinkNS, "href", img2);
		imgB.setAttributeNS(null, "x", "0");
		imgB.setAttributeNS(null, "y", "0");
		imgB.setAttributeNS(null, "width", widthStr);
		imgB.setAttributeNS(null, "height", heightStr);
		imgB.setAttributeNS(null, "opacity", "0");
		
		Element animation21 = doc2.createElementNS(svgNS, "animate");
	    animation21.setAttributeNS(null, "attributeType", "CSS");
	    animation21.setAttributeNS(null, "attributeName", "opacity");
	    animation21.setAttributeNS(null, "from", "0");
	    animation21.setAttributeNS(null, "to", "1");
	    animation21.setAttributeNS(null, "dur", "2s");
		animation21.setAttributeNS(null, "fill", "freeze");
		
	    imgB.appendChild(animation21);
	    
		svgRoot2.appendChild(imgB);

		Element textB = doc2.createElementNS(svgNS, "text");
		textB.setAttributeNS(null, "x", "0");
		textB.setAttributeNS(null, "y", "200");
		textB.setAttributeNS(null, "font-family", fontType);
		textB.setAttributeNS(null, "font-size", fontStr);
		textB.setAttributeNS(null, "opacity", "0");
		textB.appendChild(doc2.createTextNode(text2));
		
		Element animation22 = doc2.createElementNS(svgNS, "animate");
	    animation22.setAttributeNS(null, "attributeType", "CSS");
	    animation22.setAttributeNS(null, "attributeName", "opacity");
	    animation22.setAttributeNS(null, "from", "0");
	    animation22.setAttributeNS(null, "to", "1");
	    animation22.setAttributeNS(null, "dur", "2s");
	    animation22.setAttributeNS(null, "fill", "freeze");
		
	    textB.appendChild(animation22);
	    
		svgRoot2.appendChild(textB);
	    
		Node importedNode = doc1.importNode(svgRoot2, true);
		svgRoot1.appendChild(importedNode);
		
	    
		JSVGCanvas c = new JSVGCanvas(null,true,false);
		c.setDocument(doc1);
		visualUnit.display(c);

	    
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
