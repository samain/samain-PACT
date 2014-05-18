package decoupage;

import java.io.IOException;
import java.util.ArrayList;

public interface TextInterface {
	//méthode renvoyant le nombre de pages du texte lu
	public int getNumberOfPages();
	
	//méthode découpant le texte lu en pages
    public ArrayList<String> CutInPages() throws IOException;
    
}
