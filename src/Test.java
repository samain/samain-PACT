import affichage.*;
import augmentedPage.AugmentedPage;
import Classification.*;
import decoupage.*;
import menu.Menu;
import son.*;
import synchroniseur.*;

import java.util.*;
import java.awt.*;
import java.awt.GraphicsEnvironment;
import java.io.*;


public class Test {

	public static void main(String[] args) {

		   VisualUnit visualUnit = new VisualUnit();
	       SoundUnit soundUnit = new SoundUnit();
	       PageMaker pageMaker = new PageMaker();
	       Classifier2 classifier =  new Classifier2(pageMaker);
	       // usage : classifieur, image, son, taille de police, police, mode plein écran pour l'image (true), position des images en x
	       Synchronizer synchronizer = new Synchronizer(classifier, visualUnit, soundUnit, 40, "Arial", false, 0);
	       final Menu menu = new Menu(synchronizer);
	       visualUnit.addKeyListener(menu); 
		
		   /* System.out.println(System.getProperty("user.dir"));
		   System.out.println(System.getProperty("user.dir") +"//Nouvellepage.txt"); */
		
		   /* String s1 = "abc def\nghi";
  		   System.out.println(s1.replace('\n',' '));
  		   String[] brutTab = s1.replace('\n', ' ').split("[.,;:' ]+");
  		   for (int i = 0; i< brutTab.length; i++){
  			 System.out.println(brutTab[i]);
  		   }
  		 System.out.println(s1); */
	
		 /* try{
		   FileReader fr = new FileReader(System.getProperty("user.dir")+"//Ressources"+"//Nouvellepage.txt");
		
		   
		   BufferedReader bf = new BufferedReader(fr);
		   bf.mark(1);
		   System.out.println("entrée boucle");
           while(bf.read()!=-1){
        	   bf.reset(); 
        	   String s = bf.readLine();
        	   System.out.println(s);
               bf.mark(1);
           }
		 }
		 catch(FileNotFoundException e){
			 System.out.println("fichier on trouvé");
		 }
		 catch(IOException e){
			 System.out.println("erreur");
		 } */
		
	       // test 1
	       /* ArrayList<String> listOfLines = synchronizer.getLines("aabbaabbaabb  aabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaa aa aa aa aa aa aa aa aa aa aa ");
	       int size = listOfLines.size();
	       for(int i = 0; i<size; i++){
	    	   System.out.println(listOfLines.get(i));
	       } */
	       
	       // test 2
	      /* String[] array = pageMaker.setBook("");
	       for(int i = 0; i<3; i++){
	    	   System.out.println(array[i]);
	       } */
	       
	       // test  3
	       /* ArrayList<AugmentedPage> aPL = classifier.firstAugmentedPages("");
	       for(int i = 0; i<3; i++){
	    	   System.out.println(aPL.get(i).getText());
	    	   System.out.println(aPL.get(i).getAmbiance());
	       } */
	       
	       // test 4
	       /*  ArrayList<AugmentedPage> aPL = classifier.firstAugmentedPages("");
	       
	        String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
	   	
	   	    String xlinkNS = "http://www.w3.org/1999/xlink";
	   	
	     	DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
	   	
	    	String resourcesAdress = "file:///".concat(synchronizer.reverseSlash(System.getProperty("user.dir")).concat("/Ressources/"));
	       
	    	AugmentedPage augmentedPage = aPL.get(1);
	    	
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
		     
		     Element text = doc.createElementNS(svgNS, "text");
			 text.setAttributeNS(null, "x", "100");
			 Integer integerY = new Integer(200  + ((height-100)/size)*i );
			 text.setAttributeNS(null, "y", integerY.toString());
			 text.setAttributeNS(null, "font-family", "Verdana");
			 text.setAttributeNS(null, "font-size", "55");
			 text.setAttributeNS(null, "fill", "black");
			 
			 Text textNode = doc.createTextNode(augmentedPage.getText());
			 text.appendChild(textNode);
			 svgRoot.appendChild(text);
		   
		     JSVGCanvas canvas = new JSVGCanvas(null, true, false);
		     canvas.setDocument(doc);
		     visualUnit.display(canvas); 
		     
		     System.out.println(augmentedPage.getText()); */
		     
		     // ArrayList<String> listOfLines = synchronizer.getLines(augmentedPage.getText()); 
		   
	         // test 5
	       /* ArrayList<AugmentedPage> aPL = classifier.firstAugmentedPages("");
	       ArrayList<String> list = synchronizer.getLines(aPL.get(1).getText());
	       System.out.println(list.get(0));
	       ArrayList<String> list = synchronizer.getLines("AB");
	       System.out.println(list.get(0)); */
	       
	       
	       
	   
	     /* Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
	       System.out.println(rect.getHeight()); */
	       
	         // test 6
	           synchronizer.initialiseBook("");
	       
	       //test 7 : séquence de mouvement de l'utilisateur
	       // synchronizer.initialiseBook("");
	       /*  try {
			    Thread.sleep(10000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			} 
	       synchronizer.receiveMouvement("right");
	       try {
			    Thread.sleep(5000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			} 
	       synchronizer.receiveMouvement("right");
	       try {
			    Thread.sleep(5000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			} 
	       synchronizer.receiveMouvement("left");
	       try {
			    Thread.sleep(5000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			} 
	       synchronizer.receiveMouvement("left"); */ 
	         
	       
	}

}
