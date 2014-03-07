package Classification;

import java.util.ArrayList;


public class Champlexical {
	
	private ArrayList<String> champlexical;
	
	public Champlexical(NetTab netTab){
		this.champlexical = netTab.getNetList(netTab.getNetTab());
	}
	
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
