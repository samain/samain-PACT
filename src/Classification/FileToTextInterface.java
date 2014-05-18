package Classification;

import java.util.ArrayList;

public interface FileToTextInterface {
	
	/*m�thode envoyant une ArrayList contenant un premier tableau avec les adresses du livre et 
	  des atmosph�res et un second tableau contenant le texte du livre et les mots associ�s aux 
	  amosph�res dont l'adresse est au m�me indice dans le premier tableau*/
	  public ArrayList<String[]> findAtmosphere(String bookName);
	  
	//idem que la m�thode pr�c�dente mais avec une atmosph�re et des textes � lire
	public ArrayList<String[]> findBook(String atmosphereName);
}
