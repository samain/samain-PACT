package Synchronisation;

import java.util.ArrayList;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
	public DocumentCreator(int font, int height, int width, String fontType){
		this.linesGetter = new LinesGetter();
		
		this.font = font;
		this.height = height;
		this.width = width;
		this.fontType = fontType;
		
		this.fontStr = ((Integer)font).toString();
		this.heightStr = ((Integer)height).toString();
		this.widthStr = ((Integer)width).toString();
		
		this.ressourcesAdress = "file:///".concat(reverseSlash(System.getProperty("user.dir")).concat("/Ressources/"));
	}
//-----------------------------------------------------------------------------------------------------------------------	
	public Document toDocument(AugmentedPage augmentedPage){
		
		 Document doc = impl.createDocument(svgNS, "svg", null);
		 Element svgRoot = doc.getDocumentElement();
	     svgRoot.setAttributeNS(null, "width", widthStr);
	     svgRoot.setAttributeNS(null, "height", heightStr);
	    
	     Element image = doc.createElementNS(svgNS, "image");
	     image.setAttributeNS(null, "x", "0");
	     image.setAttributeNS(null, "y", "0");
	     image.setAttributeNS(null, "width", widthStr);
	     image.setAttributeNS(null, "height", heightStr);
	     image.setAttributeNS(xlinkNS, "xlink:href", ressourcesAdress.concat(augmentedPage.getAmbiance()));
	     image.setAttributeNS(null, "visibility", "visible");
	     image.setAttributeNS(null, "opacity", "1");
	     
	     svgRoot.appendChild(image);
	     
	     ArrayList<String> listOfLines = linesGetter.getLines(augmentedPage.getText());
	     
	     createText(listOfLines, doc, svgRoot, height,width);
	     
	     return doc;
		
	}
//-------------------------------------------------------------------------------------------------------------	
	public void createText(ArrayList<String> listOfLines, Document doc, Element root, int height, int width){
			 
		int size = listOfLines.size();
		String fontSize = (new Integer(this.font)).toString();
		
		for(int i = 0; i<size; i++){
			Element text = doc.createElementNS(svgNS, "text");
			text.setAttributeNS(null, "x", "0");
			Integer integerY = new Integer(200 + ((height-100)/size)*i);
			text.setAttributeNS(null, "y", integerY.toString());
			text.setAttributeNS(null, "font-family", fontType);
			text.setAttributeNS(null, "font-size", fontSize);
			text.setAttributeNS(null, "fill", "black");
			
			Text textNode = doc.createTextNode(listOfLines.get(i));
			text.appendChild(textNode);
			root.appendChild(text);
		}
			 
	}
//-----------------------------------------------------------------------------------------------------------------	
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
}
