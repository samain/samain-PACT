package Classification;

import augmentedPage.AugmentedPage;
import decoupage.*;

// classe implémentant le classificateur

public class Classifier implements ClassifierInterface {

private PageInterface pageMaker;
private GetAtmosphere getAtmosphere;
	
	public Classifier(String textURI, int font){
		this.pageMaker = new PageMaker(textURI, font);
		this.getAtmosphere = new GetAtmosphere();
	}
	
	public AugmentedPage sendAugmentedPage(String mouvement){
		
		String text = pageMaker.sendNewPage(mouvement);
		String[] atmosphereAdress = getAtmosphere.getTheAtmosphere(text);
		AugmentedPage augmentedPage = new AugmentedPage(text, atmosphereAdress);
		return augmentedPage;
	}
	
	public boolean isFirst(){
		return pageMaker.isFirst(); 
	}
	
	public boolean isLast(){
		return  pageMaker.isLast(); 
	}
	
}
