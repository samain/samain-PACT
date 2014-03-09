package Classification;

import java.util.ArrayList;
import java.util.Arrays;


public class GetAtmosphere {
	
	private ArrayList<String> montagne;
	private ArrayList<String> plage;
	private ArrayList<String> foret;
	private ArrayList<String> mer;
	private ArrayList<String> fete;
	private ArrayList<String> ville;
	private ArrayList<String> espace;
	private ArrayList<String> nuit;
	private ArrayList<String> neige;
	private ArrayList<String> amour;
	private ArrayList<String> famille;
	private ArrayList<String> maison;
	private ArrayList<String> montagneImages;
	private ArrayList<String> plageImages;
	private ArrayList<String> foretImages;
	private ArrayList<String> merImages;
	private ArrayList<String> feteImages;
	private ArrayList<String> villeImages;
	private ArrayList<String> espaceImages;
	private ArrayList<String> nuitImages;
	private ArrayList<String> neigeImages;
	private ArrayList<String> amourImages;
	private ArrayList<String> familleImages;
	private ArrayList<String> maisonImages;
	private String montagneMusic;
	private String plageMusic; 
	private String foretMusic;
	private String merMusic;
	private String feteMusic;
	private String villeMusic;
	private String espaceMusic;
	private String nuitMusic;
	private String neigeMusic;
	private String amourMusic;
	private String familleMusic;
	private String maisonMusic;
	
private String resourcesAdress;
	
