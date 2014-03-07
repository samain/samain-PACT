package Classification;

import java.util.ArrayList;

public class TextProcessing {
	private String[] atmosphereText;
	private Champlexical atmosphereList;
	
	public TextProcessing(String text, String motsasupprimerAdresse){
		this.atmosphereText = (new NetTab(new FileText(text, 
				(new ReadAFile(motsasupprimerAdresse)).contenuFichier()))).getNetTab();
		this.atmosphereList = new Champlexical((new NetTab(new FileText(text, 
				(new ReadAFile(motsasupprimerAdresse)).contenuFichier()))));
	}
	
	public String[] getAtmosphereWords(){
		return atmosphereText;
	}
	
	public ArrayList<String> getAtmosphereWordsList(){
		ArrayList<String> maliste = new ArrayList<String>();
		for (int i=0; i<atmosphereText.length; i++){
			maliste.add(atmosphereText[i]);
		}	
		return maliste;
	}
	
	public String[] enrichTheAtmosphere(String anotherTextAdresse, String motsasupprimerAdresse){
		FileText anotherTextFileText = new FileText((new ReadAFile(anotherTextAdresse)).contenuFichier(), 
				(new ReadAFile(motsasupprimerAdresse)).contenuFichier());
		return atmosphereList.addNewFile(anotherTextFileText);
	}
	

}

