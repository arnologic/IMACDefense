package simpleShape;

import java.awt.Color;
import java.awt.Graphics2D;

import simpleShape.ShapeAbstract;

public class Rectangle extends ShapeAbstract{
	private int width;
	private int height;
	private int x;
	private int y;
	private Color colorShape;
	
	public Rectangle(int w, int h, int x, int y, Color c){
		width = w;
		height = h;
		this.x = x;
		this.y = y;
		colorShape = c;
	}
	
	public void drawRectangle(Graphics2D object){
		object.setColor(colorShape);
		object.fillRect(x, y, width, height);
	}
	
	/*
	 * Getter / Setter
	 */
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
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColorShape() {
		return colorShape;
	}

	public void setColorShape(Color colorShape) {
		this.colorShape = colorShape;
	}
	private Color color;
}
