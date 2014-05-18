package Classification;

import augmentedPage.AugmentedPage;

public interface ClassifierInterface {

	public AugmentedPage sendAugmentedPage(String mouvement); 
	/*renvoie une AugmentedPage fonction du mouvement de l'utilisateur 
	en demandant au bloc D�coupage le texte � analyser et en l'analysant.*/
	
	//m�thode indiquant si la page lue par le lecteur est la premi�re
	public boolean isFirst();
	
	//m�thode indiquant  si la page lue par le lecteur est la derni�re
	public boolean isLast();
	
	//m�thode choisissant le livre le mieux adapt� � une atmosph�re 
	public void chooseBook(int font, String atmosphereAdress);
	
	//m�thode transmettant l'adresse du livre � lire
	public void setBook(int font, String textURI);
	
}
