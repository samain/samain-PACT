package Synchronisation;

import augmentedPage.AugmentedPage;
import java.awt.image.BufferedImage;
import java.io.*;

public interface DocumentCreatorInterface {

	/*m�thode servant � cr�er et enregistrer un nouveau docuement 
	  sous forme de fichier PNG � partir d'une AugmentedPage*/ 
	public void createDocument(AugmentedPage augmentedPage);
	
	
}
