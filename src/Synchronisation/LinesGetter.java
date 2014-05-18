package Synchronisation;

import java.util.ArrayList;


//classe s'occuppant de donner les lignes du texte à afficher sur le SVG.

public class LinesGetter implements CutInLinesInterface {
	
	//constructeur de la classe LinesGetter
	public LinesGetter(){
		
	}
	
	//renvoie la liste des lignes à afficher dans le SVG
	public ArrayList<String> getLines(String text){ 
		int lastIndexOfSpace = -1;
		int lastCut = 0;
		int length = text.length();
		ArrayList<String> stringList = new ArrayList<String>();
		 
		for(int i = 0; i<length; i++){
			//si l'on arrive à la fin d'un mot
			if (text.charAt(i) == ' '){
				/*si le nombre de caractère depuis la fin 
				de la dernière ligne est strictement supérieur à 70*/
				if ((i-lastCut) > 70){
					/*si l'on n'a pas changé de mot depuis la dernière ligne,
					 la ligne va de l'indice de dernière coupe à l'indice actuel*/
					if (lastIndexOfSpace<=lastCut){
						stringList.add(text.substring(lastCut, i));
						lastCut = i;
					}
					/*sinon, on crée une ligne se terminant à la fin du dernier mot rencontré 
					avant le mot courant*/
					else{
						stringList.add(text.substring(lastCut, lastIndexOfSpace));	 
						lastCut = lastIndexOfSpace;
					}
				}
				//l'indice de dernier espace rencontré devient l'indice actuel
				lastIndexOfSpace = i;
			}
		}
		 
		/*si la dernière ligne crée ne comprend pas les derniers caractères du texte, 
		 on crée une ligne incomplète les contenant*/
		if ((lastCut)<=(length-1)){
			stringList.add(text.substring(lastCut, length));
		}
		 
		return stringList;
	}
}
