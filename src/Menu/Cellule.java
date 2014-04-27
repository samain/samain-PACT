package Menu;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Cellule extends JPanel {
	private File image;
	
	public Cellule(File image){
		this.image=image;
	}
	
	protected void paintComponent(Graphics g) {

		try {
			Image img = ImageIO.read(image);

			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
