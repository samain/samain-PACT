package Classification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileToText implements FileToTextInterface {
	
	private String ResourcesAdress;
//-------------------------------------------------------------------------------------------
	//constructeur de la classe FileToText
	public FileToText(){
		ResourcesAdress = System.getProperty("user.dir");
	}
//---------------------------------------------------------------------------------------------
	/*m�thode envoyant une ArrayList contenant un premier tableau avec les adresses du livre et 
	  des atmosph�res et un second tableau contenant le texte du livre et les mots associ�s aux 
	  amosph�res dont l'adresse est au m�me indice dans le premier tableau*/
	public ArrayList<String[]> findAtmosphere(String bookName){
		ArrayList<String> listOfNames = new ArrayList<String>();
		ArrayList<String> listOfTexts = new ArrayList<String>();
		listOfNames.add(bookName);
		listOfTexts.add(readFile(bookName));
		File file = new File(ResourcesAdress+ "/Ressources");	
		File[] fileList = file.listFiles();
		int length = fileList.length;
		
		/*�num�re tout les fichiers contenu par le r�pertoire correspondant � l'adresse du File 
		pr�c�demment ouvert*/
		for (int i = 0; i<length; i++){
			if (fileList[i].isFile()){
				listOfNames.add(fileList[i].getName());
				listOfTexts.add(readFile(fileList[i].getName()));
			}
			
		}
		
		//conversion des tableaux en ArrayList
		int size = listOfNames.size();
		
		String[] arrayOfNames = new String[size];
		
		String[] arrayOfTexts = new String[size];
		
		for (int j = 0; j<size; j++){
			arrayOfNames[j] = listOfNames.get(j); 
			arrayOfTexts[j] = listOfTexts.get(j);
		}
		
		//ajout des tableaux dans l'ArrayList � renvoyer 
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		list.add(arrayOfNames);
		list.add(arrayOfTexts);
		
		return list;
	}
//--------------------------------------------------------------------------------------------------
	//idem que la m�thode pr�c�dente mais avec une atmosph�re et des textes � lire
	public ArrayList<String[]> findBook(String atmosphereName){
		ArrayList<String> listOfNames = new ArrayList<String>();
		ArrayList<String> listOfTexts = new ArrayList<String>();
		listOfNames.add(atmosphereName);
		listOfTexts.add(readFile(atmosphereName));
		File file = new File(ResourcesAdress+ "/Textes");	
		File[] fileList = file.listFiles();
		int length = fileList.length;
		
		for (int i = 0; i<length; i++){
			if (fileList[i].isFile()){
				listOfNames.add(ResourcesAdress+ "/Textes/" + fileList[i].getName());
				listOfTexts.add(readFile(ResourcesAdress+ "/Textes/" + fileList[i].getName()));
			}
			
		}
		
		int size = listOfNames.size();
		
		String[] arrayOfNames = new String[size];
		
		String[] arrayOfTexts = new String[size];
		
		for (int j = 0; j<size; j++){
			arrayOfNames[j] = listOfNames.get(j); 
			arrayOfTexts[j] = listOfTexts.get(j);
		}
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		list.add(arrayOfNames);
		list.add(arrayOfTexts);
		
		return list;
	}
//------------------------------------------------------------------------------------------------
	//m�thode renvoyant une cha�ne de caract�res String contenant le texte du fichier � lire
	private String readFile(String fileName){
		
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

}
