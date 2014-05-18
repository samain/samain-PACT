package Classification;

import java.util.ArrayList;

//classe impl�mentant la m�thode pour garder les mots pertinents � analyser pour d�terminer l'ambiance 
// du texte

public class NetTab {
	
	private String[] brutTab;
	private String[] wordsToDelete;
	private ArrayList<String> wordsToDeleteList;
	private ArrayList<String> netList;
//-------------------------------------------------------------------------------------------
	//constructeur de la classe NetTab
	public NetTab(FileText fileText){
		this.brutTab= fileText.getBrutTab();
		this.wordsToDelete= fileText.getWordsToDelete();
		this.wordsToDeleteList = new ArrayList<String>();
		for (int i=0; i<wordsToDelete.length; i++) wordsToDeleteList.add(wordsToDelete[i]);
		this.netList = new ArrayList<String>();
	}	
//--------------------------------------------------------------------------------------------		
	//m�thode renvoyant le tableau des mots � utiliser pour analyser l'ambiance d'un texte
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
//-----------------------------------------------------------------------------------------------	
	//m�thode faisant la m�me chose que la pr�c�dente
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
//---------------------------------------------------------------------------------------	
	//m�thode renvoyant une ArrayList contenant les m�mes mots que le tablau ds mots � conserver
	public ArrayList<String> getNetList(String[] netTab){
		for (int i=0; i<netTab.length; i++){
			netList.add(netTab[i]);
		}
		return netList;
	}
		

}
