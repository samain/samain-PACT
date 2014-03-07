package Classification;

public class FileText {
	
	private String[] brutTab;
	private String[] wordsToDelete;
	
	public FileText(String s1, String s2){
		this.brutTab = s1.replace('\n', ' ').split("[.,;:' ]+");
		this.wordsToDelete = s2.split("[.,;:' ]+");
	}
	
	public String[] getBrutTab(){
		return brutTab;
	}
	
	public String[] getWordsToDelete(){
		return wordsToDelete;
	}
		

}
