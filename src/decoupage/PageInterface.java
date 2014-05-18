package decoupage;

public interface PageInterface {

	//méthode renvoyant un chaîne de caractère contenant le texte de la page à analyser.
	public String sendNewPage(String mouvement);   
	
	//méthode testant si la page lue est la première page du livre choisi
	public boolean isFirst();
	
	//méthode testant si la page lue est la dernière page du livre choisi
	public boolean isLast();

	
}
