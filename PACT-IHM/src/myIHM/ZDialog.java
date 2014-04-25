package myIHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class ZDialog extends JDialog {
	  private ZDialogInfo zInfo = new ZDialogInfo();
	  private boolean sendData;
	  private JLabel nomLabel, policeLabel;
	  private JComboBox police;
	  private JTextField nom;
	  public ZDialog(JFrame parent, String title, boolean modal){
	    super(parent, title, modal);
	    this.setSize(550, 200);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	    this.initComponent();
	  }
	  public ZDialogInfo showZDialog(){
	    this.sendData = false;
	    this.setVisible(true);      
	    return this.zInfo;      
	  }
	  private void initComponent(){
	
	    //Le nom
	    JPanel panNom = new JPanel();
	    panNom.setBackground(Color.white);
	    panNom.setPreferredSize(new Dimension(220, 60));
	    nom = new JTextField();
	    nom.setPreferredSize(new Dimension(100, 25));
	    panNom.setBorder(BorderFactory.createTitledBorder("Nom du lecteur"));
	    nomLabel = new JLabel("Saisir un nom :");
	    panNom.add(nomLabel);
	    panNom.add(nom);
	    //La police
	    JPanel panPolice = new JPanel();
	    panPolice.setBackground(Color.white);
	    panPolice.setPreferredSize(new Dimension(220, 100));
	    panPolice.setBorder(BorderFactory.createTitledBorder("Taille de la police"));
	    police = new JComboBox();
	    police.addItem("Petite");
	    police.addItem("Moyenne");
	    police.addItem("Grande");
	    policeLabel = new JLabel("Taille de la police : ");
	    panPolice.add(policeLabel);
	    panPolice.add(police);
	   
	    JPanel content = new JPanel();
	    content.setBackground(Color.white);
	    content.add(panNom);
	    content.add(panPolice);
	    JPanel control = new JPanel();
	    JButton okBouton = new JButton("OK");
	    
	    okBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {        
	        zInfo = new ZDialogInfo(nom.getText(), (String)police.getSelectedItem());
	        setVisible(false);
	      }
	      /*public String getAge(){
	        return (tranche1.isSelected()) ? tranche1.getText() : 
	               (tranche2.isSelected()) ? tranche2.getText() : 
	               (tranche3.isSelected()) ? tranche3.getText() : 
	               (tranche4.isSelected()) ? tranche4.getText() : 
	                tranche1.getText();  
	      }
	      public String getTaille(){
	        return (taille.getText().equals("")) ? "180" : taille.getText();
	      }   */   
	    });
	    JButton cancelBouton = new JButton("Annuler");
	    cancelBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {
	        setVisible(false);
	      }      
	    });
	    control.add(okBouton);
	    control.add(cancelBouton);
	   // this.getContentPane().add(panIcon, BorderLayout.WEST);
	    this.getContentPane().add(content, BorderLayout.CENTER);
	    this.getContentPane().add(control, BorderLayout.SOUTH);
	  }  
	}