package myIHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import be.pwnt.jflow.*;
import be.pwnt.jflow.demo.*;
import be.pwnt.jflow.event.ShapeEvent;
import be.pwnt.jflow.event.ShapeListener;
import be.pwnt.jflow.shape.Picture;

public class Fenetre extends JFrame {
	private JButton bouton = new JButton("Réglages");
	//private Panneau pan = new Panneau();

	public Fenetre() {
		this.setTitle("ImmersiveReading");
		this.setSize(1200, 900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		// On crée nos différents conteneurs de couleur différente
		JPanel cell1 = new PanneauAmbiances();
		// cell1.setBackground(Color.YELLOW);
		cell1.setPreferredSize(new Dimension(300, 900));

		JPanel cell2 = new PanneauThemes();
		// cell2.setBackground(Color.red);
		cell2.setPreferredSize(new Dimension(600, 900));

		//JPanel cell4 = new PanneauLivres();
		be.pwnt.jflow.demo.Configuration conf = new be.pwnt.jflow.demo.Configuration();
		/*conf.shapes = new Shape[5];
		try {
			conf.shapes[0] = new Picture(getClass().getResource(
				"/Users/paulinerabis/Documents/Images-PACT/kaiken.jpg"));
			conf.shapes[1] = new Picture(getClass().getResource(
					"/Users/paulinerabis/Documents/Images-PACT/heidi.jpg"));
			conf.shapes[2] = new Picture(getClass().getResource(
					"/Users/paulinerabis/Documents/Images-PACT/petit-prince.jpg"));
			conf.shapes[3] = new Picture(getClass().getResource(
					"/Users/paulinerabis/Documents/Images-PACT/bfg.jpg"));
			conf.shapes[4] = new Picture(getClass().getResource(
					"/Users/paulinerabis/Documents/Images-PACT/voyage.jpg"));
		} catch(Exception e) {
			System.out.println("Exception a l'ouverture du cover Flow : " + e.getMessage());
		}
		*/
		JPanel cell4 = new JFlowPanel(conf);
		// cell4.setBackground(Color.black);
		cell4.setPreferredSize(new Dimension(300, 900));
		//pan.setLayout(new FlowLayout());
		//cell4.add(pan);

		// Le conteneur principal
		JPanel content = new JPanel();
		content.setPreferredSize(new Dimension(1200, 900));
		content.setBackground(Color.WHITE);
		// On définit le layout manager
		content.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints(0, 0,
				GridBagConstraints.REMAINDER, GridBagConstraints.REMAINDER, 1,
				1, GridBagConstraints.FIRST_LINE_START,
				GridBagConstraints.BOTH, new Insets(1, 1, 0, 0), 0, 0);

		gbc.gridx = 0;
		gbc.gridy = 0;

		gbc.gridheight = 2;
		gbc.gridwidth = 1;

		content.add(cell1, gbc);

		gbc.gridx = 1;
		gbc.gridheight = 2;

		content.add(cell2, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		// Celle-ci indique que la cellule se réplique de façon verticale

		content.add(cell4, gbc);

		this.setContentPane(content);
		content.add(bouton);
		this.setVisible(true);
		//go();

		bouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ZDialog zd = new ZDialog(null, "Réglages Immersive Reading",
						true);
				ZDialogInfo zInfo = zd.showZDialog();
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(null, zInfo.toString(),
						"Réglages Immersive Reading", jop.INFORMATION_MESSAGE);
			}
		});
	}

	/*private void go() {
		// Les coordonnées de départ de notre rond
		int x = pan.getPosX(), y = pan.getPosY();
		// Le booléen pour savoir si l'on recule ou non sur l'axe x
		boolean backX = false;
		// Le booléen pour savoir si l'on recule ou non sur l'axe y
		boolean backY = false;

		// Dans cet exemple, j'utilise une boucle while
		// Vous verrez qu'elle fonctionne très bien
		while (true) {
			// Si la coordonnée x est inférieure à 1, on avance
			if (x < 1)
				backX = false;

			// Si la coordonnée x est supérieure à la taille du Panneau moins la
			// taille du rond, on recule
			if (x > pan.getWidth() - 50)
				backX = true;

			// Idem pour l'axe y
			if (y < 1)
				backY = false;
			if (y > pan.getHeight() - 50)
				backY = true;

			// Si on avance, on incrémente la coordonnée
			// backX est un booléen, donc !backX revient à écrire
			// if (backX == false)
			if (!backX)
				pan.setPosX(++x);

			// Sinon, on décrémente
			else
				pan.setPosX(--x);

			// Idem pour l'axe Y
			if (!backY)
				pan.setPosY(++y);
			else
				pan.setPosY(--y);

			// On redessine notre Panneau
			pan.repaint();

			// Comme on dit : la pause s'impose ! Ici, trois millièmes de
			// seconde
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}*/
}

