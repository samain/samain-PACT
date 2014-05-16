package Classification;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainTfIdfChosenBook {
  
	static String[] testbook = 
	{"Son nom est c�l�br� par le bocage qui fr�mit, et par le ruisseau qui murmure, les vents l�emportent jusqu�� l�arc c�leste, l�arc de gr�ce et de consolation que sa main tendit dans les nuages."
			,"amour adoration culte v�n�ration d�votion pi�t� ferveur respect admiration passion extase penchant sentiment affection b�guin ardeur flamme tendresse affectueuse tendre affectionn�e amicale c�line caressante hypocoristique chaleureuse langoureuse alanguie languide languissante lascive voluptueuse doucereuse indolente charnelle �rotique l�g�re polissonne sensuelle suave tendrement passionn�ment transi �pris amoureux charme s�duction s�duire."
			, "espace cosmologie cosmonaute astronautique astronaute astronautes spatial spatiale spatiales spatiaux univers plan�te plan�tes astronomie ast�ro�de ast�ro�des astre astres vide gravit� galaxie galaxies t�l�scope t�l�scopes t�lescope t�lescopes astronomes astronome com�te com�tesastres astre orbite orbites satellite satellites Mars Saturne Neptune Pluton Uranus Terre V�nus Venus"
			, "f�te f�tes �v�nement �v�nements c�l�brer c�l�bration c�l�bre c�l�bres c�l�brons c�l�brait c�l�brais c�l�braient festif, festin, festoyer, f�tard joie joyeux bruit bruits chant chants chanter cris rire rires anniversaire, f�ter festoyer amusement divertissement danse, bringue, danses danser dansait dansais c�l�bration, c�r�monie, c�r�monies festin banquet, banquets noce, noces festival, festivit�, gala festivit�s bal fil ruban rubans nattes illumin�"
			, "famille m�nage sang dynastie lignage descendance prog�niture g�n�alogie souche parent�le lign�e filiation ascendance couple �poux �pouse enfants parents descendants fils fille ascendants p�re m�re gendre bru oncle tante cousin cousine neveu ni�ce germain germaine parrain marraine a�eul familial"
			, "for�t massif forestier �tendue bois�e dense arbres esp�ce esp�ces boisement bois boqueteau bosquet esp�ce d�forestation tropical tropicales tropicale for�t clairi�re branche feuille pin ch�ne b�cheron bois buisson animaux"
			, "maison  baraque cabane masure chez-soi foyer toit salle de bains cuisine chambre salon toilettes"
			, "mer horizon une grande �tendue d'eau sal�e  des oc�ans, des espaces d'eau sal�e oc�an marin marines marine marins houle houles clapoti clapotis mar�e mar�es vent courant courants phare phares vague vagues mousse fruits fruit bateau bateaux voile voiles navire navires nager baigner bain baignade soleil mer appel du large bateau r�cif littoral coquillages coquillage lagunes lagune capitaine mouvants mouvant �cumes �cume embrun embruns abysses flux ports port �paves �pave �le    �le oc�an naufrages naufrage �chouage b�bord tribord rochers algues algue m�ts m�t go�mon go�lette golfes golfe baies baie estuaire brume poissons poisson p�che p�cheurs p�cheur navigateurs navigateur r�gates r�gate ancre scaphandre flotter flotte flottais flottes flottent flottait flottaient maritime estran amarre amarr� amarr�s  amarr�e amarr�es flot flots exotisme"
			, "montagne alpinisme escalade grimpe varappe randonn�e ascension mont hauteur �minence butte pic amas empillement tas montagneux alpestre alpin neigeux montueux accident� "
			, "mouill�, mouill�s tombaient fondaient fondait blancheur neige neiger flocon flocons cristaux cristallis� cristallis�e cristallis�es cristallis�s tombe tomber tombait neigeait neigeux manteau avalanche plaque verglas froid froide air poudre poudreux poudreuse fondre fond fondait ski skis skieurs pistes piste hiver fondu fondues fondue fondus blanc blancs blanche blanches givre givr� givr�s givr�e givr�es boule boules batailles bataille surf snow snowboard surfer enneig� enneig�e enneig�s enneig�es plaques verglac� vergalc�e verglac�s verglac�es neiger neigeoter alpin alpes Alpes glacial glisser luge luges glaciale blancheur"
			, "nuit nuits sombre sombres noir noire noires noirs coucher lune �toile �toiles filante filantes dormir dodo dors dorment dormons dormez dormais dormait dormaient dort obscurit� cauchemars  soir soir�e somnambulisme �toiles dormir �toile firmament nuit�e pyjama p�nombre obscurit� astre nuisetter�ve croissant aube ciel"
			, "rivage mer plage baln�aire plages baln�aires rivages vacances, bord, c�te, gr�ve, rivage mar�e �chou� �chou�e �chou�s �chou�es �tendue sable galet galets c�te espace gr�ve littoral rivage bronzer"
			, "building buildings ville villes population espace am�nag� concentrer activit�s habitat structure organisation urbain urbaine urbains urbaines urbanisme cit� cit�s immeubles immeuble pollution route routes autoroutes autoroute rue rues pi�ton pi�tons feux tricolores tricolore feu urbanisation commune communes m�tropole m�tropoles centre habitant habitants  art�re art�res rue rues ruelle ruelles"
			, "ciel nuage vent bocage ruisseau vents c�leste nuages"
	};

	public static void main(String args[]) throws FileNotFoundException, IOException
    {
		
        BookReaderChosenBook bk = new BookReaderChosenBook();
        bk.readPagesChosenBook(testbook);//location of your source files, only text file
        bk.tfIdfCalculatorChosenBook(); //calculates tfidf
        bk.getCosineSimilarityChosenBook(); //calculated cosine similarity   
    }
}