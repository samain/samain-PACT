import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class testCardLayout {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		final CardLayout cl = new CardLayout();
		final JPanel mother = new JPanel(cl);
		frame.getContentPane().add(mother);
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JButton b1 = new JButton("1");
		JButton b11 = new JButton("a");
		JButton b12 = new JButton("b");
		JButton b2 = new JButton("2");
		
		p1.add(b1);
		p2.add(b2);
		p1.add(b11);
		p1.add(b12);
		
		mother.add(p1,"Premier");
		mother.add(p2,"Second");
		
		b1.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				cl.show(mother, "Second");
			}
		});
		
		b2.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				cl.show(mother, "Premier");
			}
		});
		
		cl.show(mother, "Premier");
		frame.pack();
		frame.setVisible(true);
		
	}

}
