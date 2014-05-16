package Classification;

import java.util.ArrayList;


/**
 * Class to read documents
 */
public class BookReaderChosenBook {
	
		private ArrayList<String[]> termsPagesArray = new ArrayList<String[]>(); //This variable will hold all terms of each page in an array.
	    private ArrayList<String> allTerms = new ArrayList<String>(); //to hold all terms each term appears only one time 
	    private ArrayList<double[]> tfidfPagesVector = new ArrayList<double[]>(); //tab of to hold for each page the TfIdf product of each term

    /* Method to read files and store in array.
     */
    public void readPagesChosenBook(String[] book) {
        for (String p : book) {
                String[] tokenizedTerms = p.split(" ") ; //to get individual terms
                for (String term : tokenizedTerms) {
                    if (!allTerms.contains(term)) {  //avoid duplicate entry
                        allTerms.add(term);
                    }
                }
                termsPagesArray.add(tokenizedTerms);
            }
        }


    /* Method to create termVector according to its tfidf score.
     */
    public void tfIdfCalculatorChosenBook() {
        double tf; //term frequency
        double idf; //inverse document frequency
        double tfidf; //term frequency inverse document frequency        
        for (String[] pageTermsArray : termsPagesArray) {
            double[] tfidfvectors = new double[allTerms.size()];
            int count = 0;
            for (String terms : allTerms) {
                tf = new TfIdf().tfCalculator(pageTermsArray, terms);
                idf = new TfIdf().idfCalculator(termsPagesArray, terms);
                tfidf = tf * idf;
                tfidfvectors[count] = tfidf;
                count++;
            }
            tfidfPagesVector.add(tfidfvectors);  //storing document vectors;            
        }
    }

    /*Method to calculate cosine similarity between all the documents.
     */
    public void getCosineSimilarityChosenBook() {
    	double maxlink = 0;
    	int num = 0;
    	String[] theme= { "Page", "Amour", "Espace", "Fête", "Famille", "Foret", "Maison", "Mer", "Montagne", "Neige", "Nuit", "Plage", "Ville", "Ciel"};
            for (int j = 1; j < tfidfPagesVector.size(); j++) {
                System.out.println("between the page and " + theme[j] + "  =  " + new CosineSimilarity().cosineSimilarity
                                       (
                                         tfidfPagesVector.get(0), 
                                         tfidfPagesVector.get(j)
                                       )
                                  );
               if ( Math.max(maxlink, new CosineSimilarity().cosineSimilarity (tfidfPagesVector.get(0), tfidfPagesVector.get(j))) != maxlink ){
            	   num = j;
            	   maxlink = Math.max(maxlink, new CosineSimilarity().cosineSimilarity (tfidfPagesVector.get(0), tfidfPagesVector.get(j))); 
               }
            }
            
            System.out.println("Meilleure ambiance " + theme[num] );
    }
}