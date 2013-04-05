package building;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.util.Random;

import simpleShape.Circle;

public class Base extends BuildingAbstract {
	private int lifePoint;
	private int bullets;
	private int positionX;
	private int positionY;
	private int size;
	private int ID;
	private int type; // O = neutre, 1 = joueur, 2 = mŽchant
	private static int number = 0;
	
	public Base(Random random, int s, int id, int t){
		lifePoint = 10;
		bullets = 5;
		size = s;
		positionX = showRandomInteger(0+100, 1024-100, random);
		positionY = showRandomInteger(0+100, 768-100, random);
		ID = id;
		type = t;
		setNumber(getNumber() + 1);
	}	
	public Base(int l, int b, int s, int id, Random random){
		lifePoint = l;
		bullets = b;
		size = s;
		positionX = showRandomInteger(0+100, 1024-100, random);
		positionY = showRandomInteger(0+100, 768-100, random);
		ID = id;
		setNumber(getNumber() + 1);
	}
	public Base(int l, int b, int s, int id, int x, int y){
		lifePoint = l;
		bullets = b;
		size = 10;
		positionX = x;
		positionY = y;	
		ID = id;
		setNumber(getNumber() + 1);
	}  
	
    public void paintBase(Graphics2D g){
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
    	
		GradientPaint gradient = new GradientPaint(getPositionX(), getPositionY(),Color.WHITE,getPositionX()+getSize(), getPositionY()+getSize(), color);
		
		// Construction des formes
		Circle baseCircle = new Circle(getSize()+6, getSize()+6, getPositionX()-3, getPositionY()-3, Color.WHITE);
		Circle upCircle = new Circle(getSize(), getSize(), getPositionX(), getPositionY(), gradient);
		
		// binding des formes dans le buffer
		baseCircle.drawCircle(g);
		upCircle.drawCircle(g);
    }
    
    public int showRandomInteger(int aStart, int aEnd, Random aRandom){
        if ( aStart > aEnd ) {
          throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long)aEnd - (long)aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * aRandom.nextDouble());
        return (int)(fraction + aStart);    
     }
    
    
    @Override
	public String toString() {
		return "Base [lifePoint=" + lifePoint + ", bullets=" + bullets
				+ ", positionX=" + positionX + ", positionY=" + positionY
				+ ", size=" + size + ", ID=" + ID + ", type=" + type + "]";
	}
	// getter / setter
	public int getID() {
		return ID;
	}
	public int getLifePoint() {
		return lifePoint;
	}
	public void setLifePoint(int lifePoint) {
		this.lifePoint = lifePoint;
	}
	public int getBullets() {
		return bullets;
	}
	public void setBullets(int bullets) {
		this.bullets = bullets;
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
	public static int getNumber() {
		return number;
	}
	public static void setNumber(int number) {
		Base.number = number;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
