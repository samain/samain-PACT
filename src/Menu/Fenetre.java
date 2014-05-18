package Menu;

import Synchronisation.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import be.pwnt.jflow.*;
import be.pwnt.jflow.demo.*;
import be.pwnt.jflow.event.ShapeEvent;
import be.pwnt.jflow.event.ShapeListener;
import be.pwnt.jflow.shape.Picture;

public class Fenetre extends JFrame {
	
	private JButton bouton = new JButton("Réglages");

	public static SynchronizerInterface synchronizer = new Synchronizer();
	
	public static int font = 35;
	
	//constructeur de la fenêtre
	public Fenetre() {
		
		//paramêtrage initial de la fenêtre
		this.setTitle("ImmersiveReading");
		this.setSize(1200, 900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//création d'un objet contenant les images du menu déroulant des livres.
		be.pwnt.jflow.demo.Configuration conf = new be.pwnt.jflow.demo.Configuration();
		
		//création d'un objet contenant les images du menu déroulant des livres.
		be.pwnt.jflow.demo.Configuration2 conf2 = new be.pwnt.jflow.demo.Configuration2();
		
		/*création du JFlowPanel contenant le menu déroulant des livres, 
		auquel on ajoute un listener sur chaque livre*/
		final JFlowPanel cell1 = new JFlowPanel(conf);
		cell1.setPreferredSize(new Dimension(300, 900));
		cell1.addListener(new ShapeListener() {
			
			@Override
			public void shapeClicked(ShapeEvent e) {
				MouseEvent me = e.getMouseEvent();
				if (!me.isConsumed() && me.getButton() == MouseEvent.BUTTON1
						&& me.getClickCount() == 1) {
					
					
					JOptionPane.showMessageDialog(cell1,
							"Vous avez choisi un livre. Bonne lecture !",
							"Immersive Reading", JOptionPane.INFORMATION_MESSAGE);
					 
					 		//transmission de la taille désirée de la police pour le livre lu
							Fenetre.synchronizer.setFont(font);
							
							//création du livre et affichage de la première page
							Fenetre.synchronizer.initialiseBook(System.getProperty("user.dir") + "//Textes//" + e.getShape().getName(), font);
					
							
					
				}
			}

			@Override
			public void shapeActivated(ShapeEvent e) {
			}

			@Override
			public void shapeDeactivated(ShapeEvent e) {
			}
		});
		
		//création du menu déroulant contenant les ambiances
		final JFlowPanel cell2 = new JFlowPanel(conf2);
		cell2.setPreferredSize(new Dimension(300, 900));
		cell2.addListener(new ShapeListener() {
			@Override
			public void shapeClicked(ShapeEvent e) {
				MouseEvent me = e.getMouseEvent();
				if (!me.isConsumed() && me.getButton() == MouseEvent.BUTTON1
						&& me.getClickCount() == 1) {
					
					
					 JOptionPane.showMessageDialog(cell2,
								"Vous avez choisi une ambiance. Bonne lecture !",
								"Immersive Reading", JOptionPane.INFORMATION_MESSAGE);
					
					 
					 		//transmission de la taille désirée de la police pour le livre lu
							Fenetre.synchronizer.setFont(font);
							
							//choix du livre le mieux approprié à l'ambinance choisie, création du livre et affichage de la première page
							Fenetre.synchronizer.initialiseAtmosphere(System.getProperty("user.dir") + "//Ressources//" + e.getShape().getName(), font);
					
							
					
				}
			}

			@Override
			public void shapeActivated(ShapeEvent e) {
			}

			@Override
			public void shapeDeactivated(ShapeEvent e) {
			}
		});
		
		
		//création du conteneur principal
		JPanel content = new JPanel();
		content.setPreferredSize(new Dimension(1200, 900));
		content.setBackground(Color.WHITE);
		
		// On définit le layout manager
		content.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints(0, 0,
				GridBagConstraints.REMAINDER, GridBagConstraints.REMAINDER, 1,
				1, GridBagConstraints.FIRST_LINE_START,
				GridBagConstraints.BOTH, new Insets(1, 1, 0, 0), 0, 0); 
		

		//positionnement du menu déroulant des livres
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		

		content.add(cell1, gbc);

		
		//positionnement du menu déroulant des ambiances
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		
		content.add(cell2, gbc);
		
		
		//ajout du conteneur principal à la Fenêtre
		
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
		
		
		/*création du NAtiveHookListener qui transmet les ordres de l'utilisateur 
		 (flêche droite pour avancer d'une page, flêche gauche pour reculer d'une page, 
		 touche entrée pour quitter l'application)*/
		
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException e) {
			System.err.println("There was a problem registering the native hook.");
			e.printStackTrace();
			System.exit(1);
		}
		
		GlobalScreen.getInstance().addNativeKeyListener(new NativeKeyListener(){
			public void nativeKeyReleased(NativeKeyEvent e) {
				int code = e.getKeyCode();
				switch(code){
				case NativeKeyEvent.VK_RIGHT :;
					transferMovement("right");
					break;
				case NativeKeyEvent.VK_LEFT :
					transferMovement("left");
					break;
				case NativeKeyEvent.VK_UP :
					transferMovement("up");
					break;
				case NativeKeyEvent.VK_DOWN :
					transferMovement("down");
					break;
				case NativeKeyEvent.VK_ENTER :
					transferMovement("select");
				break;
				}
			}

			@Override
			public void nativeKeyPressed(NativeKeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void nativeKeyTyped(NativeKeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	//-------------------------------------------------------------------
	//méthode chargée de transférer le changement de page désiré au synchroniseur
	public void transferMovement(String movement){
		switch(movement){
		case "select" :
			System.exit(0);
			break;
	    default : 
	    	synchronizer.receiveMouvement(movement);
	    	break;
		}
	}
//--------------------------------------------------------------------------	
			
}

