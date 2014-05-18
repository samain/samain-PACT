package Synchronisation;

import java.util.ArrayList;


//classe s'occuppant de donner les lignes du texte � afficher sur le SVG.

public class LinesGetter implements CutInLinesInterface {
	
	//constructeur de la classe LinesGetter
	public LinesGetter(){
		
	}
	
	//renvoie la liste des lignes � afficher dans le SVG
	public ArrayList<String> getLines(String text){ 
		int lastIndexOfSpace = -1;
		int lastCut = 0;
		int length = text.length();
		ArrayList<String> stringList = new ArrayList<String>();
		 
		for(int i = 0; i<length; i++){
			//si l'on arrive � la fin d'un mot
			if (text.charAt(i) == ' '){
				/*si le nombre de caract�re depuis la fin 
				de la derni�re ligne est strictement sup�rieur � 70*/
				if ((i-lastCut) > 70){
					/*si l'on n'a pas chang� de mot depuis la derni�re ligne,
					 la ligne va de l'indice de derni�re coupe � l'indice actuel*/
					if (lastIndexOfSpace<=lastCut){
						stringList.add(text.substring(lastCut, i));
						lastCut = i;
					}
					/*sinon, on cr�e une ligne se terminant � la fin du dernier mot rencontr� 
					avant le mot courant*/
					else{
						stringList.add(text.substring(lastCut, lastIndexOfSpace));	 
						lastCut = lastIndexOfSpace;
					}
				}
				//l'indice de dernier espace rencontr� devient l'indice actuel
				lastIndexOfSpace = i;
			}
		}
		 
		/*si la derni�re ligne cr�e ne comprend pas les derniers caract�res du texte, 
		 on cr�e une ligne incompl�te les contenant*/
		if ((lastCut)<=(length-1)){
			stringList.add(text.substring(lastCut, length));
		}
		 
		return stringList;
	}
}
