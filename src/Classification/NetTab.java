package Classification;

import java.util.ArrayList;


public class NetTab {
	
	private String[] brutTab;
	private String[] wordsToDelete;
	private ArrayList<String> netList;
	
	public NetTab(FileText fileText){
		this.brutTab= fileText.getBrutTab();
		this.wordsToDelete= fileText.getWordsToDelete();
		this.netList = new ArrayList<String>();
	}	
		
	
	public String[] getNetTab(){
		ArrayList<String> motsagarder = new ArrayList<String>();
		ArrayList<String> motsasupprimer = new ArrayList<String>();
		for (int i=0; i<brutTab.length; i++){
			for (int k=0; k<wordsToDelete.length;k++){
				if (brutTab[i].equals(wordsToDelete[k])){
					motsasupprimer.add(brutTab[i]);
				}
			}
			if (!motsasupprimer.contains(brutTab[i])){
				if (!motsagarder.contains(brutTab[i])){
				motsagarder.add(brutTab[i]);
				}
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
