package decoupage;

public interface PageInterface {

	public String sendNewPage(String mouvement); 
	//renvoie un cha�ne de caract�re contenant le texte de la page � analyser.  
	
	// public String[] firstPages(); 
	
	public boolean isFirst();
	
	public boolean isLast();

	
}
