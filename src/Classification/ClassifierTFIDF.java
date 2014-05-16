package Classification;

import java.util.ArrayList;

import augmentedPage.AugmentedPage;
import decoupage.PageInterface;
import decoupage.PageMaker;

public class ClassifierTFIDF implements ClassifierInterface{
	
	private PageInterface pageMaker;
	
	private GetAtmosphere getAtmosphere;
	
	private BookReaderChosenBook book;
	
	private BookReaderChosenAtmosphere atmosphere;
	
	private FileToText fileToText;
	
	public ClassifierTFIDF(){
		
			getAtmosphere = new GetAtmosphere();
	
			atmosphere = new BookReaderChosenAtmosphere();
	
			book = new BookReaderChosenBook();
			
			fileToText = new FileToText();
			
	}
	
	public AugmentedPage sendAugmentedPage(String mouvement){
		long time1 = System.currentTimeMillis();
		String text = pageMaker.sendNewPage(mouvement);
		String[] atmosphereAdress = getAtmosphere.getTheAtmosphere(text);
		AugmentedPage augmentedPage = new AugmentedPage(text, atmosphereAdress);
		long time2 = System.currentTimeMillis();
		System.out.println("Classifier : sendAugmentedPAge : " +  (time2-time1));
		return augmentedPage;
	}
	
	public boolean isFirst(){
		return pageMaker.isFirst(); 
	}
	
	public boolean isLast(){
		return  pageMaker.isLast(); 
	}
	

	
	public void chooseBook(int font, String atmosphereAdress){
		ArrayList<String[]> list = fileToText.findBook(atmosphereAdress);
		String[] atmosphereAndBooks = list.get(1);
		atmosphere.readPagesChosenAtmosphere(atmosphereAndBooks);//location of your source files, only text file
        atmosphere.tfIdfCalculatorChosenAtmosphere(); //calculates tfidf
        int i = atmosphere.getCosineSimilarityChosenAtmosphere(); //calculated cosine similarity  
		System.out.println("chooseBook : " + i);
        System.out.println(list.get(0)[i]);     
        pageMaker = new PageMaker(list.get(0)[i], font);
        
	}
	
	public void setBook(int font, String textURI){
		pageMaker = new PageMaker(textURI, font);
	}
	
}
