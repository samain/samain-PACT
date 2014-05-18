package Synchronisation;

import augmentedPage.AugmentedPage;
import java.awt.image.BufferedImage;
import java.io.*;

public interface DocumentCreatorInterface {

	/*méthode servant à créer et enregistrer un nouveau docuement 
	  sous forme de fichier PNG à partir d'une AugmentedPage*/ 
	public void createDocument(AugmentedPage augmentedPage);
	
	
}
