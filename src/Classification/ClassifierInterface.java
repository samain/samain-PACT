package Classification;

import augmentedPage.AugmentedPage;

public interface ClassifierInterface {

	public AugmentedPage sendAugmentedPage(String mouvement); 
	/*renvoie une AugmentedPage fonction du mouvement de l'utilisateur 
	en demandant au bloc D�coupage le texte � analyser et en l'analysant.*/
	
	// public ArrayList<AugmentedPage> firstAugmentedPages();
	/*donne les trois premi�res pages du livre desin� � �tre lu*/
	
	public boolean isFirst();
	
	public boolean isLast();
	
}
