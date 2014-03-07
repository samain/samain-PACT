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
	
	public String getTheAtmosphere(String newPage){
		TextProcessing newPage2 = new TextProcessing(newPage,this.resourcesAdress+"//StopWords.txt");
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
		int fa = 0;
		int am = 0;
		int mais = 0;
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
			if (famille.contains(newPageWords[i])) fa++;
			if (amour.contains(newPageWords[i])) am++;
			if (maison.contains(newPageWords[i])) {mais++;
			System.out.println((new Integer(fa)).toString());
			}
			else nowherewords = nowherewords +1;
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
		resultat[9] = fa;
		resultat[10] = am;
		resultat[11] = mais;
		Arrays.sort(resultat);
		String reponse = "";
		String adresseImage = "";
		if (mont==0 && pla==0 && fo==0 && sea==0 && fet==0 && vil==0 && espa==0 && nui==0 && neig==0 && fa == 0 && am == 0 && mais == 0) return "1.jpg";
		if (resultat[11]==mont) {
			reponse = reponse + " Montagne";
			int hasard = (int) (Math.random() * (montagneImages.size()-1));
			adresseImage = montagneImages.get(hasard);
		}
		if (resultat[11]==pla) {
			reponse = reponse + " Plage";
			int hasard = (int) (Math.random() * (plageImages.size()-1));
			adresseImage = plageImages.get(hasard);
		}
		if (resultat[11]==fo) {
			reponse = reponse + " Forêt";
			int hasard = (int) (Math.random() * (foretImages.size()-1));
			adresseImage = foretImages.get(hasard);
		}
		if (resultat[11]==sea) {
			reponse = reponse + " Mer";
			int hasard = (int) (Math.random() * (merImages.size()-1));
			adresseImage = merImages.get(hasard);
		}
		if (resultat[11]==fet) {
			reponse = reponse + " Fête";
			int hasard = (int) (Math.random() * (feteImages.size()-1));
			adresseImage = feteImages.get(hasard);
		}
		if (resultat[11]==vil) {
			reponse = reponse + " Ville";
			int hasard = (int) (Math.random() * (villeImages.size()-1));
			adresseImage = villeImages.get(hasard);
		}
		if (resultat[11]==espa) {
			reponse = reponse + " Espace";
			int hasard = (int) (Math.random() * (espaceImages.size()-1));
			adresseImage = espaceImages.get(hasard);
		}
		if (resultat[11]==nui) {
			reponse = reponse + " Nuit";
			int hasard = (int) (Math.random() * (nuitImages.size()-1));
			adresseImage = nuitImages.get(hasard);
		}
		if (resultat[11]==neig) {
			reponse = reponse + " Neige";
			int hasard = (int) (Math.random() * (neigeImages.size()-1));
			adresseImage = neigeImages.get(hasard);
		}
		if (resultat[11]==fa) {
			reponse = reponse + " Famille";
			int hasard = (int) (Math.random() * (familleImages.size()-1));
			adresseImage = familleImages.get(hasard);
		}
		if (resultat[11]==am) {
			reponse = reponse + " Amour";
			int hasard = (int) (Math.random() * (amourImages.size()-1));
			adresseImage = amourImages.get(hasard);
		}
		if (resultat[11]==mais) {
			reponse = reponse + " Maison";
			int hasard = (int) (Math.random() * (maisonImages.size()-1));
			adresseImage = maisonImages.get(hasard);
		}
		return adresseImage;
				
	}

}
