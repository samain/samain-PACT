package synchroniseur;

import java.util.*;

import augmentedPage.*;
import son.*;
import affichage.*;
import Classification.*;

import java.io.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.dom.*;
import org.apache.batik.dom.svg.*;
import org.w3c.dom.*;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;

public class Synchronizer implements SynchronizerInterface  {
	
	private Classifier classifier;
	
	private VisualUnit visualUnit;
	
	private SoundUnit soundUnit;
	
	// private ArrayList<Document> menuScreensList;
    
    public ArrayList<AugmentedPage> augmentedPageList;
	
	public ArrayList<Document> pagesList;
	
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
		this.resourcesAdress = "file:///".concat(reverseSlash(System.getProperty("user.dir")).concat("/Ressources/"));
		this.screenDimensions = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		this.font = 55;
	}
	
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
	
	
	//crée les documents svg de chaque écran de l'interface graphique
	public void initialiseBook(String book){
                
		        this.augmentedPageList = this.classifier.firstAugmentedPages(book);
         		this.pagesList = new ArrayList<Document>();
		        for(int i = 0; i<3; i++){
         			 Document doc = toDocument(this.augmentedPageList.get((i)));
         			 this.pagesList.add(doc);
         		}
		        JSVGCanvas canvas = new JSVGCanvas(null, true, false);
    		    canvas.setDocument(pagesList.get(1));
    		    visualUnit.display(canvas);
	};
	
	
	public void createPage(String mouvement){
		
		AugmentedPage augmentedPage = this.classifier.sendAugmentedPage(mouvement);
		Document doc = toDocument(augmentedPage);
		System.out.println("création du document effectuée");
		switch(mouvement){
		case "right" : turnRight(augmentedPage, doc);
		               break;
			
		case "left" : turnLeft(augmentedPage, doc);
		              break;
			
		}
		/* System.out.println("changement de page effectué");
		JSVGCanvas canvas = new JSVGCanvas(null, true, false);
	    canvas.setDocument(pagesList.get(1));
	    visualUnit.display(canvas); */ 
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
	     image.setAttributeNS(null, "height", "700px");
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
		svgRoot1.setAttributeNS(null, "width", "1200");
		svgRoot1.setAttributeNS(null, "height", "700");
		
		Element svgRoot2 = doc2.getDocumentElement();
		svgRoot2.setAttributeNS(null, "width", "1200");
		svgRoot2.setAttributeNS(null, "height", "700");
		
		//create SVGGraphics2D
		SVGGraphics2D svgGen1 = new SVGGraphics2D(doc1);
		SVGGraphics2D svgGen2 = new SVGGraphics2D(doc2);
		
		// Add elements
		
		Element imgA = doc1.createElementNS(svgNS, "image");
		imgA.setAttributeNS(xlinkNS, "href", img1);
		imgA.setAttributeNS(null, "x", "0");
		imgA.setAttributeNS(null, "y", "0");
		imgA.setAttributeNS(null, "width", "1200");
		imgA.setAttributeNS(null, "height", "700");
		
		Element animation11 = doc1.createElementNS(svgNS, "animate");
	    animation11.setAttributeNS(null, "attributeType", "CSS");
	    animation11.setAttributeNS(null, "attributeName", "opacity");
	    animation11.setAttributeNS(null, "from", "1");
	    animation11.setAttributeNS(null, "to", "0");
	    animation11.setAttributeNS(null, "dur", "2s");
	    animation11.setAttributeNS(null, "fill", "freeze");
	    
	    imgA.appendChild(animation11);
	    
	    svgRoot1.appendChild(imgA);
		
	    
		/* Element rectangleA = doc1.createElementNS(svgNS, "rect");
	    rectangleA.setAttributeNS(null, "x", "600");
	    rectangleA.setAttributeNS(null, "y", "200");
	    rectangleA.setAttributeNS(null, "width", "400");
	    rectangleA.setAttributeNS(null, "height", "500");
	    rectangleA.setAttributeNS(null, "fill", "white");
	    
	    Element animation12 = doc1.createElementNS(svgNS, "animate");
	    animation12.setAttributeNS(null, "attributeType", "CSS");
	    animation12.setAttributeNS(null, "attributeName", "opacity");
	    animation12.setAttributeNS(null, "from", "1");
	    animation12.setAttributeNS(null, "to", "0");
	    animation12.setAttributeNS(null, "dur", "5s");
	    
	    rectangleA.appendChild(animation12);
	    
	    svgRoot1.appendChild(rectangleA); */

	    
		Element textA = doc1.createElementNS(svgNS, "text");
		textA.setAttributeNS(null, "x", "50");
		textA.setAttributeNS(null, "y", "150");
		textA.setAttributeNS(null, "font-family", "Arial");
		textA.setAttributeNS(null, "font-size", "40");
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
		
		
	 /*	try{
		svgGen1.stream(resourcesAdress.concat("doc1.svg"),true);
		}
		catch(SVGGraphics2DIOException e){
			
		} */
		
		Element imgB = doc2.createElementNS(svgNS, "image");
		imgB.setAttributeNS(xlinkNS, "href", img2);
		imgB.setAttributeNS(null, "x", "0");
		imgB.setAttributeNS(null, "y", "0");
		imgB.setAttributeNS(null, "width", "1200");
		imgB.setAttributeNS(null, "height", "700");
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
		
		/* Element rectangleB = doc2.createElementNS(svgNS, "rect");
	    rectangleB.setAttributeNS(null, "x", "600");
	    rectangleB.setAttributeNS(null, "y", "200");
	    rectangleB.setAttributeNS(null, "width", "400");
	    rectangleB.setAttributeNS(null, "height", "500");
	    rectangleB.setAttributeNS(null, "fill", "white");
	    svgRoot2.appendChild(rectangleB); */

		Element textB = doc2.createElementNS(svgNS, "text");
		textB.setAttributeNS(null, "x", "50");
		textB.setAttributeNS(null, "y", "150");
		textB.setAttributeNS(null, "font-family", "Arial");
		textB.setAttributeNS(null, "font-size", "40");
		textB.appendChild(doc2.createTextNode(text2));
		
		Element animation22 = doc2.createElementNS(svgNS, "animate");
	    animation22.setAttributeNS(null, "attributeType", "CSS");
	    animation22.setAttributeNS(null, "attributeName", "opacity");
	    animation22.setAttributeNS(null, "from", "0");
	    animation22.setAttributeNS(null, "to", "1");
	    animation22.setAttributeNS(null, "dur", "2s");
	    animation22.setAttributeNS(null, "fill", "freeze");
		
	    imgB.appendChild(animation22);
	    
		svgRoot2.appendChild(textB);
		
		
		
	   //  svgRoot2.appendChild(animation2);
	    
		Node importedNode = doc1.importNode(svgRoot2, true);
		svgRoot1.appendChild(importedNode);
		
		/* try{
			svgGen2.stream(resourcesAdress.concat("doc2.svg"),true);
			}
			catch(SVGGraphics2DIOException e){
				
			} */
		
	    
		JSVGCanvas c = new JSVGCanvas(null,true,false);
		c.setDocument(doc1);
		visualUnit.display(c);
		
		try {
		    Thread.sleep(1000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	    
	}
	
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
	 
	 public ArrayList<String> getLines(String text){
		 int lastIndexOfSpace = -1;
		 int lastCut = 0;
		 int length = text.length();
		 ArrayList<String> stringList = new ArrayList<String>();
		 
		 for(int i = 0; i<length; i++){
			 if (text.charAt(i) == ' '){
				 if ((i-lastCut) > 30){
					 if (lastIndexOfSpace<=lastCut){
					 stringList.add(text.substring(lastCut, i));
					 lastCut = i;
					 }
					 else{
				     stringList.add(text.substring(lastCut, lastIndexOfSpace));	 
					 lastCut = lastIndexOfSpace;
					 }
				 }
				 lastIndexOfSpace = i;
			 }
		 }
		 
		 if ((lastCut)<=(length-1)){
			 stringList.add(text.substring(lastCut, length));
		 }
		 
		 return stringList;
	 }
	 
	 public void createText(ArrayList<String> listOfLines, Document doc, Element root, int height, int width){
		 
		 int size = listOfLines.size();
		 String fontSize = (new Integer(this.font)).toString();
				 
		 for(int i = 0; i<size; i++){
			 Element text = doc.createElementNS(svgNS, "text");
			 text.setAttributeNS(null, "x", "100");
			 Integer integerY = new Integer(200 + ((height-100)/size)*i);
			 text.setAttributeNS(null, "y", integerY.toString());
			 text.setAttributeNS(null, "font-family", "Verdana");
			 text.setAttributeNS(null, "font-size", fontSize);
			 text.setAttributeNS(null, "fill", "blue");
			 
			 Text textNode = doc.createTextNode(listOfLines.get(i));
			 text.appendChild(textNode);
			 root.appendChild(text);
		 }
		 
	 }
	
	 private void turnLeft(AugmentedPage page, Document doc){
		 this.augmentedPageList.add(0, page);
		 this.augmentedPageList.remove(3);
		 System.out.println(augmentedPageList.size());
		 this.pagesList.add(0, doc);
		 this.pagesList.remove(3);
		 System.out.println(pagesList.size());
	 }
	 
	 private void turnRight(AugmentedPage page, Document doc){
		 this.augmentedPageList.add(page);
		 this.augmentedPageList.remove(0);
		 System.out.println(augmentedPageList.size());
		 this.pagesList.add(doc);
		 this.pagesList.remove(0);
		 System.out.println(pagesList.size());
	 };
	 
}
