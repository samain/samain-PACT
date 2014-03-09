package myIHM;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetrebis extends JFrame {
	private JPanel pan = new JPanel();
	private JButton bouton = new JButton("Mon bouton");

	public Fenetrebis() {
		this.setTitle("Animation");
		this.setSize(300, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		// Ajout du bouton ˆ notre content pane
		pan.add(bouton);
		this.setContentPane(pan);
		this.setVisible(true);
	}
}
