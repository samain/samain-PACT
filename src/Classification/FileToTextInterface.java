package Classification;

import java.util.ArrayList;

public interface FileToTextInterface {
	
	/*méthode envoyant une ArrayList contenant un premier tableau avec les adresses du livre et 
	  des atmosphères et un second tableau contenant le texte du livre et les mots associés aux 
	  amosphères dont l'adresse est au même indice dans le premier tableau*/
	  public ArrayList<String[]> findAtmosphere(String bookName);
	  
	//idem que la méthode précédente mais avec une atmosphère et des textes à lire
	public ArrayList<String[]> findBook(String atmosphereName);
}
