package Menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class Bouton extends JButton implements MouseListener{
	private String name;
	private Image img;
	private File image;
	

	public Bouton(String str, File image) {
		super(str);

		this.name = str;

		this.image = image;
		try {
			img = ImageIO.read(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 this.addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, 0, 180, 90, 90, this);
		g2d.setColor(Color.black);
		g2d.drawString(this.name, this.getWidth() / 2
				- (this.getWidth() / 2 / 4), (this.getHeight() / 2) + 5);
	}
	//Méthode appelée lors du clic de souris
	  public void mouseClicked(MouseEvent event) { }

	  //Méthode appelée lors du survol de la souris
	  public void mouseEntered(MouseEvent event) { }

	  //Méthode appelée lorsque la souris sort de la zone du bouton
	  public void mouseExited(MouseEvent event) { }

	  //Méthode appelée lorsque l'on presse le bouton gauche de la souris
	  public void mousePressed(MouseEvent event) { }

	  //Méthode appelée lorsque l'on relâche le clic de souris
	  public void mouseReleased(MouseEvent event) { }   
}