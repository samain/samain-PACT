package Synchronisation;

import java.util.ArrayList;


//classe s'occuppant de donner les lignes du texte � afficher sur le SVG.

public class LinesGetter {
	public LinesGetter(){
		
	}
	
	public ArrayList<String> getLines(String text){ //renvoie la liste des lignes � afficher dans le SVG
		int lastIndexOfSpace = -1;
		int lastCut = 0;
		int length = text.length();
		ArrayList<String> stringList = new ArrayList<String>();
		 
		for(int i = 0; i<length; i++){
			if (text.charAt(i) == ' '){
				if ((i-lastCut) > 70){
					if (lastIndexOfSpace<=lastCut){
						stringList.add(text.substring(lastCut, i));
						lastCut = i;
					}
					else{
						stringList.add(text.substring(lastCut, lastIndexOfSpace));	 
						lastCut = lastIndexOfSpace;
					}
				}
				lastIndexOfSpace = i;
			}
		}
		 
		if ((lastCut)<=(length-1)){
			stringList.add(text.substring(lastCut, length));
		}
		 
		return stringList;
	}
}
