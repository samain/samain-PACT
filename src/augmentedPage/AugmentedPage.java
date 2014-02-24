package augmentedPage;

public class AugmentedPage implements AugmentedPageInterface {

	private String page;
	private String image;
	private String sound;
	
	public AugmentedPage(String page, String image, String sound){
		this.page = page;
		this.image = image;
		this.sound = sound;
	};
	
	public String getText(){
		return page;
	};
	
	public String getImage(){
		return image;
	};
	
	public String getSound(){
		return sound;
	};
}