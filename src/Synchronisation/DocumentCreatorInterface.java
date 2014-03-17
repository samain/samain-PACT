package Synchronisation;

import org.w3c.dom.Document;

import augmentedPage.AugmentedPage;

public interface DocumentCreatorInterface {

	public Document createDocument(AugmentedPage augmentedPage);
	
	public Document createDocument(AugmentedPage first, AugmentedPage second);
	
}
