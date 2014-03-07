package Classification;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ReadAFile {
	
	private BufferedReader monFichier;
	
	public ReadAFile(String adresseFichier){
		try {
			this.monFichier = new BufferedReader(new FileReader(adresseFichier));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String contenuFichier(){
		String contenuMonFichier="";
		try {
			monFichier.mark(1);
			while(monFichier.read()!= -1)
			{
				monFichier.reset();
				contenuMonFichier= contenuMonFichier + monFichier.readLine()+"\n";
				monFichier.mark(1);
			}
			// System.out.println(contenuMonFichier);
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

