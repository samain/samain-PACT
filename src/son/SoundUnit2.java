package son;

import java.io.File;
import java.net.MalformedURLException;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundUnit2 {
	private MediaPlayer mp;
	
	public SoundUnit2(){
		this.mp=null;
		JFXPanel fxPanel = new JFXPanel();
	}

	public void stop() {
		if(mp != null) mp.stop();
		mp = null;
	}

	public void play(String fileName) {
		if(mp != null){
			File song = new File(System.getProperty("user.dir") + fileName);
			Media media;
			try {
				media = new Media(song.toURI().toURL().toString());
				
				final MediaPlayer mp2 = new MediaPlayer(media);
				mp2.setVolume(0);
				mp2.play();
				
				final double volume = mp.getVolume();
				
				mp.volumeProperty().unbind();
				mp2.volumeProperty().unbind();
				
				Timeline fadeOut = new Timeline(new KeyFrame(Duration.seconds(3), new KeyValue(mp.volumeProperty(), 0,Interpolator.EASE_OUT)));
				fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						mp.stop();
					}
				});
				
				Timeline fadeIn = new Timeline(new KeyFrame(Duration.seconds(3), new KeyValue(mp2.volumeProperty(), volume,Interpolator.EASE_BOTH)));
				fadeIn.setOnFinished(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						mp = mp2;
					}
				});
				
				fadeOut.play();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				fadeIn.play();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		else {
			try{
				File song = new File(System.getProperty("user.dir") + fileName);
				mp = new MediaPlayer(new Media(song.toURI().toURL().toString()));
	            mp.setVolume(0.0);
	            mp.play();
	            
	            Timeline fadeIn = new Timeline(new KeyFrame(Duration.seconds(8), new KeyValue(mp.volumeProperty(), 1.0, Interpolator.EASE_BOTH)));
				
				fadeIn.play();
	            
			}
			catch(Exception ex)
			{ 
				ex.printStackTrace();
			}
			
		}
		
	}

}
