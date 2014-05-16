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
	//private Panneau pan = new Panneau();

	public static Synchronizer synchronizer = new Synchronizer();
	
	public static int font = 35;
	
	public Fenetre() {
		this.setTitle("ImmersiveReading");
		this.setSize(1200, 900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		// On crée nos différents conteneurs de couleur différente
		 /*JPanel cell1 = new PanneauAmbiances();
		// cell1.setBackground(Color.YELLOW);
		cell1.setPreferredSize(new Dimension(300, 900));
*/
		JPanel cell2 = new PanneauThemes();
		// cell2.setBackground(Color.red);
		cell2.setPreferredSize(new Dimension(600, 900)); 

		//JPanel cell4 = new PanneauLivres();
		be.pwnt.jflow.demo.Configuration conf = new be.pwnt.jflow.demo.Configuration();
		be.pwnt.jflow.demo.Configuration2 conf2 = new be.pwnt.jflow.demo.Configuration2();
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
					
					 
					 		System.out.println("synchroniseur : setFont");
							Fenetre.synchronizer.setFont(font);
							System.out.println("synchroniseur : initialiseBook");
							System.out.println(System.getProperty("user.dir") + "//Textes//" + e.getShape().getName());
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
		
		
		final JFlowPanel cell1 = new JFlowPanel(conf2);
		// cell4.setBackground(Color.black);
		cell1.setPreferredSize(new Dimension(300, 900));
		//pan.setLayout(new FlowLayout());
		//cell4.add(pan);
		cell1.addListener(new ShapeListener() {
			@Override
			public void shapeClicked(ShapeEvent e) {
				MouseEvent me = e.getMouseEvent();
				if (!me.isConsumed() && me.getButton() == MouseEvent.BUTTON1
						&& me.getClickCount() == 1) {
					
					
					 JOptionPane.showMessageDialog(cell1,
								"Vous avez choisi : " + e.getShape() + ".",
								"Immersive Reading", JOptionPane.INFORMATION_MESSAGE);
					
					 
					 		System.out.println("synchroniseur : setFont");
							Fenetre.synchronizer.setFont(font);
							System.out.println("synchroniseur : initialiseAtmosphere");
							System.out.println(System.getProperty("user.dir") + "//Ressources//" + e.getShape().getName());
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
		// GridBagConstraints gbc =  new GridBagConstraints();

	/*	gbc.gridx = 0;
		gbc.gridy = 0;

		gbc.gridheight = 2;
		gbc.gridwidth = 1;

		content.add(cell1, gbc);

		gbc.gridx = 1;
		gbc.gridheight = 2;

		content.add(cell2, gbc); */ 

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		// Celle-ci indique que la cellule se réplique de façon verticale

		content.add(cell4, gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		
		content.add(cell1, gbc);
		
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
				case NativeKeyEvent.VK_RIGHT : 
					System.out.println("right");
					transferMovement("right");
					break;
				case NativeKeyEvent.VK_LEFT : 
					System.out.println("left");
					transferMovement("left");
					break;
				case NativeKeyEvent.VK_UP :
					System.out.println("up");
					transferMovement("up");
					break;
				case NativeKeyEvent.VK_DOWN :
					System.out.println("down");
					transferMovement("down");
					break;
				case NativeKeyEvent.VK_ENTER :
					System.out.println("select");
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

