package building;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.Line2D;

import simpleShape.Circle;

public class Nano extends BuildingAbstract{
	private int lifePoint;
	private int positionX;
	private int positionY;
	private int positionX1Line;
	private int positionY1Line;
	private int positionX2Line;
	private int positionY2Line;
	private int size;
	private int ID;
	private int type; // O = neutre, 1 = joueur, 2 = mŽchant
	
	private BuildingAbstract baseFinal;
	
	private Circle upCircle;
	private Line2D.Float linePath = new Line2D.Float();
	private FlatteningPathIterator path;
	float[] coords = new float[6];
	
	private int stepdist = 3; // max pixels per step
	
	private static int number = 0;
	
	public Nano(int l, int x, int y, int x1, int y1, int x2, int y2, int iD, int s, int t, BuildingAbstract b){
		lifePoint = l;
		positionX = x;
		positionY = y;
		positionX1Line = x1;
		positionY1Line = y1;
		positionX2Line = x2;
		positionY2Line = y2;
		size = s;
		type = t;
		ID = iD;
		baseFinal = b;
		linePath.setLine(positionX1Line, positionY1Line, positionX2Line, positionY2Line);
	}
	
    @Override
	public String toString() {
		return "Nano [lifePoint=" + lifePoint + ", positionX=" + positionX
				+ ", positionY=" + positionY + ", positionX1Line="
				+ positionX1Line + ", positionY1Line=" + positionY1Line
				+ ", positionX2Line=" + positionX2Line + ", positionY2Line="
				+ positionY2Line + ", size=" + size + ", ID=" + ID + ", type="
				+ type + ", upCircle=" + upCircle + ", linePath=" + linePath
				+ ", path=" + path + "]";
	}
    
    public void minusLifeNano(int number){
    	lifePoint -= number;
    }
    public void Moving(){
    	double xdiff = positionX2Line - positionX1Line;
    	double ydiff = positionY2Line - positionY1Line;
    	double dist = java.lang.Math.sqrt(xdiff * xdiff + ydiff * ydiff);
    	int steps = (int) ( ( dist - 1 ) / stepdist );
    	if( --steps >= 0 && lifePoint > 0)
    	{
    	   double xdiffVal = xdiff /= steps;
    	   double ydiffVal = ydiff /= steps;
		   positionX1Line += Math.round(xdiffVal);
		   positionY1Line += Math.round(ydiffVal);
	       positionX = positionX1Line;
	       positionY = positionY1Line;
    	}else{
    	   if(positionX != 99999 && lifePoint > 0){
    		   baseFinal.attackLifePoint(lifePoint);
    	   }
 	       positionX = 99999;
 	       positionY = 99999;
    	}
    }

    
	public void paintNano(Graphics2D g){
    	Color color = Color.WHITE;
    	if(getType() == 0){
    		color = Color.LIGHT_GRAY;
    	}
    	if(getType() == 1){
    		color = Color.BLUE;
    	}
    	if(getType() == 2){
    		color = Color.RED;
    	}
    	//System.out.println("OK");
		
		// Construction des formes
		upCircle = new Circle(getSize(), getSize(), getPositionX(), getPositionY(), color);
		
		// binding des formes dans le buffer
		upCircle.drawCircle(g);
		//g.draw(linePath);
		
		// Police
		g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(lifePoint), getPositionX()+(getSize()/2)-7, getPositionY()+(getSize()/2)+3);
    }
	
	public void removeNano(){}
	
	/*
	 * Getter / Setter
	 * @see building.BuildingAbstract#getLifePoint()
	 */
	public int getLifePoint() {
		return lifePoint;
	}
	public void setLifePoint(int lifePoint) {
		this.lifePoint = lifePoint;
	}
	public int getPositionX() {
		return positionX;
	}
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	public int getPositionY() {
		return positionY;
	}
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public static int getNumber() {
		return number;
	}
	public static void setNumber(int number) {
		Nano.number = number;
	}

}
