package Decoupage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Text {
	private String fileName;
	private int font;
	
	public Text(String fileName, int font) {
        this.fileName = fileName;
        this.font= font;
	}

	public final int[][] tabledelongueurs=
		{
			{10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40},
			{100,98,96,94,92,90,88,86,84,82,80,78,76,74,72,70,68,66,64,62,60,58,56,54,52,50,48,46,44,42,40},
			// {21,19,18,17,16,14,13,13,12,11,11,10,10,9,9,9,8,8,8,8,8,7,7,7,7,6,6,6,6,6,5},
			{94,86,78,72,67,63,59,55,52,49,47,45,43,41,39,37,36,35,33,32,31,30,29,28,27,27,26,25,24,24,23}
	};

	public int getNumberOfPages() {
		try {
			int length = this.convertToText().length();
			int caraNbr = tabledelongueurs[1][this.font-10]*tabledelongueurs[2][this.font-10];
			caraNbr = caraNbr / 50; //A MODIFIER POUR REGLER L'AFFICHAGE
			
			int pageNbr = (int) (length/caraNbr) + 1;
			return pageNbr;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public final String convertToText() throws IOException {
    	String texte= "";
		
		try
		{
			BufferedReader printfile = new BufferedReader(new FileReader(fileName));
			
			String ligne = "";
			printfile.mark(1);
			if(printfile.read() !=-1){
				printfile.reset();
				ligne=printfile.readLine();
				texte= texte + ligne;
				printfile.mark(1);
			}
			
			try
			{    						
				while (printfile.read() != -1)
				{	
					printfile.reset();
					ligne =printfile.readLine();
					texte= texte+ligne;
					printfile.mark(1);
				}
				
				printfile.close();
			} catch (IOException exception) { 
				System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
			}
		} catch (FileNotFoundException exception) {
			System.out.println ("Le fichier n'a pas ete trouve");
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		} catch (Error e) {
			System.out.println("Error: " + e);
		}
		return texte;
    	
}
	
	public ArrayList<String> CutInPages() throws IOException {
		
		int k = 0; //nombre de caract�re par page
		int h = 0; //curseur
	    int pagesNbr = this.getNumberOfPages();
		
		ArrayList<String> pagesContent = new ArrayList<String>();
		
		k = tabledelongueurs[1][this.font-10]*tabledelongueurs[2][this.font-10]/50;
		
		String text = this.convertToText();
		int length = text.length();
		
		if (pagesNbr != 0)
		{
			//for (int j =0; j < pagesNbr; j++)  //j = num�ro de page
			while(h<text.length())
			{		
			    System.out.println("h = ");
				System.out.println(h);
				System.out.println("min(h+k, length) = ");
				System.out.println(Math.min(h+k, length)); 
				if(h+k>length){
					pagesContent.add(text.substring(h, length));
					h = h+k;
				}
				else{
				if (text.substring(h, h+k).lastIndexOf(' ') == -1){		
					pagesContent.add(text.substring(h, h+k));
					System.out.println("derni�re page ajout�e = ");
				    System.out.println(text.substring(h, h+k));
					h= h+k;
					if(h<length){
						if (text.charAt(h) == ' '){
							h = h+1;
						}
					}
				}
				else{	
			    System.out.println("lastIndexOfSpace = ");	
				System.out.println(text.substring(h, h+k).lastIndexOf(' ')+1+h);	
				pagesContent.add(text.substring(h, text.substring(h, h+k).lastIndexOf(' ')+1+h));
				System.out.println("derni�re page ajout�e = ");
			    System.out.println(text.substring(h, text.substring(h, h+k).lastIndexOf(' ')+1+h)); 
				h= text.substring(h, h+k).lastIndexOf(' ')+1+h;
				}
				}
			}
			
		}
		return pagesContent;	
	}
}