	public GetAtmosphere(){
		this.resourcesAdress = System.getProperty("user.dir")+"//Ressources";
		this.montagne = (new EnrichAtmosphere(this.resourcesAdress +"//Montagne.txt", 
				this.resourcesAdress +"//StopWords.txt")).getAtmosphereWordsList();
		this.plage = (new EnrichAtmosphere(this.resourcesAdress +"//Plage.txt", 
				this.resourcesAdress +"//StopWords.txt")).getAtmosphereWordsList();
		this.foret = (new EnrichAtmosphere(this.resourcesAdress +"//Forêt.txt", 
				this.resourcesAdress+"//StopWords.txt")).getAtmosphereWordsList();
		this.mer = (new EnrichAtmosphere(this.resourcesAdress+"//Mer.txt", 
				this.resourcesAdress+"//StopWords.txt")).getAtmosphereWordsList();
		this.fete = (new EnrichAtmosphere(this.resourcesAdress+"//Fête.txt", 
				this.resourcesAdress+"//StopWords.txt")).getAtmosphereWordsList();
		this.ville = (new EnrichAtmosphere(this.resourcesAdress+"//Ville.txt", 
				this.resourcesAdress+"//StopWords.txt")).getAtmosphereWordsList();
		this.espace= (new EnrichAtmosphere(this.resourcesAdress+"//Espace.txt", 
				this.resourcesAdress+"//StopWords.txt")).getAtmosphereWordsList();
		this.nuit = (new EnrichAtmosphere(this.resourcesAdress+"//Nuit.txt", 
				this.resourcesAdress+"//StopWords.txt")).getAtmosphereWordsList();
		this.neige = (new EnrichAtmosphere(this.resourcesAdress+"//Neige.txt", 
				this.resourcesAdress+"//StopWords.txt")).getAtmosphereWordsList();
		this.amour = (new EnrichAtmosphere(this.resourcesAdress+"//Amour.txt", 
				this.resourcesAdress+"//StopWords.txt")).getAtmosphereWordsList();
		this.famille = (new EnrichAtmosphere(this.resourcesAdress+"//Famille.txt", 
				this.resourcesAdress+"//StopWords.txt")).getAtmosphereWordsList();
		this.maison = (new EnrichAtmosphere(this.resourcesAdress+"//Maison.txt", 
				this.resourcesAdress+"//StopWords.txt")).getAtmosphereWordsList();
		this.montagneImages = new ArrayList<String>();
		montagneImages.add("Montagne//montagne1.jpg");
		montagneImages.add("Montagne//montagne2.jpg");
		montagneImages.add("Montagne//montagne3.jpg");
		montagneImages.add("Montagne//montagne4.jpg");
		montagneImages.add("Montagne//montagne5.jpg");
		this.plageImages = new ArrayList<String>();
		plageImages.add("Plage//plage1.jpg");
		plageImages.add("Plage//plage2.jpg");
		plageImages.add("Plage//plage3.jpg");
		plageImages.add("Plage//plage4.jpg");
		this.foretImages = new ArrayList<String>();
		foretImages.add("Foret//foret1.jpg");
		foretImages.add("Foret//foret2.jpg");
		foretImages.add("Foret//foret3.jpg");
		foretImages.add("Foret//foret4.jpg");
		this.merImages = new ArrayList<String>();
		merImages.add("Mer//mer1.jpg");
		merImages.add("Mer//mer2.jpg");
		merImages.add("Mer//mer3.jpg");
		merImages.add("Mer//mer4.jpg");
		this.feteImages = new ArrayList<String>();
		feteImages.add("Fete//fete1.jpg");
		feteImages.add("Fete//fete2.jpg");
		this.villeImages = new ArrayList<String>();
		villeImages.add("Ville//ville1.jpg");
		villeImages.add("Ville//ville2.jpg");
		villeImages.add("Ville//ville3.jpg");
		this.espaceImages = new ArrayList<String>();
		espaceImages.add("Espace//espace1.jpg");
		espaceImages.add("Espace//espace2.jpg");
		espaceImages.add("Espace//espace3.jpg");
		espaceImages.add("Espace//espace4.jpg");
		espaceImages.add("Espace//espace5.jpg");
		this.nuitImages = new ArrayList<String>();
		nuitImages.add("Nuit//nuit1.jpg");
		nuitImages.add("Nuit//nuit2.jpg");
		nuitImages.add("Nuit//nuit3.jpg");
		nuitImages.add("Nuit//nuit4.jpg");
		this.neigeImages = new ArrayList<String>();
		neigeImages.add("Neige//neige1.jpg");
		neigeImages.add("Neige//neige2.jpg");
		neigeImages.add("Neige//neige3.jpg");
		neigeImages.add("Neige//neige4.jpg");
		this.amourImages = new ArrayList<String>();
		amourImages.add("Amour//amour1.jpg");
		amourImages.add("Amour//amour2.jpg");
		amourImages.add("Amour//amour3.jpg");
		this.familleImages = new ArrayList<String>();
		familleImages.add("Famille//famille1.jpg");
		familleImages.add("Famille//famille2.jpg");
		familleImages.add("Famille//famille3.jpg");
		this.maisonImages = new ArrayList<String>();
		maisonImages.add("Maison//maison1.jpg");
		maisonImages.add("Maison//maison2.jpg");
		maisonImages.add("Maison//maison3.jpg");
	}
	
