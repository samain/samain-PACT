package decoupage;

public interface PageInterface {

	//m�thode renvoyant un cha�ne de caract�re contenant le texte de la page � analyser.
	public String sendNewPage(String mouvement);   
	
	//m�thode testant si la page lue est la premi�re page du livre choisi
	public boolean isFirst();
	
	//m�thode testant si la page lue est la derni�re page du livre choisi
	public boolean isLast();

	
}
