package be.pwnt.jflow.demo;

import java.awt.Color;
import java.io.IOException;

import be.pwnt.jflow.Shape;
import be.pwnt.jflow.shape.Picture;

public class Configuration2  extends be.pwnt.jflow.Configuration {
	public Configuration2() {
		shapes = new Shape[9];
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
		    System.out.println("chargement amour");
			shapes[0] = new Picture("Amour.txt", getClass().getResource(
					"img/amour1.jpg"));
			System.out.println("chargement espace");
			shapes[1] = new Picture("Espace.txt", getClass().getResource(
					"img/espace1.jpg"));
			System.out.println("chargement famille");
		    /* shapes[2] = new Picture("Famille.txt", getClass().getResource(
					"img/famille1.jpg")); */ 
			System.out.println("chargement fete");
			shapes[2] = new Picture("Fête.txt", getClass().getResource(
					"img/fete1.jpg"));
			System.out.println("chargement foret");
		   /* shapes[4] = new Picture("Forêt.txt", getClass().getResource(
					"img/foret1.jpg")); */			
			System.out.println("chargement maison"); 
			/* shapes[5] = new Picture("Maison.txt" ,getClass().getResource(
					"img/maison1.jpg")); */ 
			System.out.println("chargement mer"); 
			 shapes[3] = new Picture("Mer.txt" ,getClass().getResource(
					"img/mer1.jpg")); 
			System.out.println("chargement montagne");
			shapes[4] = new Picture("Montagne.txt" ,getClass().getResource(
					"img/montagne1.jpg"));
			System.out.println("chargement neige");
			shapes[5] = new Picture("Neige.txt" ,getClass().getResource(
					"img/neige1.jpg"));
			System.out.println("chargement nuit");
			shapes[6] = new Picture("Nuit.txt" ,getClass().getResource(
					"img/nuit1.jpg"));
			 System.out.println("chargement plage");
			shapes[7] = new Picture("Plage.txt" ,getClass().getResource(
					"img/plage1.jpg"));
			System.out.println("chargement ville");
			shapes[8] = new Picture("Ville.txt" ,getClass().getResource(
					"img/ville1.jpg")); 
			
				
		} catch (Exception e) {
			System.out.println("Exception a l'ouverture du cover Flow : " + e.getMessage());
		}
		
		//backgroundColor = new Color(255, 255, 255, 0);
	}
}

