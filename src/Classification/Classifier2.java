package Classification;

import java.util.ArrayList;

import augmentedPage.AugmentedPage;
import decoupage.PageMaker;

public class Classifier2 {

private PageMaker pageMaker;
private GetAtmosphere getAtmosphere;
	
	public Classifier2(PageMaker pageMaker){
		
		this.pageMaker = pageMaker;
		this.getAtmosphere = new GetAtmosphere();
	};
	
	public AugmentedPage sendAugmentedPage(String mouvement){
		
		String text = pageMaker.sendNewPage(mouvement);
		String atmosphereAdress = getAtmosphere.getTheAtmosphere(text);
		AugmentedPage augmentedPage = new AugmentedPage(text, atmosphereAdress);
		return augmentedPage;
	};
	
	public ArrayList<AugmentedPage> firstAugmentedPages(String uri){
		String[] pageList = pageMaker.setBook(uri);
		ArrayList<AugmentedPage> aPL = new ArrayList<AugmentedPage>();
		for(int i = 0; i<3; i++){
			String atmosphereAdress = getAtmosphere.getTheAtmosphere(pageList[i]);
			AugmentedPage aP = new AugmentedPage(pageList[i], atmosphereAdress);
			System.out.println(atmosphereAdress);
			aPL.add(aP);
		}
		return aPL;
	}
	
}
