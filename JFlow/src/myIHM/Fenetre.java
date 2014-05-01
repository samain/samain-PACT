package myIHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;

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
		
		be.pwnt.jflow.demo.Configuration2 conf2 = new be.pwnt.jflow.demo.Configuration2();
		/*Cellule cell1 = new Cellule(new File("/Users/paulinerabis/Documents/Images-PACT/galaxy.jpg"));
		cell1.setLayout(new BorderLayout());*/
		
		final JFlowPanel case1 = new JFlowPanel(conf2);
		// cell4.setBackground(Color.black);
		case1.setPreferredSize(new Dimension(400, 900));
		//pan.setLayout(new FlowLayout());
		//cell4.add(pan);
		case1.addListener(new ShapeListener() {
			@Override
			public void shapeClicked(ShapeEvent e) {
				MouseEvent me = e.getMouseEvent();
				if (!me.isConsumed() && me.getButton() == MouseEvent.BUTTON1
						&& me.getClickCount() == 1) {
					JOptionPane.showMessageDialog(case1,
							"Vous avez choisi : " + e.getShape() + ".",
							"Immersive Reading", JOptionPane.INFORMATION_MESSAGE);
				}
			}

			@Override
			public void shapeActivated(ShapeEvent e) {
			}

			@Override
			public void shapeDeactivated(ShapeEvent e) {
			}
		});
			
		//cell1.add(case1, BorderLayout.CENTER);
		/*JPanel cell2 = new PanneauThemes();
		// cell2.setBackground(Color.red);
		cell2.setPreferredSize(new Dimension(500, 900));*/
be.pwnt.jflow.demo.Configration3 conf3 = new be.pwnt.jflow.demo.Configration3();
		
		final JFlowPanel cell2 = new JFlowPanel(conf3);
		// cell4.setBackground(Color.black);
		cell2.setPreferredSize(new Dimension(400, 900));
		//pan.setLayout(new FlowLayout());
		//cell4.add(pan);
		cell2.addListener(new ShapeListener() {
			@Override
			public void shapeClicked(ShapeEvent e) {
				MouseEvent me = e.getMouseEvent();
				if (!me.isConsumed() && me.getButton() == MouseEvent.BUTTON1
						&& me.getClickCount() == 1) {
					JOptionPane.showMessageDialog(cell2,
							"Vous avez choisi : " + e.getShape() + ".",
							"Immersive Reading", JOptionPane.INFORMATION_MESSAGE);
				}
			}

			@Override
			public void shapeActivated(ShapeEvent e) {
			}

			@Override
			public void shapeDeactivated(ShapeEvent e) {
			}
		});
		
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
		final JFlowPanel cell4 = new JFlowPanel(conf);
		// cell4.setBackground(Color.black);
		cell4.setPreferredSize(new Dimension(300, 900));
		//pan.setLayout(new FlowLayout());
		//cell4.add(pan);
		cell4.addListener(new ShapeListener() {
			@Override
			public void shapeClicked(ShapeEvent e) {
				MouseEvent me = e.getMouseEvent();
				if (!me.isConsumed() && me.getButton() == MouseEvent.BUTTON1
						&& me.getClickCount() == 1) {
					JOptionPane.showMessageDialog(cell4,
							"Vous avez choisi : " + e.getShape() + ".",
							"Immersive Reading", JOptionPane.INFORMATION_MESSAGE);
				}
			}

			@Override
			public void shapeActivated(ShapeEvent e) {
			}

			@Override
			public void shapeDeactivated(ShapeEvent e) {
			}
		});
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

		content.add(case1, gbc);

		gbc.gridx = 1;
		gbc.gridheight = 2;

		content.add(cell2, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		

		content.add(cell4, gbc);

		this.setContentPane(content);
		content.add(bouton);
		this.setVisible(true);


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

	