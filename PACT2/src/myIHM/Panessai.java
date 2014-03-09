package myIHM;
import java.awt.Graphics;
import javax.swing.JPanel;
 
public class Panessai extends JPanel { 
  public void paintComponent(Graphics g){
    //x1, y1, width, height, arcWidth, arcHeight
    g.drawRoundRect(10, 10, 30, 50, 10, 10);
    g.drawRoundRect(800, 90, 30, 50, 10, 10);
    g.fillRoundRect(55, 65, 55, 30, 5, 5);
    g.drawRoundRect(1000, 90, 30, 50, 10, 10);
  }               
}

