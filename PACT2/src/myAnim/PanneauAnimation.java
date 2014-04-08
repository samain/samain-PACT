package myAnim;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

public class PanneauAnimation extends JPanel{
	
	private Double rectwidth = 200.0;
	private Double rectheight = 300.0;
	private ImageObserver observer;
	private static float SPEED_X1 = 3, SPEED_Y1 = 2, SPEED_X2 = 4,
			SPEED_Y2 = 2;
	private AnimModel animModel;
	private boolean animRunning = false;
	
	@Override
protected void paintComponent(Graphics g) {
		
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			Rectangle2D.Double rectangle = new Rectangle2D.Double(500.0, 500.0, rectwidth,rectheight); // voir si je mets plutôt des coordonnées x,y modifiables
			g2d.setPaint(Color.RED);
			g2d.draw(rectangle);	
			
	}
	// ----------------------------------------------------------------------------
	protected boolean isAnimRunning() {
		return animRunning;
	}

	// ----------------------------------------------------------------------------
	protected void setAnimRunning(boolean running) {
		animRunning = running;
	}

	// ----------------------------------------------------------------------------
	public void startAnimThread() {
		if (isAnimRunning())
			return;
		// Don't start a second thread
		setAnimRunning(true);
		new Thread(new AnimThread(animModel, this, SPEED_X1, SPEED_X2,
				SPEED_X2, SPEED_Y2)).start();
	}
}
