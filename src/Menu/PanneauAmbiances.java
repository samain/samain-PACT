package Menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PanneauAmbiances extends JPanel {

	private JRadioButton jr1 = new JRadioButton("Relaxation");
	private JRadioButton jr2 = new JRadioButton("Aventure");
	private JRadioButton jr3 = new JRadioButton("Sommeil");
	
	public PanneauAmbiances() {
		Color trans = new Color(255, 255, 255, 0);
		this.setLayout(new GridBagLayout());

		this.setPreferredSize(new Dimension(600, 500));

		JPanel cell1 = new Cellule(new File(System.getProperty("user.dir") + "/Images-PACT/relaxation.jpg"));
		//cell1.setBackground(Color.YELLOW);
		cell1.add(jr1);
		
		cell1.setPreferredSize(new Dimension(100, 100));
		JPanel cell2 = new Cellule(new File(System.getProperty("user.dir") + "/Images-PACT/aventure.jpg"));
		//cell2.setBackground(Color.red);
		cell2.add(jr2);
		cell2.setPreferredSize(new Dimension(100, 100));
		JPanel cell3 = new Cellule(new File(System.getProperty("user.dir") + "/Images-PACT/nuit.jpg"));
		cell3.setBackground(Color.green);
		cell3.setPreferredSize(new Dimension(100, 100));
		cell3.add(jr3);
		JPanel cell4 = new JPanel();
		cell4.setBackground(trans);
		cell4.setPreferredSize(new Dimension(60, 40));
		JPanel cell5 = new JPanel();
		cell5.setBackground(trans);
		cell5.setPreferredSize(new Dimension(60, 40));
		JPanel cell6 = new JPanel();
		cell6.setBackground(trans);
		cell6.setPreferredSize(new Dimension(60, 40));
		JPanel cell7 = new JPanel();
		cell7.setBackground(trans);
		cell7.setPreferredSize(new Dimension(60, 40));
		JPanel cell8 = new JPanel();
		cell8.setBackground(trans);
		cell8.setPreferredSize(new Dimension(60, 40));
		

		// L'objet servant à positionner les composants
		
		GridBagConstraints gbc = new GridBagConstraints(0, 0,
				GridBagConstraints.REMAINDER, GridBagConstraints.REMAINDER, 1,
				1, GridBagConstraints.SOUTH,
				GridBagConstraints.BOTH, new Insets(1, 1, 0, 0), 0, 0);

		// On positionne la case de départ du composant
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		// La taille en hauteur et en largeur
		//gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(cell4, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		//gbc.fill = GridBagConstraints.VERTICAL;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		this.add(cell5, gbc);
		
		gbc.gridx = 1;
		//gbc.fill = GridBagConstraints.VERTICAL;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		this.add(cell1, gbc);
		// ---------------------------------------------
		gbc.gridx = 2;
		this.add(cell2, gbc);
		// ---------------------------------------------

		gbc.gridx = 3;
		this.add(cell3, gbc);
		
		gbc.gridx = 4;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(cell6,gbc);
		
		//gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.gridy=2;
		gbc.gridx=0;
		this.add(cell7,gbc);
		
		
		jr1.setForeground(new Color(0, 0, 0));
		jr2.setForeground(new Color(255, 255, 255));
		jr3.setForeground(new Color(255, 255, 255));
		
		jr1.setFont(new Font("Garamond", Font.BOLD, 16));
		jr2.setFont(new Font("Garamond", Font.BOLD, 16));
		jr3.setFont(new Font("Garamond", Font.BOLD, 16));
		// ---------------------------------------------
		// Cette instruction informe le layout que c'est une fin de ligne
		
		
		
	}
	protected void paintComponent(Graphics g) {

		try {
			Image img = ImageIO.read(new File(System.getProperty("user.dir") + "/Images-PACT/cubes.jpg"));

			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

}

