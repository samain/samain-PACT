package be.pwnt.jflow.demo;

import java.awt.Color;
import java.io.IOException;

import be.pwnt.jflow.Shape;
import be.pwnt.jflow.shape.Picture;

public class Configuration2  extends be.pwnt.jflow.Configuration {
	public Configuration2() {
		shapes = new Shape[9];
		try {
			shapes[0] = new Picture("Amour.txt", getClass().getResource(
					"img/amour1.jpg"));
			shapes[1] = new Picture("Espace.txt", getClass().getResource(
					"img/espace1.jpg"));
			shapes[2] = new Picture("Fête.txt", getClass().getResource(
					"img/fete1.jpg"));
			 shapes[3] = new Picture("Mer.txt" ,getClass().getResource(
					"img/mer1.jpg"));
			shapes[4] = new Picture("Montagne.txt" ,getClass().getResource(
					"img/montagne1.jpg"));
			shapes[5] = new Picture("Neige.txt" ,getClass().getResource(
					"img/neige1.jpg"));
			shapes[6] = new Picture("Nuit.txt" ,getClass().getResource(
					"img/nuit1.jpg"));
			shapes[7] = new Picture("Plage.txt" ,getClass().getResource(
					"img/plage1.jpg"));
			shapes[8] = new Picture("Ville.txt" ,getClass().getResource(
					"img/ville1.jpg")); 
			
				
		} catch (Exception e) {
			System.out.println("Exception a l'ouverture du cover Flow : " + e.getMessage());
		}
	}
}

