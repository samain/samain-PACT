import Classification.*;
import Decoupage.*;
import AugmentedPage.*;

import java.io.*;
import java.util.ArrayList;


public class Test {

	public static void main(String[] args) {
	   /* Text text = new Text(System.getProperty("user.dir") + "//Ressources//Texte1.txt", 30);
        try{
		System.out.println(text.convertToText());
		ArrayList<String> list = text.CutInPages();
		int size = list.size();
		for(int i = 0; i<size; i++){
			System.out.println(list.get(i));
		}
        }
        catch (IOException e){
        	
        } */
   
		
		Classifier classifier = new Classifier(System.getProperty("user.dir") + "//Ressources//Texte1.txt", 30);
	    ArrayList<AugmentedPage> list = classifier.firstAugmentedPages();
	    System.out.println(list.get(0).getAmbiance());
	    System.out.println(list.get(0).getText());
	    
	    System.out.println(list.get(1).getAmbiance());
	    System.out.println(list.get(1).getText());
	    
	    System.out.println(list.get(2).getAmbiance());
	    System.out.println(list.get(2).getText()+"\n"+"\n");
	    
	    for (int i = 0; i<8; i++){
	    	AugmentedPage aP = classifier.sendAugmentedPage("right");
	    	System.out.println(aP.getAmbiance());
	    	System.out.println(aP.getText()+"\n");
	    }
	    
	    for (int i = 0; i<8; i++){
	    	AugmentedPage aP = classifier.sendAugmentedPage("left");
	    	System.out.println(aP.getAmbiance());
	    	System.out.println(aP.getText()+"\n");
	    } 
	    
		/* PageMaker pageMaker = new PageMaker(System.getProperty("user.dir") + "//Ressources//Texte1.txt", 30);
	    String[] tab =  pageMaker.firstPages();
		System.out.println(tab[0]);
		System.out.println(tab[1]);
		System.out.println(tab[2]);
		for(int i = 0; i<10; i++){
			System.out.println(pageMaker.sendNewPage("right"));
			
		} */
		
		
	    /* Text text = new Text(System.getProperty("user.dir") + "//Ressources//Texte1.txt", 30);
		
		try{
		ArrayList<String> list = text.CutInPages();
		for(int i = 0; i<list.size(); i++){
			System.out.println(list.get(i));
		}
		}
		catch(IOException e){
		} */
		/* ArrayList<String> list = new ArrayList<String>();
		list.add("");
		list.add("abc");
		System.out.println((list).size()); */
	}
}
