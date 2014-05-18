package Synchronisation;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import son.SoundUnit;
import affichage.VisualUnit;
import augmentedPage.AugmentedPage;

public interface SynchronizerInterface {

	    //cr�e les documents svg de chaque �cran de l'interface graphique
		public void initialiseBook(String textURI, int font);
		
		//m�thode d�marrant la lecture du livre correspondant le mieux � l'ambiance choisie par le lecteur
		public void initialiseAtmosphere(String atmosphereURI, int font);
		
		//d�cide des actions � faire en fonstion du contexte (menu utilisateur ou bien 
		//lecture d'un livre)
		public void receiveMouvement (String mouvement);
		
		//re�oit la page demand�e par le lecteur et la place au bon endroit. 
		public AugmentedPage createPage(String mouvement);
		
		// renverse les slash dans une adresse uri.
		public String reverseSlash(String path);
				
	    //m�thode renvoyant les dimensions de l'�cran sur lequel le livre est affich�
		public int[] getResolution();
		
		//m�thode modifiant la taille de la police utilis�e
		public void setFont(int font);
}
