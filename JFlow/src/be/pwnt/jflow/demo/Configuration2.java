package be.pwnt.jflow.demo;

import java.io.File;

import be.pwnt.jflow.Shape;
import be.pwnt.jflow.shape.Picture;

public class Configuration2 extends be.pwnt.jflow.Configuration {
	public Configuration2() {
		shapes = new Shape[3];
		file = new File(System.getProperty("user.dir")+"Images-PACT/cubes.jpg");
		/*
		shapes = new Shape[9];
		for (int i = 0; i < shapes.length; i++) {
			try {
				shapes[i] = new Picture(getClass().getResource(
						"img/pic" + (i + 1) + ".jpg"));
			} catch (IOException e) {
			}
		}
		*/
		try {
			shapes[0] = new Picture(getClass().getResource(
					"img/aventure.jpg"));
			shapes[1] = new Picture(getClass().getResource(
					"img/nuit.jpg"));
			shapes[2] = new Picture(getClass().getResource(
					"img/relaxation.jpg"));
			/*shapes[3] = new Picture(getClass().getResource(
					"img/bfg.jpg"));
			shapes[4] = new Picture(getClass().getResource(
					"img/voyage.jpg"));
			shapes[5] = new Picture(getClass().getResource(
					"img/gulliver.png"));
			shapes[6] = new Picture(getClass().getResource(
					"img/histoire-grand-nord.jpg"));
			shapes[7] = new Picture(getClass().getResource(
					"img/raisins.png"));*/
				
		} catch (Exception e) {
			System.out.println("Exception a l'ouverture du cover Flow : " + e.getMessage());
		}
		
		//backgroundColor = new Color(255, 255, 255, 0);
	}
}
