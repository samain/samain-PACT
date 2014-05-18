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


// classe s'occuppant de cr�er les documents 
public class DocumentCreator implements DocumentCreatorInterface {
	private CutInLinesInterface linesGetter;
	
	//Police de caract�re
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
	
	private DocumentToFileInterface png;


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
	// cr�e le document associ� au fichier SVG d'une page qui sera affich�
	public void createDocument(AugmentedPage augmentedPage){
	     
		 Document doc = impl.createDocument(svgNS, "svg", null);
		 
		 //obtention de la racine du document
		 Element svgRoot = doc.getDocumentElement();
		 
		 //sp�cification de la largeur et de la hauteur du document
	     svgRoot.setAttributeNS(null, "width", widthStr);
	     svgRoot.setAttributeNS(null, "height", heightStr);
	    
	     
	     //cr�ation et ajout de l'image corespondant � l'ambiance visuelle de la page
	     Element image = doc.createElementNS(svgNS, "image");
	     	 image.setAttributeNS(null, "x", "0");
		     image.setAttributeNS(null, "y", "0");
		     image.setAttributeNS(null, "width", widthStr);
		     image.setAttributeNS(null, "height", heightStr);
		     image.setAttributeNS(xlinkNS, "xlink:href", ressourcesAdress.concat(augmentedPage.getAmbiance()[0]));
		     image.setAttributeNS(null, "visibility", "visible");
		     image.setAttributeNS(null, "opacity", "1");
	     
	     svgRoot.appendChild(image);
	     
	     
	     //d�coupage du texte de la page en lignes
	     ArrayList<String> listOfLines = linesGetter.getLines(augmentedPage.getText());
	     
	     int size = listOfLines.size();
	     
	     for(int i = 0; i<size; i++){
	    	 //ajout de la (i+1)-�me ligne
	    	 Integer integerY = new Integer((height/(size+1))*(i+1));
	    	 
	    	 Element text = doc.createElementNS(svgNS, "text");
	    	 	 text.setAttributeNS(null, "x", "5");
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
	     
	     //enregistrement du Document nouvellement cr�� sous forme de fichier PNG
	     png.save(doc, System.getProperty("user.dir")+ "\\Images\\img.png");

	     	
	}
	


}
