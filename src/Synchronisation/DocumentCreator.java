package Synchronisation;

import java.util.ArrayList;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import AugmentedPage.AugmentedPage;

public class DocumentCreator {
	private LinesGetter linesGetter;
	
	//Police de caractère
	private int font;
	private String fontStr;
	private String fontType;
	
	//Dimension ecran
	private int height;
	private int width;
	private String heightStr;
	private String widthStr;
	
	//Constantes Batik
	private String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
	private String xlinkNS = "http://www.w3.org/1999/xlink";
	private DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
	
	//Adresse complete des ressources
	private String ressourcesAdress;

//------------------------------------------------------------------------------------------------------------------
	public DocumentCreator(int font, int height, int width, String fontType, String ressourcesAdress){
		this.linesGetter = new LinesGetter();
		
		this.font = font;
		this.height = height;
		this.width = width;
		this.fontType = fontType;
		this.ressourcesAdress = ressourcesAdress;
		
		this.fontStr = ((Integer)font).toString();
		this.heightStr = ((Integer)height).toString();
		this.widthStr = ((Integer)width).toString();
	}
//-----------------------------------------------------------------------------------------------------------------------	
	public Document createDocument(AugmentedPage augmentedPage){
		
		 Document doc = impl.createDocument(svgNS, "svg", null);
		 Element svgRoot = doc.getDocumentElement();
	     svgRoot.setAttributeNS(null, "width", widthStr);
	     svgRoot.setAttributeNS(null, "height", heightStr);
	    
	     Element image = doc.createElementNS(svgNS, "image");
	     image.setAttributeNS(null, "x", "0");
	     image.setAttributeNS(null, "y", "0");
	     image.setAttributeNS(null, "width", widthStr);
	     image.setAttributeNS(null, "height", heightStr);
	     image.setAttributeNS(xlinkNS, "xlink:href", ressourcesAdress.concat(augmentedPage.getAmbiance()[0]));
	     image.setAttributeNS(null, "visibility", "visible");
	     image.setAttributeNS(null, "opacity", "1");
	     
	     svgRoot.appendChild(image);
	     
	     ArrayList<String> listOfLines = linesGetter.getLines(augmentedPage.getText());
	     
	     int size = listOfLines.size();
	     
	     for(int i = 0; i<size; i++){
	    	 Element text = doc.createElementNS(svgNS, "text");
	    	 text.setAttributeNS(null, "x", "5");
	    	 Integer integerY = new Integer((height/(size+1))*(i+1));
	    	 text.setAttributeNS(null, "y", integerY.toString());
	    	 text.setAttributeNS(null, "font-family", fontType);
	    	 text.setAttributeNS(null, "font-size", fontStr);
	    	 text.setAttributeNS(null, "fill", "white");
	    	 text.setAttributeNS(null, "stroke", "black");
	    	 text.setAttributeNS(null, "stroke-width", "1px");
	    	 
	    	 Text textNode = doc.createTextNode(listOfLines.get(i));
	    	 text.appendChild(textNode);
	    	 svgRoot.appendChild(text);
	     } 
	     return doc;
		
	}
	
//-------------------------------------------------------------------------------------------------------------	
	public Document createDocument(AugmentedPage first, AugmentedPage second){
			
		String text1 = first.getText();
		String text2 = second.getText();
		
		System.out.println("texte doc prec = " + text1);
		System.out.println("texte doc suiv = " + text2);
			
		String img1 = ressourcesAdress.concat(first.getAmbiance()[0]);
		String img2 = ressourcesAdress.concat(second.getAmbiance()[0]);
			
		// Implement the SVG DOM and create a SVG Document
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
			  
	    ArrayList<String> listOfLines = linesGetter.getLines(first.getText());
	     
	     int size = listOfLines.size();
	     
	     for(int i = 0; i<size; i++){
	    	 Element text = doc1.createElementNS(svgNS, "text");
	    	 text.setAttributeNS(null, "x", "5");
	    	 Integer integerY = new Integer((height/(size+1))*(i+1));
	    	 text.setAttributeNS(null, "y", integerY.toString());
	    	 text.setAttributeNS(null, "font-family", fontType);
	    	 text.setAttributeNS(null, "font-size", fontStr);
	    	 text.setAttributeNS(null, "fill", "white");
	    	 text.setAttributeNS(null, "stroke", "black");
	    	 text.setAttributeNS(null, "stroke-width", "1px");
	    	 Text textNode = doc1.createTextNode(listOfLines.get(i));
	    	 text.appendChild(textNode);
	 		
	 		Element animation13 = doc1.createElementNS(svgNS, "animate");
	 		animation13.setAttributeNS(null, "attributeType", "CSS");
	 		animation13.setAttributeNS(null, "attributeName", "opacity");
	 		animation13.setAttributeNS(null, "from", "1");
	 		animation13.setAttributeNS(null, "to", "0");
	 		animation13.setAttributeNS(null, "dur", "2s");
	 		animation13.setAttributeNS(null, "fill", "freeze");
	 		
	 		text.appendChild(animation13);
	 		
	 		svgRoot1.appendChild(text);
	     } 
		
		
		
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
		
	    ArrayList<String> listOfLines2 = linesGetter.getLines(second.getText());
	     
	    int size2 = listOfLines2.size();
	     
	     for(int i = 0; i<size2; i++){
	    	 Element text = doc2.createElementNS(svgNS, "text");
	    	 text.setAttributeNS(null, "x", "5");
	    	 Integer integerY = new Integer((height/(size+1))*(i+1));
	    	 text.setAttributeNS(null, "y", integerY.toString());
	    	 text.setAttributeNS(null, "font-family", fontType);
	    	 text.setAttributeNS(null, "font-size", fontStr);
	    	 text.setAttributeNS(null, "fill", "white");
	    	 text.setAttributeNS(null, "stroke", "black");
	    	 text.setAttributeNS(null, "stroke-width", "1px");
	    	 text.setAttributeNS(null, "opacity", "0");
	    	 Text textNode = doc2.createTextNode(listOfLines2.get(i));
	    	 text.appendChild(textNode);
	 		
	 		
	 		Element animation13 = doc2.createElementNS(svgNS, "animate");
	 		animation13.setAttributeNS(null, "attributeType", "CSS");
	 		animation13.setAttributeNS(null, "attributeName", "opacity");
	 		animation13.setAttributeNS(null, "from", "0");
	 		animation13.setAttributeNS(null, "to", "1");
	 		animation13.setAttributeNS(null, "dur", "2s");
	 		animation13.setAttributeNS(null, "fill", "freeze");
	 		
	 		text.appendChild(animation13);
	 		
	 		svgRoot2.appendChild(text);
	     } 
		
		Node importedNode = doc1.importNode(svgRoot2, true);
		svgRoot1.appendChild(importedNode);
		
		
		return doc1;
		
		
	}

}
