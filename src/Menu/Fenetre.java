package Menu;

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

		JPanel cell4 = new PanneauLivres();
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

	
}

