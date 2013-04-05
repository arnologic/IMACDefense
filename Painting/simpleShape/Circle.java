package simpleShape;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

public class Circle extends ShapeAbstract{
	private int width;
	private int height;
	private int x;
	private int y;
	private Color colorShape = null;
	private GradientPaint gradientColorShape = null;
	
	public Circle(int w, int h, int x, int y, Color color){
		width = w;
		height = h;
		this.x = x;
		this.y = y;
		colorShape = color;
	}
	
	public Circle(int w, int h, int x, int y, GradientPaint g){
		width = w;
		height = h;
		this.x = x;
		this.y = y;
		gradientColorShape = g;
	}
	
	public void drawCircle(Graphics2D object){
		if(colorShape != null){
			object.setColor(colorShape);
		}
		if(gradientColorShape != null){
			object.setPaint(gradientColorShape);
		}
		object.fillOval(x, y, width, height);
	}

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

	public Color getColorShape() {
		return colorShape;
	}

	public void setColorShape(Color colorShape) {
		this.colorShape = colorShape;
	}

	public GradientPaint getGradientColorShape() {
		return gradientColorShape;
	}

	public void setGradientColorShape(GradientPaint gradientColorShape) {
		this.gradientColorShape = gradientColorShape;
	}

}
