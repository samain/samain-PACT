package Menu;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

import Synchronisation.Synchronizer;

public class Fenetreclayout implements ItemListener {
	JPanel cards; // le panneau qui va contenir le CardLayout
	final static String ACCUEIL = "Menu d'accueil";
	final static String THEMES = "Menu des thèmes";
	final static String AMBIANCES = "Menu des ambiances";
	final static String LIVRES = "Menu des livres";
	String currentBook = null;

	// JFrame principalFrame = new JFrame("Immersive Reading");

	/*
	 * principalFrame.setSize(900, 1000);
	 * principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 */

	public void addComponentToPane(Container pane) {
		// On crée la combobox qui va nous servir à choisir les sous-menus
		JPanel comboBoxPane = new JPanel();
		String comboBoxItems[] = { ACCUEIL, THEMES, AMBIANCES, LIVRES };
		JComboBox cb = new JComboBox(comboBoxItems);
		cb.setEditable(false);
		cb.addItemListener(this);
		comboBoxPane.add(cb);
		/*
		 * JButton theme = new JButton("Thèmes"); JButton ambiance = new
		 * JButton("Ambiances"); JButton livre = new JButton("Livres");
		 * 
		 * accueil.add(theme); accueil.add(ambiance); accueil.add(livre);
		 */
		// On fabrique les différentes "couches" de notre IHM
		JPanel accueil = new PanneauAcceuil();
		JButton quit = new JButton("Quitter");
		accueil.add(quit);

		quit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		JPanel themes = new PanneauTheme();
		JButton theme1 = new JButton("Thème 1");
		JButton theme2 = new JButton("Thème2");
		JButton theme3 = new JButton("Thème3");
		JButton quittheme = new JButton("Quitter");
		themes.add(theme1);
		themes.add(theme2);
		themes.add(theme3);
		themes.add(quittheme);

		theme1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// à remplir quand on aura du thème
			}
		});

		theme2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// à remplir quand on aura du thème
			}
		});

		theme3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// à remplir quand on aura du thème
			}
		});

		quittheme.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		//ajout des bouton spour sélectionner les livres.

		JPanel livres = new Panneau();
/*		JButton livre1 = new JButton("Livre 1");
		JButton livre2 = new JButton("Livre 2");
		JButton livre3 = new JButton("Livre 3");
*/
		File file = new File("Textes");
		File[] filesList = file.listFiles();
		String comboBoxItemsLivres[] = new String[filesList.length];
		for(int i = 0 ; i<filesList.length; i++){
			comboBoxItemsLivres[i] = filesList[i].getName();
		}
		JComboBox cbLivres = new JComboBox(comboBoxItemsLivres);
		cbLivres.setEditable(false);
		cbLivres.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent event) {
				currentBook = (String)event.getItem();
			}
		});
		livres.add(cbLivres);
		JButton launchlivre = new JButton("Lire");
		JButton quitlivre = new JButton("Quitter");
/*		livres.add(livre1);
		livres.add(livre2);
		livres.add(livre3);
*/		livres.add(quitlivre);
		livres.add(launchlivre);

		//ajout des listeners sur les boutons du panneau où l'on choisit son livre
		
/*		livre1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Menu.synchronizer.initialiseBook(System.getProperty("user.dir") + "//Textes//Les raisins de la colère -Nuit-.txt", 35);
			}
		});

		livre2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Menu.synchronizer.initialiseBook(System.getProperty("user.dir") + "//Textes//Histoires du Grand Nord -Plage-.txt", 35);
			}
		});

		livre3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Menu.synchronizer.initialiseBook(System.getProperty("user.dir") + "//Textes//Voyages de Gulliver -Espace-.txt", 35);
			}
		});
*/
		launchlivre.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Menu.synchronizer.initialiseBook(System.getProperty("user.dir") + "//Textes//" + currentBook, 35);
			}
		});
		
		quitlivre.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		JPanel ambiances = new PanneauAmbiance();
		JButton ambiance1 = new JButton("Ambiance 1");
		JButton ambiance2 = new JButton("Ambiance 2");
		JButton ambiance3 = new JButton("Ambiance 3");
		JButton quitambiance = new JButton("Quitter");

		ambiances.add(ambiance1);
		ambiances.add(ambiance2);
		ambiances.add(ambiance3);
		ambiances.add(quitambiance);

		ambiance1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// à remplir quand on aura des ambiances
			}
		});

		ambiance2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// à remplir quand on aura des ambiances
			}
		});

		ambiance3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// à remplir quand on aura des ambiances
			}
		});

		quitambiance.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		// On fabrique le panneau qui va contenir toutes les couches (accueil,
		// themes, livres et ambiances)
		final CardLayout cl = new CardLayout();
		cards = new JPanel(cl);
		cards.add(accueil, ACCUEIL);
		cards.add(themes, THEMES);
		cards.add(livres, LIVRES);
		cards.add(ambiances, AMBIANCES);

		pane.add(comboBoxPane, BorderLayout.PAGE_START);
		pane.add(cards, BorderLayout.CENTER);

	}

	public void itemStateChanged(ItemEvent evt) {
		CardLayout cl = (CardLayout) (cards.getLayout());
		cl.show(cards, (String) evt.getItem());
	}

}