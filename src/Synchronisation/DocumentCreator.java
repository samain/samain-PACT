package Synchronisation;

import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import augmentedPage.AugmentedPage;


// classe s'occuppant de créer les documents 
public class DocumentCreator implements DocumentCreatorInterface {
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
	
	private SaveAsPNG png;


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
		this.png = new SaveAsPNG();
		
	}
//-----------------------------------------------------------------------------------------------------------------------	
	// crée le document associé au fichier SVG d'une page qui sera affiché
	public void createDocument(AugmentedPage augmentedPage){
		
		 System.out.println("createDocument : texte : " + augmentedPage.getText());
		
		 long time1 = System.currentTimeMillis();
		
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
	     
	     long time2 = System.currentTimeMillis();
	     
	     System.out.println("DocumentCreator : createDocument : création du document : " + (time2-time1));
	  
	     png.save(doc, System.getProperty("user.dir")+ "\\Images\\img.png");

	     	
	}
	


}
