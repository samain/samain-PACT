package augmentedPage;

// classe stockant la page lue et l'ambiance visuelle et sonore associée.

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
	//méthode renvoyant le texte de l'AugmentedPage
	public String getText(){
		return page;
	}
//-------------------------------------------------------------------------
	//méthode renvoyant l'ambiance associée au texte de l'AugmentedPage
	public String[] getAmbiance(){
		return ambiance;
	}
}