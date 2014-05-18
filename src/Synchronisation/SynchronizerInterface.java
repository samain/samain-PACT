package Synchronisation;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import son.SoundUnit;
import affichage.VisualUnit;
import augmentedPage.AugmentedPage;

public interface SynchronizerInterface {

	    //crée les documents svg de chaque écran de l'interface graphique
		public void initialiseBook(String textURI, int font);
		
		//méthode démarrant la lecture du livre correspondant le mieux à l'ambiance choisie par le lecteur
		public void initialiseAtmosphere(String atmosphereURI, int font);
		
		//décide des actions à faire en fonstion du contexte (menu utilisateur ou bien 
		//lecture d'un livre)
		public void receiveMouvement (String mouvement);
		
		//reçoit la page demandée par le lecteur et la place au bon endroit. 
		public AugmentedPage createPage(String mouvement);
		
		// renverse les slash dans une adresse uri.
		public String reverseSlash(String path);
				
	    //méthode renvoyant les dimensions de l'écran sur lequel le livre est affiché
		public int[] getResolution();
		
		//méthode modifiant la taille de la police utilisée
		public void setFont(int font);
}
