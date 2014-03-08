package AugmentedPage;


public class AugmentedPage {

	private String page;
	private String ambiance;
	
	public AugmentedPage(String page, String ambiance){
		this.page = page;
		this.ambiance = ambiance;
	}
	
	public String getText(){
		return page;
	}
	
	public String getAmbiance(){
		return ambiance;
	}
}