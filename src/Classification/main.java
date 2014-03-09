package Classification; 

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//EnrichAtmosphere montagne = new EnrichAtmosphere("C://Users//Marie-Laure//Documents//Mer.txt", 
			//	"C://Users//Marie-Laure//Documents//StopWords.txt");
		//String[] montagneWords = montagne.getAtmosphereWords();
		//for (int i=0;i<montagneWords.length; i++) System.out.println(montagneWords[i]);
		
		
		//String[] ameliorateMontagneWords = montagne.enrichTheAtmosphere("C://Users//Marie-Laure//Documents//Montagne ajouts.txt",
			//	"C://Users//Marie-Laure//Documents//StopWords.txt");
		//for (int i=0;i<ameliorateMontagneWords.length; i++) System.out.println(ameliorateMontagneWords[i]);
		
		GetAtmosphere newpage = new GetAtmosphere();
		String[] atmosphere = newpage.getTheAtmosphere("C://Users//Marie-Laure//Documents//Nouvellepage.txt");
		System.out.println(atmosphere[0]);
		System.out.println(atmosphere[1]);
		
		
		

	}

}
