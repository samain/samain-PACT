package Classification;

import augmentedPage.AugmentedPage;
import decoupage.*;

// classe implémentant le classificateur

public class Classifier implements ClassifierInterface {

private PageInterface pageMaker;
private GetAtmosphereInterface getAtmosphere;
	
//------------------------------------------------------------------------------------------
	//constructeur de la classe Classifier
	public Classifier(String textURI, int font){
		this.pageMaker = new PageMaker(textURI, font);
		this.getAtmosphere = new GetAtmosphere();
	}	
//--------------------------------------------------------------------------------------------	
	//méthode renvoyant le texte et l'ambiance de la page demandée par l'utilisateur
	public AugmentedPage sendAugmentedPage(String mouvement){
		String text = pageMaker.sendNewPage(mouvement);
		String[] atmosphereAdress = getAtmosphere.getTheAtmosphere(text);
		AugmentedPage augmentedPage = new AugmentedPage(text, atmosphereAdress);
		return augmentedPage;
	}
//----------------------------------------------------------------------------------------------	
	//méthode testant si la page lue est la première du livre
	public boolean isFirst(){
		return pageMaker.isFirst(); 
	}
//-----------------------------------------------------------------------------------------------	
	//méthode testant si la page lue est la dernière du livre
	public boolean isLast(){
		return  pageMaker.isLast(); 
	}
//-------------------------------------------------------------------------------------------	
	//méthode choisissant le livre le mieux adapté à une atmosphère 
	public void chooseBook(int font, String atmosphereAdress){
		
	}
//-----------------------------------------------------------------------------------------------
	//méthode transmettant l'adresse du livre à lire
	public void setBook(int font, String textURI){
		pageMaker = new PageMaker(textURI, font);
	}
		
	
}
