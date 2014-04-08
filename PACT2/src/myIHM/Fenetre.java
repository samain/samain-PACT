package myIHM;
import java.awt.Color; 
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame {
	  public Fenetre(){
		    this.setTitle("Immersive Reading");
		    this.setSize(1500, 1000);
		    this.setLocationRelativeTo(null);
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		    this.setContentPane(new PanneauAcceuil());
		    this.setVisible(true);
		  }
		}
