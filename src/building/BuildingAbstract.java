package building;

import java.awt.Graphics2D;

public abstract class BuildingAbstract {
	private int lifePoint;
	private int bullets;
	private int positionX;
	private int positionY;
	private int size;
	private int type;
	@SuppressWarnings("unused")
	private int ID;
	
	public void paintBase(Graphics2D g){}
	public void setSize(int size) {
		this.size = size;
	}
	public void attack(int l){
		lifePoint-=l;
	}
	public void heal(int l){
		lifePoint+=l;
	}
    // getter / setter
	public int getID() {
		return size;
	}
	public int getSize() {
		return size;
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
	public void setID(int iD) {
		ID = iD;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
