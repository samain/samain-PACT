package myIHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanneauLivres extends JPanel{
	private Bouton gauche = new Bouton("", new File("/Users/paulinerabis/Documents/Images-PACT/fleche-gauche.png"));
	private Bouton droite = new Bouton("", new File("/Users/paulinerabis/Documents/Images-PACT/fleche-droite.png"));
	JLabel label = new JLabel("Livres");

	Font police = new Font("Garamond", Font.BOLD, 24);

	public PanneauLivres() {
		label.setFont(police);
		label.setForeground(Color.darkGray);
		label.setHorizontalAlignment(JLabel.CENTER);
		gauche.setPreferredSize(new Dimension(100, 90));
		droite.setPreferredSize(new Dimension(90, 90));
		this.setLayout(new BorderLayout());
		this.add(label, BorderLayout.NORTH);
		this.add(gauche, BorderLayout.WEST);
		this.add(droite, BorderLayout.EAST);
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
