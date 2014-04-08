package myAnim;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanneauLivres extends JPanel {

	JLabel label = new JLabel("Livres"); // Marche quand on le marque dans la
											// fenêtre mais faut que j'essaie de
											// le mettre dans le JLabel

	protected void paintComponent(Graphics g) {

		try {
			Image img = ImageIO.read(new File(
					"/Users/paulinerabis/Documents/light.png"));

			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

