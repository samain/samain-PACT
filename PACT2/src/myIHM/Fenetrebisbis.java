package myIHM;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fenetrebisbis extends JFrame {

  private JPanel container = new JPanel();
  private JTextField jtf;   
  private JLabel label = new JLabel("Téléphone FR");
  private JButton b = new JButton ("OK");

  public Fenetrebisbis(){      
    this.setTitle("Animation");
    this.setSize(300, 150);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);

    container.setBackground(Color.white);
    container.setLayout(new BorderLayout());

    jtf = new JTextField();
    JPanel top = new JPanel();      

    Font police = new Font("Arial", Font.BOLD, 14);
    jtf.setFont(police);
    jtf.setPreferredSize(new Dimension(150, 30));
    jtf.setForeground(Color.BLUE);
    //On ajoute l'écouteur à notre composant
    jtf.addKeyListener(new ClavierListener());

    top.add(label);
    top.add(jtf);
    top.add(b);

    this.setContentPane(top);
    this.setVisible(true);         
  }      
}