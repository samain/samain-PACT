package myIHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

class IHMDefile extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private int pas = 10;
	private int x;
	private Image image;
	private int largeurImage;
	Color trans = new Color(255, 255, 255, 0);
	
	IHMDefile() {
		setPreferredSize(new Dimension(1000, 1500));
		setBackground(trans);
		timer = new Timer(100, this);
		timer.start() ;

}

	void construireImage() {
		Image bfg, kaiken, prince, voyage, heidi;
		String chaine = "Immersive Reading - Be inside. Dive inside. Enjoy";
	
		try {
			bfg = javax.imageio.ImageIO.read (new File("/Users/paulinerabis/Documents/Images-PACT/bfg.jpg"));
			prince = javax.imageio.ImageIO.read (new File("/Users/paulinerabis/Documents/Images-PACT/petit-prince.jpg"));
			kaiken = javax.imageio.ImageIO.read (new File("/Users/paulinerabis/Documents/Images-PACT/kaiken.jpg"));
			voyage = javax.imageio.ImageIO.read (new File("/Users/paulinerabis/Documents/Images-PACT/voyage.jpg"));
			heidi = javax.imageio.ImageIO.read (new File("/Users/paulinerabis/Documents/Images-PACT/heidi.jpg"));
			int largeurbfg = bfg.getWidth(this);
			int hauteurbfg = bfg.getHeight(this);
			int largeurprince = prince.getWidth(this);
			int hauteurprince = prince.getHeight(this);
			int largeurkaiken = kaiken.getWidth(this);
			int hauteurkaiken = kaiken.getHeight(this);
			int largeurvoyage = voyage.getWidth(this);
			int hauteurvoyage = voyage.getHeight(this);
			int largeurheidi = heidi.getWidth(this);
			int hauteurheidi = heidi.getHeight(this);
			/*Font fonte = new Font("Garamond",Font.PLAIN, 25);
			FontMetrics mesure = getFontMetrics(fonte);
			int hauteurChaine = mesure.getHeight();
			int largeurChaine = mesure.stringWidth(chaine);*/

			largeurImage = largeurbfg + 10 + largeurprince + 10 + largeurkaiken + 10 + largeurvoyage + largeurheidi + 20;
			int hauteurImage = (hauteurheidi > hauteurprince ?hauteurheidi : hauteurprince);
			//int yChaine = (hauteurImage + mesure.getAscent())/2;

			image = createImage(largeurImage, hauteurImage);

			Graphics g = image.getGraphics();
			g.setColor(trans);
			g.fillRect(0, 0, largeurImage, hauteurImage);
			g.drawImage(bfg, 0, (hauteurImage - hauteurbfg)/2, this);
			//g.setFont(fonte);
			//g.setColor(Color.RED);
			g.drawImage(prince, largeurbfg + 10, (hauteurImage - hauteurprince)/2,this);
			g.drawImage(kaiken, largeurbfg + largeurprince + 20, (hauteurImage - hauteurkaiken)/2,this);
			g.drawImage(voyage, largeurbfg + largeurprince + largeurkaiken + 30, (hauteurImage - hauteurvoyage)/2,this);
			g.drawImage(heidi, largeurbfg + largeurprince + largeurkaiken + largeurvoyage + 40, (hauteurImage - hauteurvoyage)/2,this);
			g.dispose();
			javax.imageio.ImageIO.write((java.awt.image.BufferedImage)image, "jpeg", new java.io.File("defile.jpg"));
		}
		catch (IOException exc) {
			exc.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		x = x - pas;
	
		// si l'image est sortie sur la droite, on la fait repartir de la gauche
		if (x < -largeurImage)  x = getWidth();
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		if (image == null) construireImage();
		g.drawImage(image, x, 40, this);
	}
}