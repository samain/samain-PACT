package Classification;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainTfIdfChosenBook {
  
	static String[] testbook = 
	{"Son nom est célébré par le bocage qui frémit, et par le ruisseau qui murmure, les vents l’emportent jusqu’à l’arc céleste, l’arc de grâce et de consolation que sa main tendit dans les nuages."
			,"amour adoration culte vénération dévotion piété ferveur respect admiration passion extase penchant sentiment affection béguin ardeur flamme tendresse affectueuse tendre affectionnée amicale câline caressante hypocoristique chaleureuse langoureuse alanguie languide languissante lascive voluptueuse doucereuse indolente charnelle érotique légère polissonne sensuelle suave tendrement passionnément transi épris amoureux charme séduction séduire."
			, "espace cosmologie cosmonaute astronautique astronaute astronautes spatial spatiale spatiales spatiaux univers planète planètes astronomie astéroïde astéroïdes astre astres vide gravité galaxie galaxies téléscope téléscopes télescope télescopes astronomes astronome comète comètesastres astre orbite orbites satellite satellites Mars Saturne Neptune Pluton Uranus Terre Vénus Venus"
			, "fête fêtes événement événements célébrer célébration célèbre célèbres célébrons célébrait célébrais célébraient festif, festin, festoyer, fêtard joie joyeux bruit bruits chant chants chanter cris rire rires anniversaire, fêter festoyer amusement divertissement danse, bringue, danses danser dansait dansais célébration, cérémonie, cérémonies festin banquet, banquets noce, noces festival, festivité, gala festivités bal fil ruban rubans nattes illuminé"
			, "famille ménage sang dynastie lignage descendance progéniture généalogie souche parentèle lignée filiation ascendance couple époux épouse enfants parents descendants fils fille ascendants père mère gendre bru oncle tante cousin cousine neveu nièce germain germaine parrain marraine aïeul familial"
			, "forêt massif forestier étendue boisée dense arbres espèce espèces boisement bois boqueteau bosquet espèce déforestation tropical tropicales tropicale forêt clairière branche feuille pin chêne bûcheron bois buisson animaux"
			, "maison  baraque cabane masure chez-soi foyer toit salle de bains cuisine chambre salon toilettes"
			, "mer horizon une grande étendue d'eau salée  des océans, des espaces d'eau salée océan marin marines marine marins houle houles clapoti clapotis marée marées vent courant courants phare phares vague vagues mousse fruits fruit bateau bateaux voile voiles navire navires nager baigner bain baignade soleil mer appel du large bateau récif littoral coquillages coquillage lagunes lagune capitaine mouvants mouvant écumes écume embrun embruns abysses flux ports port épaves épave Île    île océan naufrages naufrage échouage bâbord tribord rochers algues algue mâts mât goémon goélette golfes golfe baies baie estuaire brume poissons poisson pêche pêcheurs pêcheur navigateurs navigateur régates régate ancre scaphandre flotter flotte flottais flottes flottent flottait flottaient maritime estran amarre amarré amarrés  amarrée amarrées flot flots exotisme"
			, "montagne alpinisme escalade grimpe varappe randonnée ascension mont hauteur éminence butte pic amas empillement tas montagneux alpestre alpin neigeux montueux accidenté "
			, "mouillé, mouillés tombaient fondaient fondait blancheur neige neiger flocon flocons cristaux cristallisé cristallisée cristallisées cristallisés tombe tomber tombait neigeait neigeux manteau avalanche plaque verglas froid froide air poudre poudreux poudreuse fondre fond fondait ski skis skieurs pistes piste hiver fondu fondues fondue fondus blanc blancs blanche blanches givre givré givrés givrée givrées boule boules batailles bataille surf snow snowboard surfer enneigé enneigée enneigés enneigées plaques verglacé vergalcée verglacés verglacées neiger neigeoter alpin alpes Alpes glacial glisser luge luges glaciale blancheur"
			, "nuit nuits sombre sombres noir noire noires noirs coucher lune étoile étoiles filante filantes dormir dodo dors dorment dormons dormez dormais dormait dormaient dort obscurité cauchemars  soir soirée somnambulisme étoiles dormir étoile firmament nuitée pyjama pénombre obscurité astre nuisetterêve croissant aube ciel"
			, "rivage mer plage balnéaire plages balnéaires rivages vacances, bord, côte, grève, rivage marée échoué échouée échoués échouées étendue sable galet galets côte espace grève littoral rivage bronzer"
			, "building buildings ville villes population espace aménagé concentrer activités habitat structure organisation urbain urbaine urbains urbaines urbanisme cité cités immeubles immeuble pollution route routes autoroutes autoroute rue rues piéton piétons feux tricolores tricolore feu urbanisation commune communes métropole métropoles centre habitant habitants  artère artères rue rues ruelle ruelles"
			, "ciel nuage vent bocage ruisseau vents céleste nuages"
	};

	public static void main(String args[]) throws FileNotFoundException, IOException
    {
		
        BookReaderChosenBook bk = new BookReaderChosenBook();
        bk.readPagesChosenBook(testbook);//location of your source files, only text file
        bk.tfIdfCalculatorChosenBook(); //calculates tfidf
        bk.getCosineSimilarityChosenBook(); //calculated cosine similarity   
    }
}