package Classification;

import augmentedPage.*;

public interface ClassifierInterface {

	public AugmentedPage sendAugmentedPage(String mouvement); 
	/*renvoie une AugmentedPage fonction du mouvement de l'utilisateur 
	en demandant au bloc D�coupage le texte � analyser et en l'analysant.*/
	
}
