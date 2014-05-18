package Classification;

public class FileText {
	
	//r�cup�re les mots dans le fichier � analyser et le fichier des mots � supprimer 
	
	private String[] brutTab;
	private String[] wordsToDelete;
//-----------------------------------------------------------------------------
	//constructeur de la classe FileText
	public FileText(String s1, String s2){
		this.brutTab = s1.split("[.,;:' ]+");
		this.wordsToDelete = s2.split("[.,;:' ]+");
	}
//-------------------------------------------------------------------------------
	//m�thode retournant le tableau des mots du fichier � analyser
	public String[] getBrutTab(){
		return brutTab;
	}
//--------------------------------------------------------------------------------
	//m�thode retournant le tableau des mots � supprimer
	public String[] getWordsToDelete(){
		return wordsToDelete;
	}
}
