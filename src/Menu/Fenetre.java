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
	
	private JButton bouton = new JButton("R�glages");

	public static SynchronizerInterface synchronizer = new Synchronizer();
	
	public static int font = 35;
	
	//constructeur de la fen�tre
	public Fenetre() {
		
		//param�trage initial de la fen�tre
		this.setTitle("ImmersiveReading");
		this.setSize(1200, 900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//cr�ation d'un objet contenant les images du menu d�roulant des livres.
		be.pwnt.jflow.demo.Configuration conf = new be.pwnt.jflow.demo.Configuration();
		
		//cr�ation d'un objet contenant les images du menu d�roulant des livres.
		be.pwnt.jflow.demo.Configuration2 conf2 = new be.pwnt.jflow.demo.Configuration2();
		
		/*cr�ation du JFlowPanel contenant le menu d�roulant des livres, 
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
					 
					 		//transmission de la taille d�sir�e de la police pour le livre lu
							Fenetre.synchronizer.setFont(font);
							
							//cr�ation du livre et affichage de la premi�re page
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
		
		//cr�ation du menu d�roulant contenant les ambiances
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
					
					 
					 		//transmission de la taille d�sir�e de la police pour le livre lu
							Fenetre.synchronizer.setFont(font);
							
							//choix du livre le mieux appropri� � l'ambinance choisie, cr�ation du livre et affichage de la premi�re page
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
		
		
		//cr�ation du conteneur principal
		JPanel content = new JPanel();
		content.setPreferredSize(new Dimension(1200, 900));
		content.setBackground(Color.WHITE);
		
		// On d�finit le layout manager
		content.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints(0, 0,
				GridBagConstraints.REMAINDER, GridBagConstraints.REMAINDER, 1,
				1, GridBagConstraints.FIRST_LINE_START,
				GridBagConstraints.BOTH, new Insets(1, 1, 0, 0), 0, 0); 
		

		//positionnement du menu d�roulant des livres
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		

		content.add(cell1, gbc);

		
		//positionnement du menu d�roulant des ambiances
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		
		content.add(cell2, gbc);
		
		
		//ajout du conteneur principal � la Fen�tre
		
		this.setContentPane(content);
		content.add(bouton);
		this.setVisible(true);

		bouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ZDialog zd = new ZDialog(null, "R�glages Immersive Reading",
						true);
				ZDialogInfo zInfo = zd.showZDialog();
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(null, zInfo.toString(),
						"R�glages Immersive Reading", jop.INFORMATION_MESSAGE);
			}
		});
		
		
		/*cr�ation du NAtiveHookListener qui transmet les ordres de l'utilisateur 
		 (fl�che droite pour avancer d'une page, fl�che gauche pour reculer d'une page, 
		 touche entr�e pour quitter l'application)*/
		
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
	//m�thode charg�e de transf�rer le changement de page d�sir� au synchroniseur
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

