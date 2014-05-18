package augmentedPage;

// classe stockant la page lue et l'ambiance visuelle et sonore associ�e.

public class AugmentedPage {

	private String page;
	private String[] ambiance;
//------------------------------------------------------------------------
	//constructeur de la classe AugmentedPage
	public AugmentedPage(String page, String[] ambiance){
		this.page = page;
		this.ambiance = ambiance;
	}
//-------------------------------------------------------------------------
	//m�thode renvoyant le texte de l'AugmentedPage
	public String getText(){
		return page;
	}
//-------------------------------------------------------------------------
	//m�thode renvoyant l'ambiance associ�e au texte de l'AugmentedPage
	public String[] getAmbiance(){
		return ambiance;
	}
}