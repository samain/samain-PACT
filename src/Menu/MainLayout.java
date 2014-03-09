
package Menu;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

class MainLayout {

	private static void createAndShowGUI() {
		// On fabrique et on met en forme une fenêtre JFrame
		JFrame principalFrame = new JFrame("Immersive Reading");
		principalFrame.setSize(900, 1000);
		principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On fabrique le contentPane
		Fenetreclayout nappe = new Fenetreclayout();
		nappe.addComponentToPane(principalFrame.getContentPane());

		principalFrame.pack();
		principalFrame.setVisible(true);
	}

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}