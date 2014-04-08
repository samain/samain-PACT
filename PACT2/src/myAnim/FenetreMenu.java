package myAnim;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class FenetreMenu extends JFrame {
  private JTabbedPane onglet;
   
  public FenetreMenu(){
    this.setLocationRelativeTo(null);
    this.setTitle("G�rer vos conteneurs");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(400, 200);
      
    //Cr�ation de plusieurs Panneau
    PanneauMenu[] tPan = {   new PanneauMenu(Color.RED), new PanneauMenu(Color.GREEN), new PanneauMenu(Color.BLUE)};
      
    //Cr�ation de notre conteneur d'onglets
    onglet = new JTabbedPane();
    int i = 0;
    for(PanneauMenu pan : tPan){
    	  //M�thode d'ajout d'onglet    
    	  onglet.add("Onglet n� "+(++i), pan);
    	  //On ajoute l'image � l'onglet en cours
    	  //Les index d'onglets fonctionnent comme les tableaux : ils commencent � 0
    	  onglet.setIconAt((i - 1), new ImageIcon("java.jpg"));
    	   
    	  //Vous pouvez aussi utiliser la m�thode addTab
    	  //onglet.addTab("Onglet n� "+(++i), new ImageIcon("java.jpg"), pan);
    	}
    //On passe ensuite les onglets au content pane
    this.getContentPane().add(onglet);
    this.setVisible(true);
  }
   
  public static void main(String[] args){
    Fenetre fen = new Fenetre();
  }   
}
