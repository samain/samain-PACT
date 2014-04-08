package myAnim;

public class AnimThread implements Runnable {
	private AnimModel animModel;
	private PanneauAnimation animPanel;
	private float dx1, dy1, dx2, dy2;

	// ----------------------------------------------------------------------------
	public AnimThread(AnimModel animModel, PanneauAnimation animPanel, float speedX1,
			float speedY1, float speedX2, float speedY2) {
		this.animModel = animModel;
		this.animPanel = animPanel;
		this.dx1 = speedX1;
		this.dy1 = speedY1;
		this.dx2 = speedX2;
		this.dy2 = speedY2;
	}

	// ----------------------------------------------------------------------------
	public void run() {
		while (animPanel.isAnimRunning()) {
			float newX = incrX1Pos((float) (animModel.getPoint1().getX()),
					animPanel.getSize().width);
			float newY = incrY1Pos((float) (animModel.getPoint1().getY()),
					animPanel.getSize().height);
			animModel.newPositionPoint1(newX, newY);
			newX = incrX2Pos((float) (animModel.getPoint2().getX()),
					animPanel.getSize().width);
			newY = incrY2Pos((float) (animModel.getPoint2().getY()),
					animPanel.getSize().height);
			animModel.newPositionPoint2(newX, newY);
			animPanel.repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// ----------------------------------------------------------------------------
	private float incrX1Pos(float startPos, float limit) {
		float newPos = (float) (animModel.getPoint1().getX()) + dx1;
		if (newPos < 0) {
			dx1 = -dx1;
			return 0;
		}
		if (newPos > limit) {
			dx1 = -dx1;
			return limit;
		}
		return newPos;
	}

	// ----------------------------------------------------------------------------
	private float incrY1Pos(float startPos, float limit) {
		float newPos = (float) (animModel.getPoint1().getY()) + dy1;
		if (newPos < 0) {
			dy1 = -dy1;
			return 0;
		}
		if (newPos > limit) {
			dy1 = -dy1;
			return limit;
		}
		return newPos;
	}

	// ----------------------------------------------------------------------------
	private float incrX2Pos(float startPos, float limit) {
		float newPos = (float) (animModel.getPoint2().getX()) + dx2;
		if (newPos < 0) {
			dx2 = -dx2;
			return 0;
		}
		if (newPos > limit) {
			dx2 = -dx2;
			return limit;
		}
		return newPos;
	}

	// ----------------------------------------------------------------------------
	private float incrY2Pos(float startPos, float limit) {
		float newPos = (float) (animModel.getPoint2().getY()) + dy2;
		if (newPos < 0) {
			dy2 = -dy2;
			return 0;
		}
		if (newPos > limit) {
			dy2 = -dy2;
			return limit;
		}
		return newPos;
	}
}
