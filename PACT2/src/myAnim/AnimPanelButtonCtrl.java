package myAnim;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AnimPanelButtonCtrl implements ActionListener{
	private AnimPanel animPanel;
	//----------------------------------------------------------------------------
	public AnimPanelButtonCtrl(AnimPanel animPanel) {
	this.animPanel = animPanel;
	}
	//----------------------------------------------------------------------------
	public void actionPerformed(ActionEvent event) {
	if (((JButton)(event.getSource())).getName().equals("STOP")) {
	animPanel.setAnimRunning(false);
	}
	else if (((JButton)(event.getSource())).getName().equals("START")) {
	animPanel.startAnimThread();
	}
	}
}