	public String[] getTheAtmosphere(String newPage){
		TextProcessing newPage2 = new TextProcessing(newPage,this.resourcesAdress+"//StopWords.txt");
		System.out.println(newPage);
		String[] newPageWords= newPage2.getAtmosphereWords();
		int mont = 0;
		int pla = 0;
		int fo = 0;
		int sea = 0;
		int fet = 0;
		int vil = 0;
		int espa = 0;
		int nui = 0;
		int neig = 0;
		int amo = 0;
		int fami = 0;
		int mais =0;
		int nowherewords = 0;
		for (int i=0; i<newPageWords.length; i++){
			if (montagne.contains(newPageWords[i])) mont=mont+1;
			if (plage.contains(newPageWords[i])) pla=pla+1;
			if (foret.contains(newPageWords[i])) fo=fo+1;
			if (mer.contains(newPageWords[i])) sea=sea+1;
			if (fete.contains(newPageWords[i])) fet=fet+1;
			if (ville.contains(newPageWords[i])) vil=vil+1;
			if (espace.contains(newPageWords[i])) espa=espa+1;
			if (nuit.contains(newPageWords[i])) nui=nui+1;
			if (neige.contains(newPageWords[i])) neig=neig+1;
			if (amour.contains(newPageWords[i])) amo=amo+1;
			if (famille.contains(newPageWords[i])) fami=fami+1;
			if (maison.contains(newPageWords[i])) {mais=mais+1;
			System.out.println(newPageWords[i]);
			}
			else {nowherewords = nowherewords +1;
			System.out.println(newPageWords[i]);
			}
		}
		int[] resultat = new int[12];
		resultat[0] = mont;
		resultat[1] = pla;
		resultat[2] = fo;
		resultat[3] = sea;
		resultat[4] = fet;
		resultat[5] = vil;
		resultat[6] = espa;
		resultat[7] = nui;
		resultat[8] = neig;
		resultat[9] = amo;
		resultat[10] = fami;
		resultat[11] = mais;
		Arrays.sort(resultat);
		String[] reponse = new String[2];
		if (mont==0 && pla==0 && fo==0 && sea==0 && fet==0 && vil==0 && espa==0 && nui==0 && neig==0 && amo==0 && fami==0 && mais==0){
			reponse[0] = "Atmosphère non trouvée...";
			reponse[1] = "Atmosphère non trouvée...";
		}
		if (resultat[11]==mont) {
			int hasard = (int) (Math.random() * (montagneImages.size()-1));
			reponse[0] = montagneImages.get(hasard);
			reponse[1] = montagneMusic;
		}
		if (resultat[11]==pla) {
			int hasard = (int) (Math.random() * (plageImages.size()-1));
			reponse[0] = plageImages.get(hasard);
			reponse[1] = plageMusic;
		}
		if (resultat[11]==fo) {
			int hasard = (int) (Math.random() * (foretImages.size()-1));
			reponse[0] = foretImages.get(hasard);
			reponse[1] = foretMusic;
		}
		if (resultat[11]==sea) {
			int hasard = (int) (Math.random() * (merImages.size()-1));
			reponse[0] = merImages.get(hasard);
			reponse[1] = merMusic;
		}
		if (resultat[11]==fet) {
			int hasard = (int) (Math.random() * (feteImages.size()-1));
			reponse[0] = feteImages.get(hasard);
			reponse[1] = feteMusic;
		}
		if (resultat[11]==vil) {
			int hasard = (int) (Math.random() * (villeImages.size()-1));
			reponse[0] = villeImages.get(hasard);
			reponse[1] = villeMusic;
		}
		if (resultat[11]==espa) {
			int hasard = (int) (Math.random() * (espaceImages.size()-1));
			reponse[0] = espaceImages.get(hasard);
			reponse[1] = espaceMusic;
		}
		if (resultat[11]==nui) {
			int hasard = (int) (Math.random() * (nuitImages.size()-1));
			reponse[0] = nuitImages.get(hasard);
			reponse[1] = nuitMusic;
		}
		if (resultat[11]==neig) {
			int hasard = (int) (Math.random() * (neigeImages.size()-1));
			reponse[0] = neigeImages.get(hasard);
			reponse[1] = neigeMusic;
		}
		if (resultat[11]==amo) {
			int hasard = (int) (Math.random() * (amourImages.size()-1));
			reponse[0] = amourImages.get(hasard);
			reponse[1] = amourMusic;
		}
		if (resultat[11]==fami) {
			int hasard = (int) (Math.random() * (familleImages.size()-1));
			reponse[0] = familleImages.get(hasard);
			reponse[1] = familleMusic;
		}
		if (resultat[11]==mais) {
			int hasard = (int) (Math.random() * (maisonImages.size()-1));
			reponse[0] = maisonImages.get(hasard);
			reponse[1] = maisonMusic;
		}
		return reponse;
				
	}

}
