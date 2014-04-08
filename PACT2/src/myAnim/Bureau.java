package myAnim;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

public class Bureau extends JFrame{
	  private static int nbreFenetre = 0;
	  private JDesktopPane desktop = new JDesktopPane();
	  private static int xy = 10;
	   
	  public Bureau(){
	    this.setSize(400, 300);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JButton ajouter = new JButton("Ajouter une fenêtre interne");
	    ajouter.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){
	        ++nbreFenetre;
	        xy += 2;
	        desktop.add(new MiniFenetre(nbreFenetre), nbreFenetre);
	      }
	    });
	    this.getContentPane().add(desktop, BorderLayout.CENTER);
	    this.getContentPane().add(ajouter, BorderLayout.SOUTH);            
	  }
}
