package myAnim;

import java.awt.Color;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class MiniFenetreWindow extends JWindow{

	  public static void main(String[] args){
	    MiniFenetreWindow wind = new MiniFenetreWindow();
	    wind.setVisible(true);
	  }
	   
	  public MiniFenetreWindow(){      
	    setSize(220, 165);
	    setLocationRelativeTo(null);      
	    JPanel pan = new JPanel();
	    JLabel img = new JLabel(new ImageIcon("A.jpeg"));
	    img.setVerticalAlignment(JLabel.CENTER);
	    img.setHorizontalAlignment(JLabel.CENTER);      
	    pan.setBorder(BorderFactory.createLineBorder(Color.blue));
	    pan.add(img);
	    getContentPane().add(pan);
	  }
	}