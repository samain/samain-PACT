package Classification;

import java.util.ArrayList;

//classe implémentant la méthode pour garder les mots pertnants à analyser pour déterminer l'ambiance 
// du texte

public class NetTab {
	
	private String[] brutTab;
	private String[] wordsToDelete;
	private ArrayList<String> wordsToDeleteList;
	private ArrayList<String> netList;
	
	public NetTab(FileText fileText){
		this.brutTab= fileText.getBrutTab();
		this.wordsToDelete= fileText.getWordsToDelete();
		this.wordsToDeleteList = new ArrayList<String>();
		for (int i=0; i<wordsToDelete.length; i++) wordsToDeleteList.add(wordsToDelete[i]);
		this.netList = new ArrayList<String>();
	}	
		
	
	public String[] getNetTab(){
		ArrayList<String> motsagarder = new ArrayList<String>();
		ArrayList<String> motsasupprimer = new ArrayList<String>();
		for (int i=0; i<brutTab.length; i++){
			if (wordsToDeleteList.contains(brutTab[i])){
				motsasupprimer.add(brutTab[i]);
			}
			else motsagarder.add(brutTab[i]);			
		}
		String[] netTab = new String[motsagarder.size()];
		for (int j=0; j<netTab.length; j++){
			netTab[j]=motsagarder.get(j);
		}
		return netTab;
	}
	
	public String[] getNetTabNewPage(){
		ArrayList<String> motsagarder = new ArrayList<String>();
		ArrayList<String> motsasupprimer = new ArrayList<String>();
		for (int i=0; i<brutTab.length; i++){
			for (int k=0; k<wordsToDelete.length;k++){
				if (brutTab[i].equals(wordsToDelete[k])){
					motsasupprimer.add(brutTab[i]);
				}
			}
			if (!motsasupprimer.contains(brutTab[i])){
				motsagarder.add(brutTab[i]);
			}
		}
		String[] netTab = new String[motsagarder.size()];
		for (int j=0; j<netTab.length; j++){
			netTab[j]=motsagarder.get(j);
		}
		return netTab;
	}
	
	public ArrayList<String> getNetList(String[] netTab){
		for (int i=0; i<netTab.length; i++){
			netList.add(netTab[i]);
		}
		return netList;
	}
		

}
