package Classification;

public class FileText {
	
	//récupère les mots dans le fchier à analyser et le fichers des mots à supprimer 
	
	private String[] brutTab;
	private String[] wordsToDelete;
	
	public FileText(String s1, String s2){
		this.brutTab = s1.split("[.,;:' ]+");
		this.wordsToDelete = s2.split("[.,;:' ]+");
	}
	
	public String[] getBrutTab(){
		return brutTab;
	}
	
	public String[] getWordsToDelete(){
		return wordsToDelete;
	}
		

}
