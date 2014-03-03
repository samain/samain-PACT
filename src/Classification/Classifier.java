package Classification;

import augmentedPage.AugmentedPage;
import decoupage.*;
import java.util.*;

public class Classifier implements ClassifierInterface {

	private PageMaker pageMaker;
	
	public Classifier(PageMaker pageMaker){
		
		this.pageMaker = pageMaker;
		
	};
	
	public AugmentedPage sendAugmentedPage(String mouvement){
		String page = pageMaker.sendNewPage(mouvement);
		AugmentedPage aP = new AugmentedPage(page, "2.jpg");
		return aP;
	};
	
	public ArrayList<AugmentedPage> firstAugmentedPages(String uri){
		String[] pageList = pageMaker.setBook(uri);
		ArrayList<AugmentedPage> aPL = new ArrayList<AugmentedPage>();
		for(int i = 0; i<2; i++){
			AugmentedPage aP = new AugmentedPage(pageList[i], "1.jpg");
			aPL.add(aP);
		}
		AugmentedPage aP = new AugmentedPage(pageList[2], "2.jpg");
		aPL.add(aP);
		return aPL;
	}
}
