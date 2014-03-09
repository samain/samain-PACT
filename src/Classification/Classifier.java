package Classification;

import java.util.ArrayList;

import AugmentedPage.AugmentedPage;
import Decoupage.PageMaker;

public class Classifier {

private PageMaker pageMaker;
private GetAtmosphere getAtmosphere;
	
	public Classifier(String textURI, int font){
		this.pageMaker = new PageMaker(textURI, font);
		this.getAtmosphere = new GetAtmosphere();
	}
	
	public AugmentedPage sendAugmentedPage(String mouvement){
		
		String text = pageMaker.sendNewPage(mouvement);
		String[] atmosphereAdress = getAtmosphere.getTheAtmosphere(text);
		AugmentedPage augmentedPage = new AugmentedPage(text, atmosphereAdress[0]);
		return augmentedPage;
	}
	
	public ArrayList<AugmentedPage> firstAugmentedPages(){
		String[] pageList = pageMaker.firstPages();
		ArrayList<AugmentedPage> aPL = new ArrayList<AugmentedPage>();
		for(int i = 0; i<3; i++){
			String[] atmosphereAdress = getAtmosphere.getTheAtmosphere(pageList[i]);
			AugmentedPage aP = new AugmentedPage(pageList[i], atmosphereAdress[0]);
			aPL.add(aP);
		}
		return aPL;
	}
	
}
