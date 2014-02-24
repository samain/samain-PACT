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
		return null;
	};
	
	public AugmentedPage firstAugmentedPages(String uri){
		String[] pageList = pageMaker.setBook(uri);
		return null;
	}
}
