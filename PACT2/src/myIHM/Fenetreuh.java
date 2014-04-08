package myIHM;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Fenetreuh extends JFrame {
	private JButton bouton = new JButton("Mon bouton");

	public Fenetreuh() {
		this.setTitle("Bouton");
		this.setSize(300, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		// On ajoute le bouton au content pane de la JFrame
		this.getContentPane().add(bouton);
		this.setVisible(true);
	}
}
