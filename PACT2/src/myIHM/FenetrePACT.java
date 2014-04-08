package myIHM;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FenetrePACT extends JFrame {
	CardLayout card = new CardLayout();
	GridBagConstraints gBc = new GridBagConstraints();
	//JPanel main = new JPanel();
	String[] listContent = { "Livres", "Ambiance", "Themes"};
	int indice = 0;

	private JButton livre1 = new JButton("Livre 1");
	private JButton livre2 = new JButton("Livre 2");
	private JButton livre3 = new JButton("Livre 3");
	private JButton livre4 = new JButton("Livre 4");
	private JButton livre5 = new JButton("Livre 5");
	private JButton livre6 = new JButton("Livre 6");
	private JButton bouton7 = new JButton("Menu");
	private JButton bouton8 = new JButton("Quitter");
	private JButton ambiance1 = new JButton("Ambiance 1");
	private JButton ambiance2 = new JButton("Ambiance 2");
	private JButton ambiance3 = new JButton("Ambiance 3");
	private JButton ambiance4 = new JButton("Ambiance 4");
	private JButton ambiance5 = new JButton("Ambiance 5");
	private JButton ambiance6 = new JButton("Ambiance 6");
	private JButton theme1 = new JButton("Thème 1");
	private JButton theme2 = new JButton("Thème 2");
	private JButton theme3 = new JButton("Thème 3");
	private JButton theme4 = new JButton("Thème 4");
	private JButton theme5 = new JButton("Thème 5");
	private JButton theme6 = new JButton("Thème 6");
	private JButton theme = new JButton("Thèmes");
	private JButton ambiance = new JButton("Ambiances");
	private JButton livre = new JButton("Livres");

	public FenetrePACT() {
		this.setTitle("Immersive Reading");
		this.setSize(1000, 1500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		Color trans = new Color(255, 255, 255, 0);

		// **********************************************************************************************************
		// Le conteneur livres
		JPanel content1 = new Panneau();
		content1.setPreferredSize(new Dimension(900, 900));

		JPanel cell1 = new JPanel();
		cell1.setBackground(trans);
		cell1.setPreferredSize(new Dimension(100, 500));
		cell1.add(livre1);
		JPanel cell2 = new JPanel();
		cell2.setBackground(trans);
		cell2.setPreferredSize(new Dimension(100, 500));
		cell2.add(livre2);
		JPanel cell3 = new JPanel();
		cell3.setBackground(trans);
		cell3.setPreferredSize(new Dimension(100, 500));
		cell3.add(livre3);
		JPanel cell4 = new JPanel();
		cell4.setBackground(trans);
		cell4.setPreferredSize(new Dimension(100, 500));
		cell4.add(livre4);
		JPanel cell5 = new JPanel();
		cell5.setBackground(trans);
		cell5.setPreferredSize(new Dimension(100, 500));
		cell5.add(livre5);
		JPanel cell6 = new JPanel();
		cell6.setBackground(trans);
		cell6.setPreferredSize(new Dimension(100, 500));
		cell6.add(livre6);
		JPanel cell7 = new JPanel();
		cell7.setBackground(trans);
		cell7.setPreferredSize(new Dimension(60, 40));
		cell7.add(bouton7);
		JPanel cell8 = new JPanel();
		cell8.setBackground(trans);
		cell8.setPreferredSize(new Dimension(60, 40));
		cell8.add(bouton8);

		// On définit le layout manager
		content1.setLayout(new GridBagLayout());

		// L'objet servant à positionner les composants
		GridBagConstraints gbc = new GridBagConstraints();

		// On positionne la case de départ du composant
		gbc.gridx = 0;
		gbc.gridy = 0;
		// La taille en hauteur et en largeur
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		content1.add(cell1, gbc);
		// ---------------------------------------------
		gbc.gridx = 1;
		content1.add(cell2, gbc);
		// ---------------------------------------------
		gbc.gridx = 2;
		content1.add(cell3, gbc);
		// ---------------------------------------------
		// Cette instruction informe le layout que c'est une fin de ligne
		// gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 3;
		content1.add(cell4, gbc);
		// ---------------------------------------------
		gbc.gridx = 4;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		// Celle-ci indique que la cellule se réplique de façon verticale
		gbc.fill = GridBagConstraints.VERTICAL;
		content1.add(cell5, gbc);
		// ---------------------------------------------
		gbc.gridx = 5;
		gbc.gridheight = 1;
		// Celle-ci indique que la cellule se réplique de façon horizontale
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		content1.add(cell6, gbc);
		// ---------------------------------------------
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		content1.add(cell7, gbc);
		// ---------------------------------------------
		gbc.gridx = 3;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		content1.add(cell8, gbc);
		// ---------------------------------------------
		// **********************************************************************************************************
		// Le conteneur Ambiances
		JPanel content2 = new PanneauAmbiance();
		content2.setPreferredSize(new Dimension(900, 900));

		JPanel case1 = new JPanel();
		case1.setBackground(trans);
		case1.setPreferredSize(new Dimension(100, 500));
		case1.add(ambiance1);
		JPanel case2 = new JPanel();
		case2.setBackground(trans);
		case2.setPreferredSize(new Dimension(100, 500));
		case2.add(ambiance2);
		JPanel case3 = new JPanel();
		case3.setBackground(trans);
		case3.setPreferredSize(new Dimension(100, 500));
		case3.add(ambiance3);
		JPanel case4 = new JPanel();
		case4.setBackground(trans);
		case4.setPreferredSize(new Dimension(100, 500));
		case4.add(ambiance4);
		JPanel case5 = new JPanel();
		case5.setBackground(trans);
		case5.setPreferredSize(new Dimension(100, 500));
		case5.add(ambiance5);
		JPanel case6 = new JPanel();
		case6.setBackground(trans);
		case6.setPreferredSize(new Dimension(100, 500));
		case6.add(ambiance6);

		// On définit le layout manager
		content2.setLayout(new GridBagLayout());

		// L'objet servant à positionner les composants
		GridBagConstraints gbc2 = new GridBagConstraints();

		// On positionne la case de départ du composant
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		// La taille en hauteur et en largeur
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		content2.add(case1, gbc2);
		// ---------------------------------------------
		gbc2.gridx = 1;
		content2.add(case2, gbc2);
		// ---------------------------------------------
		gbc2.gridx = 2;
		content2.add(case3, gbc2);
		// ---------------------------------------------
		// Cette instruction informe le layout que c'est une fin de ligne
		// gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc2.gridx = 3;
		content2.add(case4, gbc2);
		// ---------------------------------------------
		gbc2.gridx = 4;
		gbc2.gridy = 0;
		gbc2.gridwidth = 1;
		gbc2.gridheight = 2;
		// Celle-ci indique que la cellule se réplique de façon verticale
		gbc2.fill = GridBagConstraints.VERTICAL;
		content2.add(case5, gbc2);
		// ---------------------------------------------
		gbc2.gridx = 5;
		gbc2.gridheight = 1;
		// Celle-ci indique que la cellule se réplique de façon horizontale
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbc2.gridwidth = GridBagConstraints.REMAINDER;
		content2.add(case6, gbc2);
		// ---------------------------------------------
		gbc2.gridx = 1;
		gbc2.gridy = 2;
		gbc2.gridwidth = 2;
		content2.add(cell7, gbc2);
		// ---------------------------------------------
		gbc2.gridx = 3;
		gbc2.gridwidth = GridBagConstraints.REMAINDER;
		content2.add(cell8, gbc2);
		// ---------------------------------------------
		// **********************************************************************************************************
		// Le conteneur Thèmes
		JPanel content3 = new PanneauTheme();
		content3.setPreferredSize(new Dimension(900, 900));

		JPanel box1 = new JPanel();
		box1.setBackground(trans);
		box1.setPreferredSize(new Dimension(100, 500));
		box1.add(theme1);
		JPanel box2 = new JPanel();
		box2.setBackground(trans);
		box2.setPreferredSize(new Dimension(100, 500));
		box2.add(theme2);
		JPanel box3 = new JPanel();
		box3.setBackground(trans);
		box3.setPreferredSize(new Dimension(100, 500));
		box3.add(theme3);
		JPanel box4 = new JPanel();
		box4.setBackground(trans);
		box4.setPreferredSize(new Dimension(100, 500));
		box4.add(theme4);
		JPanel box5 = new JPanel();
		box5.setBackground(trans);
		box5.setPreferredSize(new Dimension(100, 500));
		box5.add(theme5);
		JPanel box6 = new JPanel();
		box6.setBackground(trans);
		box6.setPreferredSize(new Dimension(100, 500));
		box6.add(theme6);

		// On définit le layout manager
		content3.setLayout(new GridBagLayout());

		// L'objet servant à positionner les composants
		GridBagConstraints gbc3 = new GridBagConstraints();

		// On positionne la case de départ du composant
		gbc3.gridx = 0;
		gbc3.gridy = 0;
		// La taille en hauteur et en largeur
		gbc3.gridheight = 1;
		gbc3.gridwidth = 1;
		content3.add(box1, gbc3);
		// ---------------------------------------------
		gbc3.gridx = 1;
		content3.add(box2, gbc3);
		// ---------------------------------------------
		gbc3.gridx = 2;
		content3.add(box3, gbc3);
		// ---------------------------------------------
		// Cette instruction informe le layout que c'est une fin de ligne
		// gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc3.gridx = 3;
		content3.add(box4, gbc3);
		// ---------------------------------------------
		gbc3.gridx = 4;
		gbc3.gridy = 0;
		gbc3.gridwidth = 1;
		gbc3.gridheight = 2;
		// Celle-ci indique que la cellule se réplique de façon verticale
		gbc3.fill = GridBagConstraints.VERTICAL;
		content3.add(box5, gbc3);
		// ---------------------------------------------
		gbc3.gridx = 5;
		gbc3.gridheight = 1;
		// Celle-ci indique que la cellule se réplique de façon horizontale
		gbc3.fill = GridBagConstraints.HORIZONTAL;
		gbc3.gridwidth = GridBagConstraints.REMAINDER;
		content3.add(box6, gbc3);
		// ---------------------------------------------
		gbc3.gridx = 1;
		gbc3.gridy = 2;
		gbc3.gridwidth = 2;
		content3.add(cell7, gbc3);
		// ---------------------------------------------
		gbc3.gridx = 3;
		gbc3.gridwidth = GridBagConstraints.REMAINDER;
		content3.add(cell8, gbc3);
		// ---------------------------------------------
		// *******************************************************************************************************
		// Le conteneur Acceuil
		final JPanel content4 = new PanneauAcceuil();
		content4.setPreferredSize(new Dimension(900, 900));

		JPanel boite1 = new JPanel();
		boite1.setBackground(trans);
		boite1.setPreferredSize(new Dimension(100, 500));
		boite1.add(theme);
		JPanel boite2 = new JPanel();
		boite2.setBackground(trans);
		boite2.setPreferredSize(new Dimension(100, 500));
		boite2.add(ambiance);
		JPanel boite3 = new JPanel();
		boite3.setBackground(trans);
		boite3.setPreferredSize(new Dimension(100, 500));
		boite3.add(livre);

		// On définit le layout manager
		content4.setLayout(new GridBagLayout());

		// L'objet servant à positionner les composants
		GridBagConstraints gbc4 = new GridBagConstraints();

		// On positionne la case de départ du composant
		gbc4.gridx = 0;
		gbc4.gridy = 0;
		// La taille en hauteur et en largeur
		gbc4.gridheight = 1;
		gbc4.gridwidth = 1;
		content4.add(boite1, gbc4);
		// ---------------------------------------------
		gbc4.gridx = 1;
		content4.add(boite2, gbc4);
		// ---------------------------------------------
		gbc4.gridx = 2;
		content4.add(boite3, gbc4);
		// ---------------------------------------------
		// gbc4.gridx = 1;
		// gbc4.gridy = 2;
		// gbc4.gridwidth = 2;
		// content4.add(cell7, gbc4);
		// ---------------------------------------------
		gbc4.gridx = 3;
		gbc4.gridwidth = GridBagConstraints.REMAINDER;
		content4.add(cell8, gbc4);
		// ---------------------------------------------
		/*
		content4.add(content1,"Livres");
		content4.add(content2, "Ambiances");	Vincent entre en jeu !
		content4.add(content3, "Themes");
		*/
		content4.add(content1,gbc2);
		content4.add(content2, gbc3);
		content4.add(content3, gbc4);
		// **********************************************************************************************************

		//main.setPreferredSize(new Dimension(900, 900));
		//main.add(content4, "Accueil");
		//main.setLayout(card);
		theme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Via cette instruction, on passe au conteneur correspondant au
				// nom fourni en paramètre
				//card.show(main, listContent[3]);
				card.show(content4, listContent[3]);
			}
		});
		ambiance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Via cette instruction, on passe au conteneur correspondant au
				// nom fourni en paramètre
				//(card).show(main, listContent[2]);
				card.show(content4, listContent[2]);
			}
		});
		livre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Via cette instruction, on passe au conteneur correspondant au
				// nom fourni en paramètre
				card.show(content4, listContent[1]);
			}
		});

		// On ajoute le conteneur
		this.setContentPane(content4);
		this.setVisible(true);
		// go();
	}

}
