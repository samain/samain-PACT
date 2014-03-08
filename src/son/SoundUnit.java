package Son;

public class SoundUnit implements SoundInterface {
	private MP3 mp3;
	
	public SoundUnit(){
		this.mp3 = null;
	}
	
	
	public void playSound(String uri) {
		if(mp3 != null) mp3.close();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {e.printStackTrace();}
		
		mp3 = new MP3(uri);
		mp3.start();
	}
}
