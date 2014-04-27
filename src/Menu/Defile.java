package Menu;

import javax.swing.JFrame;

public class Defile {
	public static void main(String[] arg) {
		JFrame fenetre = new JFrame();
		fenetre.setContentPane(new IHMDefile());
		fenetre.setLocation(100, 100);
		fenetre.pack();
		fenetre.setVisible(true);	
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
