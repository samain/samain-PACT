package Classification;

import java.util.ArrayList;


public class EnrichAtmosphere {
	
	private String[] atmosphereText;
	private String[] atmosphereTextNewPage;
	private Champlexical atmosphereList;
	
//--------------------------------------------------------------------------------------------	
	//constructeur de la classe EnrichAtmosphere
	public EnrichAtmosphere(String atmosphereAdresse, String motsasupprimerAdresse){
		//cr�ation du tableau des mots utiles de l'atmosph�re
		this.atmosphereText = (new NetTab(new FileText((new ReadAFile(atmosphereAdresse)).contenuFichier(), 
				(new ReadAFile(motsasupprimerAdresse)).contenuFichier()))).getNetTab();
		//idem avec la m�thode getNetTabNewPage
		this.atmosphereTextNewPage = (new NetTab(new FileText((new ReadAFile(atmosphereAdresse)).contenuFichier(), 
				(new ReadAFile(motsasupprimerAdresse)).contenuFichier()))).getNetTabNewPage();
		//mise en place du champ lexical
		this.atmosphereList = new Champlexical((new NetTab(new FileText((new ReadAFile(atmosphereAdresse)).contenuFichier(), 
				(new ReadAFile(motsasupprimerAdresse)).contenuFichier()))));
	}
//-------------------------------------------------------------------------------------------------	
	//m�thode retournant un tableau contenant les mots utiles de l'atmosph�re
	public String[] getAtmosphereWords(){
		return atmosphereText;
	}
//---------------------------------------------------------------------------------------------------
	//idem
	public String[] getAtmosphereWordsNewPage(){
		return atmosphereTextNewPage;
	}
//---------------------------------------------------------------------------------------------------	
	//idem sous forme d'ArrayList
	public ArrayList<String> getAtmosphereWordsList(){
		ArrayList<String> maliste = new ArrayList<String>();
		for (int i=0; i<atmosphereText.length; i++){
			maliste.add(atmosphereText[i]);
		}	
		return maliste;
	}
//----------------------------------------------------------------------------------------------------	
	/*m�thode retournant un tableau, contenant les mots utiles des atmosph�res pr�c�demment ajout�s,
	  enrichi des mots utiles du nouveau fichier, mettant au passage le ChampLexical � jour*/
	public String[] enrichTheAtmosphere(String anotherTextAdresse, String motsasupprimerAdresse){
		FileText anotherTextFileText = new FileText((new ReadAFile(anotherTextAdresse)).contenuFichier(), 
				(new ReadAFile(motsasupprimerAdresse)).contenuFichier());
		return atmosphereList.addNewFile(anotherTextFileText);
	}
	

}
