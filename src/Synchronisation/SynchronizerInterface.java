package Synchronisation;

public interface SynchronizerInterface {

	/*créé le livre stocké à l'adresse textURI et avec la taille de police font*/
	public void initialiseBook(String textURI, int font);
	
	
	public void receiveMouvement (String mouvement); 
	/*reçois le mouvement et demande l'analyse d'une nouvelle page 
	qui dépend de la direction du mouvement.*/
	
	public void initialiseAtmosphere(String AtmosphereURI, int font);
}
