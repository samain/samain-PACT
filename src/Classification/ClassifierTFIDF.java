package Classification;

import java.util.ArrayList;

import augmentedPage.AugmentedPage;
import decoupage.PageInterface;
import decoupage.PageMaker;

public class ClassifierTFIDF implements ClassifierInterface{
	
	private PageInterface pageMaker;
	
	private GetAtmosphereInterface getAtmosphere;
	
	private ChosenAtmosphereInterface atmosphere;
	
	private FileToTextInterface fileToText;

//--------------------------------------------------------------------------------------------
	//constructeur de la classe ClassifierTFIDF
	public ClassifierTFIDF(){
		
			getAtmosphere = new GetAtmosphere();
	
			atmosphere = new BookReaderChosenAtmosphere();
	
			fileToText = new FileToText();
			
	}
//-------------------------------------------------------------------------------------------
	//méthode renvoyant l'AugmentedPage contenant le texte et l'ambiance associés à la page lue
	public AugmentedPage sendAugmentedPage(String mouvement){
		String text = pageMaker.sendNewPage(mouvement);
		String[] atmosphereAdress = getAtmosphere.getTheAtmosphere(text);
		AugmentedPage augmentedPage = new AugmentedPage(text, atmosphereAdress);
		return augmentedPage;
	}
//-------------------------------------------------------------------------------------------
	//méthode indiquant si la page lue par le lecteur est la première
	public boolean isFirst(){
		return pageMaker.isFirst(); 
	}
//-------------------------------------------------------------------------------------------	
	//méthode indiquant  si la page lue par le lecteur est la dernière
	public boolean isLast(){
		return  pageMaker.isLast(); 
	}
//-------------------------------------------------------------------------------------------	
	//méthode choisissant le livre le mieux adapté à une atmosphère 
	public void chooseBook(int font, String atmosphereAdress){
		ArrayList<String[]> list = fileToText.findBook(atmosphereAdress);
		String[] atmosphereAndBooks = list.get(1);
		//location of your source files, only text file
		atmosphere.readPagesChosenAtmosphere(atmosphereAndBooks);
		//calculates tfidf
        atmosphere.tfIdfCalculatorChosenAtmosphere();
        //calculated cosine similarity and returns index of the adress of the book  
        int i = atmosphere.getCosineSimilarityChosenAtmosphere();  
        pageMaker = new PageMaker(list.get(0)[i], font);
        
	}
//-----------------------------------------------------------------------------------------------
	//méthode transmettant l'adresse du livre à lire
	public void setBook(int font, String textURI){
		pageMaker = new PageMaker(textURI, font);
	}
	
}
