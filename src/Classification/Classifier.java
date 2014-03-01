package Classification;

import augmentedPage.AugmentedPage;
import decoupage.*;

public class Classifier implements ClassifierInterface {

	private PageMaker pageMaker;
	
	public Classifier(PageMaker pageMaker){
		
		this.pageMaker = pageMaker;
		
	};
	
	
	public AugmentedPage sendAugmentedPage(String mouvement){
		String page = pageMaker.sendNewPage(mouvement);
		AugmentedPage apage = new AugmentedPage(page, "1");
		return apage;
	};
	
	
	public AugmentedPage firstAugmentedPages(String uri){
		String[] pagesList = pageMaker.setBook(uri);
		return null;
	}
}
