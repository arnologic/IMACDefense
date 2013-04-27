package building;

import java.awt.Graphics2D;

import javax.swing.JFrame;

public class TowerAbstract extends BuildingAbstract {
	private int positionX;
	private int positionY;
	private int size;
	private int ID;
	private int type; // O = neutre, 1 = joueur, 2 = méchant
	
	public void paintTower(Graphics2D g){}
    // getter / setter
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
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
	public int getID() {
		return ID;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void paint() {
		// TODO Auto-generated method stub
		
	}
	public void paintTower(Graphics2D backgroundGame, JFrame frame) {
		// TODO Auto-generated method stub
		
	}
	public void minusLifeNano(int i) {
		// TODO Auto-generated method stub
		
	}
}