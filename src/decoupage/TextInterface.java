package decoupage;

import java.io.IOException;
import java.util.ArrayList;

public interface TextInterface {
	//m�thode renvoyant le nombre de pages du texte lu
	public int getNumberOfPages();
	
	//m�thode d�coupant le texte lu en pages
    public ArrayList<String> CutInPages() throws IOException;
    
}
