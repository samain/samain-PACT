package myAnim;

import java.awt.geom.Point2D;

public class AnimModel {
	private Point2D.Float p1;
	// First line extremity
	private Point2D.Float p2;
	// Second line extremity
	//----------------------------------------------------------------------------
	public AnimModel(float initialX1, float initialY1, float initialX2, float initialY2) {
	p1 = new Point2D.Float(initialX1, initialY1);
	p2 = new Point2D.Float(initialX2, initialY2);
	}
	//----------------------------------------------------------------------------
	public synchronized Point2D getPoint1() {
	return (Point2D)(p1.clone());
	}
	//----------------------------------------------------------------------------
	public synchronized Point2D getPoint2() {
	return (Point2D)(p2.clone());
	}
	//----------------------------------------------------------------------------
	public synchronized void newPositionPoint1(float newX, float newY){
	p1.x = newX;
	p1.y = newY;
	}
	// ----------------------------------------------------------------------------
	public synchronized void newPositionPoint2(float newX, float newY) {
	p2.x = newX; 
	p2.y = newY;
	}
	}

