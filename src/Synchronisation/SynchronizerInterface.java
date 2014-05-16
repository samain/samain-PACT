package Synchronisation;

public interface SynchronizerInterface {

	/*cr�� le livre stock� � l'adresse textURI et avec la taille de police font*/
	public void initialiseBook(String textURI, int font);
	
	
	public void receiveMouvement (String mouvement); 
	/*re�ois le mouvement et demande l'analyse d'une nouvelle page 
	qui d�pend de la direction du mouvement.*/
	
	public void initialiseAtmosphere(String AtmosphereURI, int font);
}
