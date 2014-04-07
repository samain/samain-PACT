package decoupage;

public interface PageInterface {

	public String sendNewPage(String mouvement); 
	//renvoie un chaîne de caractère contenant le texte de la page à analyser.  
	
	// public String[] firstPages(); 
	
	public boolean isFirst();
	
	public boolean isLast();

	
}
