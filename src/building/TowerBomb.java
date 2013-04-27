package building;

import gameEngine.GameData;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.util.ArrayList;

import simpleShape.Rectangle;

public class TowerBomb extends BuildingAbstract {
	private int positionX;
	private int positionY;
	private int size;
	private int ID;
	private int type; // O = neutre, 1 = joueur, 2 = méchant
	private static int number = 0;
	private ArrayList<BuildingAbstract> nanoArray = new ArrayList<BuildingAbstract>();
	
	public TowerBomb(int x, int y, int s, int id, int t){
		size = s;
		positionX = x;
		positionY = y;
		ID = id;
		type = t;
		setNumber(getNumber() + 1);
	}
	public TowerBomb(int s, int id, int x, int y){
		size = 10;
		positionX = x;
		positionY = y;	
		ID = id;
		setNumber(getNumber() + 1);
	}  
	
	public BuildingAbstract getTower(int number) {
    	for(BuildingAbstract b : GameData.towerArray){
    		if(b.getType() == number){
    			return b;
    		}
    	}
    	return null;
	}
    public void paintTower(Graphics2D g){
    	Color color = Color.WHITE;
    	if(getType() == 1){
    		color = Color.CYAN;
    	}
    	if(getType() == 2){
    		color = Color.MAGENTA;
    	}
		
		// Construction des formes
		Rectangle towerRectangle = new Rectangle(getSize()+6, getSize()+6, getPositionX()-3, getPositionY()-3, Color.WHITE);
		Rectangle upRectangle = new Rectangle(getSize(), getSize(), getPositionX(), getPositionY(), color);
				
		// binding des formes dans le buffer
		towerRectangle.drawRectangle(g);
		upRectangle.drawRectangle(g);
		
		// Police
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.setColor(Color.BLACK);
		g.drawString("B", getPositionX()+(getSize()/2)-8, getPositionY()+(getSize()/2)+7);
		
    }
    
	public void iterateNano(){
		for(BuildingAbstract n : nanoArray){
			n.Moving();
		}
	}
	
    @Override
	public String toString() {
		return "Tower [positionX=" + positionX + ", positionY=" + positionY
				+ ", size=" + size + ", ID=" + ID 
				+ ", Type=" + type + "]";
	}
	// getter / setter
	public int getID() {
		return ID;
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
		TowerBomb.number = number;
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
}

