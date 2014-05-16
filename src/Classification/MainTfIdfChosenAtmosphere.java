package Classification;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainTfIdfChosenAtmosphere {
  
	static String[] testatmo = 
	{"amour adoration culte vénération dévotion piété ferveur respect admiration passion extase penchant sentiment affection béguin ardeur flamme tendresse affectueuse tendre affectionnée amicale câline caressante hypocoristique chaleureuse langoureuse alanguie languide languissante lascive voluptueuse doucereuse indolente charnelle érotique légère polissonne sensuelle suave tendrement passionnément transi épris amoureux charme séduction séduire."
			, "Mais votre amoureux ne se laissera pas séduire. Tout simplement parce que c’est vous qu’il aime. Parce que c’est vous qu’il veut, et personne d’autre. L’amour est d’abord basé sur une confiance mutuelle. Si vous n’avez pas confiance en votre amoureux, il n’aura pas confiance en vous, et votre amour ne saura durer. Prenez soin de votre amour, prenez soin de votre amoureux. Sinon celui-ci risque de mourir. Sachez que si vous possédez un amour, vous possédez aussi une chance incomparable. Chaque amour est unique, chaque amour est puissant. Si vous ne possédez pas cet amour… Ne baissez jamais les bras, car l’amour vous attend. Mais il faut le trouver… Il est peut-être partout. Mais il est là, soyez-en sûr(e)…"
			, "La justification théorique de ce schéma de pondération repose sur l'observation empirique de la fréquence des mots dans un texte qui est donnée par la Loi de Zipf. Si une requête contient le terme T, un document a d'autant plus de chances d'y répondre qu'il contient ce terme : la fréquence du terme au sein du document (TF) est grande. Néanmoins, si le terme T est lui-même très fréquent au sein du corpus, c'est-à-dire qu'il est présent dans de nombreux documents (e.g. les articles définis - le, la, les), il est en fait peu discriminant. C'est pourquoi le schéma propose d'augmenter la pertinence d'un terme en fonction de sa rareté au sein du corpus (fréquence du terme dans le corpus IDF élevée). Ainsi, la présence d'un terme rare de la requête dans le contenu d'un document fait croître le « score » de ce dernier."
			, "Son nom est célébré par le bocage qui frémit, et par le ruisseau qui murmure, les vents l’emportent jusqu’à l’arc céleste, l’arc de grâce et de consolation que sa main tendit dans les nuages."
			, "À peine distinguait-on deux buts à l’extrémité de la carrière : des chênes ombrageaient l’un, autour de l’autre des palmiers se dessinaient dans l’éclat du soir."
			, "Ah ! le beau temps de mes travaux poétiques ! les beaux jours que j’ai passés près de toi ! Les premiers, inépuisables de joie, de paix et de liberté ; les derniers, empreints d’une mélancolie qui eut bien aussi ses charmes."
			
	};

	public static void main(String args[]) throws FileNotFoundException, IOException
    {
		
        BookReaderChosenAtmosphere at = new BookReaderChosenAtmosphere ();
        at.readPagesChosenAtmosphere(testatmo);//location of your source files, only text file
        at.tfIdfCalculatorChosenAtmosphere(); //calculates tfidf
        at.getCosineSimilarityChosenAtmosphere(); //calculated cosine similarity   
    }
}