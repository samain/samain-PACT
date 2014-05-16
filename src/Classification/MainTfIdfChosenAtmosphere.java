package Classification;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainTfIdfChosenAtmosphere {
  
	static String[] testatmo = 
	{"amour adoration culte v�n�ration d�votion pi�t� ferveur respect admiration passion extase penchant sentiment affection b�guin ardeur flamme tendresse affectueuse tendre affectionn�e amicale c�line caressante hypocoristique chaleureuse langoureuse alanguie languide languissante lascive voluptueuse doucereuse indolente charnelle �rotique l�g�re polissonne sensuelle suave tendrement passionn�ment transi �pris amoureux charme s�duction s�duire."
			, "Mais votre amoureux ne se laissera pas s�duire. Tout simplement parce que c�est vous qu�il aime. Parce que c�est vous qu�il veut, et personne d�autre. L�amour est d�abord bas� sur une confiance mutuelle. Si vous n�avez pas confiance en votre amoureux, il n�aura pas confiance en vous, et votre amour ne saura durer. Prenez soin de votre amour, prenez soin de votre amoureux. Sinon celui-ci risque de mourir. Sachez que si vous poss�dez un amour, vous poss�dez aussi une chance incomparable. Chaque amour est unique, chaque amour est puissant. Si vous ne poss�dez pas cet amour� Ne baissez jamais les bras, car l�amour vous attend. Mais il faut le trouver� Il est peut-�tre partout. Mais il est l�, soyez-en s�r(e)�"
			, "La justification th�orique de ce sch�ma de pond�ration repose sur l'observation empirique de la fr�quence des mots dans un texte qui est donn�e par la Loi de Zipf. Si une requ�te contient le terme T, un document a d'autant plus de chances d'y r�pondre qu'il contient ce terme : la fr�quence du terme au sein du document (TF) est grande. N�anmoins, si le terme T est lui-m�me tr�s fr�quent au sein du corpus, c'est-�-dire qu'il est pr�sent dans de nombreux documents (e.g. les articles d�finis - le, la, les), il est en fait peu discriminant. C'est pourquoi le sch�ma propose d'augmenter la pertinence d'un terme en fonction de sa raret� au sein du corpus (fr�quence du terme dans le corpus IDF �lev�e). Ainsi, la pr�sence d'un terme rare de la requ�te dans le contenu d'un document fait cro�tre le � score � de ce dernier."
			, "Son nom est c�l�br� par le bocage qui fr�mit, et par le ruisseau qui murmure, les vents l�emportent jusqu�� l�arc c�leste, l�arc de gr�ce et de consolation que sa main tendit dans les nuages."
			, "� peine distinguait-on deux buts � l�extr�mit� de la carri�re : des ch�nes ombrageaient l�un, autour de l�autre des palmiers se dessinaient dans l��clat du soir."
			, "Ah ! le beau temps de mes travaux po�tiques ! les beaux jours que j�ai pass�s pr�s de toi ! Les premiers, in�puisables de joie, de paix et de libert� ; les derniers, empreints d�une m�lancolie qui eut bien aussi ses charmes."
			
	};

	public static void main(String args[]) throws FileNotFoundException, IOException
    {
		
        BookReaderChosenAtmosphere at = new BookReaderChosenAtmosphere ();
        at.readPagesChosenAtmosphere(testatmo);//location of your source files, only text file
        at.tfIdfCalculatorChosenAtmosphere(); //calculates tfidf
        at.getCosineSimilarityChosenAtmosphere(); //calculated cosine similarity   
    }
}