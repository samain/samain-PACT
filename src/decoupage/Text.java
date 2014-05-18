package decoupage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// classe r�alisant le d�coupage en page d'un fichier .txt

public class Text implements TextInterface {
	private String fileName;
	private int font;
	
	//tableau faisant la corresopondance entre police choisie et nombre de caract�res par page
	public final int[][] tabledelongueurs=
		{
			{10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50},
			{300,298,296,294,292,190,288,286,284,282,280,278,276,274,272,270,268,266,264,262,260,258,256,254,252,250,248,246,244,242,240,228,226,224,222,220,218,216,214,212,210},
			{94,86,78,72,67,63,59,55,52,49,47,45,43,41,39,37,36,35,33,32,31,30,29,28,27,27,26,25,24,24,23,23,23,23,22,22,21,21,21,20,20}
		};
//------------------------------------------------------------------------------------------------	
	//constructeur de la classe Text
	public Text(String fileName, int font) {
        this.fileName = fileName;
        this.font= font;
	}
//----------------------------------------------------------------------------------------------------
	//m�thode renvoyant le nombre de pages du texte lu
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
//----------------------------------------------------------------------------------------------------		
	//m�thode cr�ant une String contenant le texte du livre lu
	 private final String convertToText() throws IOException {
    	String texte= "";
		
		try
		{
			BufferedReader printfile = new BufferedReader(new FileReader(fileName));
			
			String ligne = "";
			/*indique que l'on place une marque sur le caract�re courant et que l'on tol�re
			 la lecture d'un seul caract�re avant perte de cette marque*/
			printfile.mark(1);
		
			try
			{    
				//tant qu'il reste au moins un caract�re � lire
				while (printfile.read() != -1)
				{	
					/*lecture de la ligne, concat�nation de 
					celle-ci � la cha�ne de caract�res courante et
					remise en place de la marque */
					printfile.reset();
					ligne =printfile.readLine();
					texte= texte+ligne;
					printfile.mark(1);
				}
				//fermeture du BufferedReader
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
//---------------------------------------------------------------------------------------------------	
	 //m�thode d�coupant le texte lu en pages
	 public ArrayList<String> CutInPages() throws IOException {
		
		int k = 0; //nombre de caract�re par page
		int h = 0; //curseur
	    int pagesNbr = this.getNumberOfPages();
		
	    //cr�ation de la liste de pages
		ArrayList<String> pagesContent = new ArrayList<String>();
		pagesContent.clear();
		
		k = tabledelongueurs[1][this.font-10];
		
		String text = this.convertToText();
		int length = text.length();
		
		//si le nombre de pages n'est pas nul
		if (pagesNbr != 0)
		{
			//tant que le curseur est strictement inf�rieur � la longueur du texte
			while(h<text.length())
			{	
				//si l'on a atteint la derni�re page
				if(h+k>length){
					pagesContent.add(text.substring(h, length));
					h = h+k;
				}
				
				else{
				/*sinon, si la page ne contient qu'un seul mot,
			      on cr�� une nouvelle page remplie de tous les caract�res 
			      qu'elle peut contenir � partir du curseur, et on d�place 
			      le curseur au d�but de la page suivante*/
				if (text.substring(h, h+k).lastIndexOf(' ') == -1){		
					pagesContent.add(text.substring(h, h+k));
					h= h+k;
					
					//si le curseur d�signe un espace, on avance le curseur
					if(h<length){
						if (text.charAt(h) == ' '){
							h = h+1;
						}
					}
					
				}
				
				/*sinon, on cr�e une nouvelle page contenant les caract�res allant du curseur
				 � la fin du dernier mot complet pouvant �tre contenu dans la page, espace le 
				 suivant compris, et on d�place le curseur jusqu'au caract�re suivant cet espace */
				else{		
					pagesContent.add(text.substring(h, text.substring(h, h+k).lastIndexOf(' ')+1+h));
					h= text.substring(h, h+k).lastIndexOf(' ')+1+h;
				}
				}
			}
			
		}
		return pagesContent;	
	}
}