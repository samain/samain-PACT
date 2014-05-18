package Classification;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//classe convertissant un ficher texte en une String.

public class ReadAFile {
	
	private BufferedReader monFichier;
	
//--------------------------------------------------------------------------------	
	//constructeur de la classe ReadAFile
	public ReadAFile(String adresseFichier){
		try {
			this.monFichier = new BufferedReader(new FileReader(adresseFichier));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//----------------------------------------------------------------------------------
	//méthode renvoyant une chaîne de caractères contenant le texte du fichier .txt lu
	public String contenuFichier(){
		String temp="";
		String contenuMonFichier="";
		try {
			while((temp=monFichier.readLine())!=null)
			{
				contenuMonFichier= contenuMonFichier + " " + temp;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			monFichier.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contenuMonFichier;
	}
}

