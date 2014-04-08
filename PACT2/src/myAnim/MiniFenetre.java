package myAnim;

import javax.swing.JInternalFrame;

public class MiniFenetre extends JInternalFrame{
	private static int xy=10;
    public MiniFenetre(int nbre){
      this.setTitle("Fenetre N¡"+nbre);
      this.setClosable(true);
      this.setResizable(true);
      this.setSize(150, 80);
      this.setLocation(xy, xy);
      this.setVisible(true);
    }      
  }
