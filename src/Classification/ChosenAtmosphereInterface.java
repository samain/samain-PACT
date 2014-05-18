package Classification;

public interface ChosenAtmosphereInterface {
	/* Method to read files and store in array.
     */
    public void readPagesChosenAtmosphere(String[] book);
    
    /* Method to create termVector according to its tfidf score.
     */
    public void tfIdfCalculatorChosenAtmosphere();
    
    /*Method to calculate cosine similarity between all the documents.
     */
    public int getCosineSimilarityChosenAtmosphere();
}
