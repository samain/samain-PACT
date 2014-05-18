package Classification;

import augmentedPage.AugmentedPage;
import decoupage.*;

// classe impl�mentant le classificateur

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
	//m�thode renvoyant le texte et l'ambiance de la page demand�e par l'utilisateur
	public AugmentedPage sendAugmentedPage(String mouvement){
		String text = pageMaker.sendNewPage(mouvement);
		String[] atmosphereAdress = getAtmosphere.getTheAtmosphere(text);
		AugmentedPage augmentedPage = new AugmentedPage(text, atmosphereAdress);
		return augmentedPage;
	}
//----------------------------------------------------------------------------------------------	
	//m�thode testant si la page lue est la premi�re du livre
	public boolean isFirst(){
		return pageMaker.isFirst(); 
	}
//-----------------------------------------------------------------------------------------------	
	//m�thode testant si la page lue est la derni�re du livre
	public boolean isLast(){
		return  pageMaker.isLast(); 
	}
//-------------------------------------------------------------------------------------------	
	//m�thode choisissant le livre le mieux adapt� � une atmosph�re 
	public void chooseBook(int font, String atmosphereAdress){
		
	}
//-----------------------------------------------------------------------------------------------
	//m�thode transmettant l'adresse du livre � lire
	public void setBook(int font, String textURI){
		pageMaker = new PageMaker(textURI, font);
	}
		
	
}
