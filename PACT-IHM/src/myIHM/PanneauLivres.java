package myIHM;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanneauLivres extends JPanel {

	JLabel label = new JLabel("               Livres"); // Marche quand on le marque dans la
											// fenêtre mais faut que j'essaie de
											// le mettre dans le JLabel
	public PanneauLivres(){
		 this.setLayout(new BorderLayout());
		 this.add(label, BorderLayout.NORTH);
		 this.add(new IHMDefile());
	}
	protected void paintComponent(Graphics g) {

		try {
			Image img = ImageIO.read(new File(
					"/Users/paulinerabis/Documents/Images-PACT/light.png"));

			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}


