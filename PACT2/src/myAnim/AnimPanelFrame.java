package myAnim;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AnimPanelFrame extends JFrame{
	private AnimModel animModel = new AnimModel(20, 20, 40, 50);
	private AnimPanel animPanel = new AnimPanel(animModel);
	private JButton btStart = new JButton("Start");
	private JButton btStop = new JButton("Stop");
	private JPanel btnPanel = new JPanel();
	//-------------------------------------------------------------------
	public AnimPanelFrame() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setTitle("Test Animation Panel");
	btStart.setName("START");
	btStop.setName("STOP");
	btStart.addActionListener(new AnimPanelButtonCtrl(animPanel));
	btStop.addActionListener(new AnimPanelButtonCtrl(animPanel));
	btnPanel.setBackground(Color.GRAY);
	btnPanel.add(btStart);
	btnPanel.add(btStop);
	getContentPane().add(animPanel,BorderLayout.CENTER);
	getContentPane().add(btnPanel,BorderLayout.SOUTH);
	pack();
	setLocationRelativeTo(getParent());
	// Center window
}
}
