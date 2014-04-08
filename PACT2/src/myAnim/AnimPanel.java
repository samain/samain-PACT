package myAnim;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class AnimPanel extends JPanel {
	private static float SPEED_X1 = 3, SPEED_Y1 = 2, SPEED_X2 = 4,
			SPEED_Y2 = 2;
	private AnimModel animModel;
	private boolean animRunning = false;

	// ----------------------------------------------------------------------------
	public AnimPanel(AnimModel animModel) {
		this.animModel = animModel;
		setPreferredSize(new Dimension(500, 300));
		setBackground(Color.WHITE);
	}

	// ----------------------------------------------------------------------------
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Line2D.Float line = new Line2D.Float(animModel.getPoint1(),
				animModel.getPoint2());
		g2d.setPaint(new GradientPaint(animModel.getPoint1(), Color.RED,
				animModel.getPoint2(), Color.YELLOW));
		g2d.setStroke(new BasicStroke(5));
		g2d.draw(line);
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