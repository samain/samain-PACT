package Synchronisation;

import org.w3c.dom.Document;

public interface DocumentToFileInterface {
	//m�thode charg�e de sauvegarder un document sous forme d'image PNG
    public void save(Document doc, String svgURI);
}
