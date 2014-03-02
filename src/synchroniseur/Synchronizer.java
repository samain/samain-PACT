package synchroniseur;

import java.util.*;

import augmentedPage.*;
import son.*;
import affichage.*;
import Classification.*;

import java.io.*;
import java.awt.*;
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
	
	private String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
	
	private String xlinkNS = "http://www.w3.org/1999/xlink";
	
	private DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
	
	private String resourcesAdress;
	
	private Rectangle screenDimensions ;
	
	private String[][] stringMap;
	
	public Synchronizer(Classifier classifier, VisualUnit visualUnit, SoundUnit soundUnit){
		this.classifier = classifier;
		this.visualUnit = visualUnit;
		this.soundUnit = soundUnit;
		this.resourcesAdress = "file:///".concat(reverseSlash(System.getProperty("user.dir")).concat("/Ressources"));
		this.screenDimensions = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
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
	private void initialiseBook(String book){
                
		        this.augmentedPageList = this.classifier.firstAugmentedPages(book);
         		
		        for(int i = 0; i<3; i++){
         			 Document doc = toDocument(this.augmentedPageList.get((i)));
         			 this.pagesList.set(i, doc);
         		}
	};
	
	
	private void createPage(String mouvement){
		
		AugmentedPage augmentedPage = this.classifier.sendAugmentedPage(mouvement);
		Document doc = toDocument(augmentedPage);
		switch(mouvement){
		case "right" : turnRight(augmentedPage, doc);
			
		case "left" : turnLeft(augmentedPage, doc);
			
		}
	};
	
	private Document toDocument(AugmentedPage augmentedPage){
		
		Document doc = impl.createDocument(svgNS, "svg", null);
		 Element svgRoot = doc.getDocumentElement();
	     svgRoot.setAttributeNS(null, "width", "1200");
	     svgRoot.setAttributeNS(null, "height", "700");
	     
	     Element image = doc.createElementNS(svgNS, "image");
	     image.setAttributeNS(null, "x", "0");
	     image.setAttributeNS(null, "y", "0");
	     image.setAttributeNS(null, "width", "1200px");
	     image.setAttributeNS(null, "heigth", "700px");
	     image.setAttributeNS(xlinkNS, "xlink:href", resourcesAdress.concat(augmentedPage.getAmbiance()));
	     image.setAttributeNS(null, "visibility", "visible");
	     image.setAttributeNS(null, "opacity", "1");
	     
	     svgRoot.appendChild(image);
	     
	     ArrayList<String> listOfLines = getLines(augmentedPage.getText());
	     
	     createText(listOfLines, doc, svgRoot, 700, 1200);
	     
	     return doc;
		
	}
	
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
	 
	 private ArrayList<String> getLines(String text){
		 int lastIndexOfSpace = -1;
		 int lastCut = 0;
		 int length = text.length();
		 ArrayList<String> stringList = new ArrayList<String>();
		 
		 for(int i = 0; i<length; i++){
			 if (text.charAt(i) == ' '){
				 if ((i-lastCut) > 30){
					stringList.add(text.substring(lastCut, lastIndexOfSpace));
					lastCut = lastIndexOfSpace+1;
					lastIndexOfSpace = i;
				 }
			 }
		 }
		 
		 if ((lastCut)<=(length-1)){
			 stringList.add(text.substring(lastCut, length-1));
		 }
		 
		 return stringList;
	 }
	 
	 private void createText(ArrayList<String> listOfLines, Document doc, Element root, int height, int width){
		 
		 int size = listOfLines.size();
		 String fontSize = (new Integer(this.font)).toString();
				 
		 for(int i = 0; i<size; i++){
			 Element text = doc.createElementNS(svgNS, "image");
			 text.setAttributeNS(null, "x", "100");
			 Integer integerY = new Integer(50 + ((height-100)/size)*i);
			 text.setAttributeNS(null, "y", integerY.toString());
			 text.setAttributeNS(null, "font-size", fontSize);
			 text.setAttributeNS(null, "fill", "black");
			 
			 Text textNode = doc.createTextNode(listOfLines.get(i));
			 text.appendChild(textNode);
			 root.appendChild(text);
		 }
		 
	 }
	
	 private void turnLeft(AugmentedPage page, Document doc){
		 this.augmentedPageList.add(0, page);
		 this.augmentedPageList.remove(3);
		 this.pagesList.add(0, doc);
		 this.pagesList.remove(3);
	 }
	 
	 private void turnRight(AugmentedPage page, Document doc){
		 this.augmentedPageList.add(page);
		 this.augmentedPageList.remove(0);
		 this.pagesList.add(doc);
		 this.pagesList.remove(0);
	 };
	 
}
