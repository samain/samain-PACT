package Classification;

import java.util.ArrayList;

/*classe permetttant de déterminer le texte le plus approrié à une ambiance choisie grâce à la TFIDF*/

public class BookReaderChosenAtmosphere implements ChosenAtmosphereInterface {

	//This variable will hold all terms of each page in an array.
	private ArrayList<String[]> termsPagesArray = new ArrayList<String[]>();
	//to hold all terms each term appears only one time
    private ArrayList<String> allTerms = new ArrayList<String>();
  //tab of to hold for each page the TfIdf product of each term
    private ArrayList<double[]> tfidfPagesVector = new ArrayList<double[]>();
    
//--------------------------------------------------------------------------------------------
    /* Method to read files and store in array.
     */
    public void readPagesChosenAtmosphere(String[] book) {
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
//----------------------------------------------------------------------------------------
    /* Method to create termVector according to its tfidf score.
     */
    public void tfIdfCalculatorChosenAtmosphere() {
        double tf; //term frequency
        double idf; //inverse document frequency
        double tfidf; //term frequency inverse document frequency        
        for (String[] pageTermsArray : termsPagesArray) {
            double[] tfidfvectors = new double[allTerms.size()];
            int count = 0;
            /*on calcule la tfidf de chaque mot du texte et 
             on stocke les résultats dans un tableau*/
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
//--------------------------------------------------------------------------------------------------
    /*Method to calculate cosine similarity between all the documents.
     */
    public int getCosineSimilarityChosenAtmosphere() {
    	double maxlink = 0;
    	int num = 0;
    	    /*boucle for servant à déterminer le livre dont le texte dans son intégralité 
    	     correspond le mieux à l'ambiance choisie*/ 
            for (int j = 1; j < tfidfPagesVector.size(); j++) {
                if ( Math.max(maxlink, new CosineSimilarity().cosineSimilarity (tfidfPagesVector.get(0), tfidfPagesVector.get(j))) != maxlink ){
             	   num = j;
             	   maxlink = Math.max(maxlink, new CosineSimilarity().cosineSimilarity (tfidfPagesVector.get(0), tfidfPagesVector.get(j))); 
                }
            }
            return num;
            
            }
    
}
