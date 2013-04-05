package simpleShape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public abstract class ShapeAbstract {
	private int width;
	private int height;
	private Point position;
	private Color colorShape;
	private Graphics2D AttachedObject;
	private int x;
	private int y;
	
	/*
	 * Getter / Setter
	 */
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Point getPosition() {
		return position;
	}
	public void setPosition(Point position) {
		this.position = position;
	}
	public Color getColorShape() {
		return colorShape;
	}
	public void setColorShape(Color colorShape) {
		this.colorShape = colorShape;
	}
	public Graphics2D getAttachedObject() {
		return AttachedObject;
	}
	public void setAttachedObject(Graphics2D attachedObject) {
		AttachedObject = attachedObject;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

}
