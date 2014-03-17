package Classification;

import java.util.ArrayList;

import augmentedPage.AugmentedPage;

public interface ClassifierInterface {

	public AugmentedPage sendAugmentedPage(String mouvement); 
	/*renvoie une AugmentedPage fonction du mouvement de l'utilisateur 
	en demandant au bloc Découpage le texte à analyser et en l'analysant.*/
	
	public ArrayList<AugmentedPage> firstAugmentedPages();
	/*donne les trois premières pages du livre desiné à être lu*/
	
	
}
