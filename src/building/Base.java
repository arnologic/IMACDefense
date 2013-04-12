package building;

import gameEngine.GameData;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import simpleShape.Circle;

public class Base extends BuildingAbstract {
	private int lifePoint;
	private int bullets;
	private int positionX;
	private int positionY;
	private int size;
	private int ID;
	private int type; // O = neutre, 1 = joueur, 2 = méchant
	private static int number = 0;
	private ArrayList<BuildingAbstract> nanoArray = new ArrayList<BuildingAbstract>();
	
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
	public Base(int x, int y, int s, int id, int t){
		lifePoint = 10;
		bullets = 5;
		size = s;
		positionX = x;
		positionY = y;
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
	
	public void paintNano(Graphics2D g){
		//System.out.println("OK");
		//System.out.println(nanoArray.toString());
		for(BuildingAbstract n : nanoArray){
			//System.out.println("OK");
			//System.out.println(nanoArray.toString());
			n.paintNano(g);
		}
	}
	public void attackLifePoint(int number){
		lifePoint-= number;
		if(lifePoint <= 0){
			type = 1;
			lifePoint = number/2;
		}
	}
    public void minusLifeNano(int number){
		for(BuildingAbstract n : nanoArray){
			n.minusLifeNano(1);
		}
    }
    public void minusLifeBase(int number){
    	lifePoint -= number;
    	size -= number;
    }
	public void addLifeBase(int number) {
		lifePoint += number;
		size += number;
	}
	public BuildingAbstract getBase(int number) {
    	for(BuildingAbstract b : GameData.baseArray){
    		if(b.getType() == number){
    			return b;
    		}
    	}
    	return null;
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
		
		// Police
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.setColor(Color.BLACK);
		g.drawString(String.valueOf(lifePoint), getPositionX()+(getSize()/2)-12, getPositionY()+(getSize()/2)+5);
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
    
	public void iterateNano(){
		for(BuildingAbstract n : nanoArray){
			n.Moving();
		}
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
	public void setSize(int s){
		this.size = s;
	}
	public void setNanoArray(Nano nano) {
		nanoArray.add(nano);
	}
	public void displayNanoArray() {
		for(BuildingAbstract n : nanoArray){
			System.out.println(n.toString());
		}
	}
}
