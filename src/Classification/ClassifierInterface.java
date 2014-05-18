package Classification;

import augmentedPage.AugmentedPage;

public interface ClassifierInterface {

	public AugmentedPage sendAugmentedPage(String mouvement); 
	/*renvoie une AugmentedPage fonction du mouvement de l'utilisateur 
	en demandant au bloc Découpage le texte à analyser et en l'analysant.*/
	
	//méthode indiquant si la page lue par le lecteur est la première
	public boolean isFirst();
	
	//méthode indiquant  si la page lue par le lecteur est la dernière
	public boolean isLast();
	
	//méthode choisissant le livre le mieux adapté à une atmosphère 
	public void chooseBook(int font, String atmosphereAdress);
	
	//méthode transmettant l'adresse du livre à lire
	public void setBook(int font, String textURI);
	
}
