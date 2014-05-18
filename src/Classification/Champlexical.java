package Classification;

import java.util.ArrayList;

/*classe permettant de stocker tous les mots utiles à analyser*/

public class Champlexical {
	
	private ArrayList<String> champlexical;
	
//--------------------------------------------------------------------------------------------	
	//constructeur de la classe ChampLexical
	public Champlexical(NetTab netTab){
		this.champlexical = netTab.getNetList(netTab.getNetTab());
	}
//----------------------------------------------------------------------------------------------	
	//méthode augmentant le champ lexical des mots utiles d'un fichier
	public String[] addNewFile(FileText fileText){
		NetTab netTabb = new NetTab(fileText);
		String[] myNewFile = netTabb.getNetTab();
		for (int i=0; i<myNewFile.length; i++){
			if (!champlexical.contains(myNewFile[i])){
				champlexical.add(myNewFile[i]);
			}
		}
		String[] newNetTab = new String[champlexical.size()];
		for (int j=0; j<champlexical.size(); j++){
			newNetTab[j] = champlexical.get(j);
		}
		return newNetTab;
	}

	
}
