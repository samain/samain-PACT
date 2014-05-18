package Classification;

public class FileText {
	
	//récupère les mots dans le fichier à analyser et le fichier des mots à supprimer 
	
	private String[] brutTab;
	private String[] wordsToDelete;
//-----------------------------------------------------------------------------
	//constructeur de la classe FileText
	public FileText(String s1, String s2){
		this.brutTab = s1.split("[.,;:' ]+");
		this.wordsToDelete = s2.split("[.,;:' ]+");
	}
//-------------------------------------------------------------------------------
	//méthode retournant le tableau des mots du fichier à analyser
	public String[] getBrutTab(){
		return brutTab;
	}
//--------------------------------------------------------------------------------
	//méthode retournant le tableau des mots à supprimer
	public String[] getWordsToDelete(){
		return wordsToDelete;
	}
}
