package Son;

public class SoundUnit implements SoundInterface {

	public SoundUnit(){
		
	}
	
	
	public void playSound(String uri) {
		MP3 mp3 = new MP3(uri);
		mp3.play();
	}
}
