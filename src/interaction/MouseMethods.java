package interaction;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import building.BuildingAbstract;
import gameEngine.GameData;

public class MouseMethods implements MouseListener {
	
	public MouseMethods() {}

	public void mouseClicked(MouseEvent e) {
		//System.out.println(e.getPoint());
    	for(BuildingAbstract b : GameData.baseArray){
    		if(e.getPoint().x >= (b.getPositionX()) && e.getPoint().x < (b.getPositionX()+b.getSize()) &&
    		   e.getPoint().y >= (b.getPositionY()) && e.getPoint().y < (b.getPositionY()+b.getSize()) &&
    		   b.getType() == 1){	
    			System.out.println("ID " + b.getID());
    			if(GameData.lineSelect.get(0) == 0 && GameData.lineSelect.get(1) == 0){
	    			GameData.lineSelect.set(0, e.getPoint().x);
	    			GameData.lineSelect.set(1, e.getPoint().y);
    			}
    			if(GameData.lineSelect.get(0) != 0 && GameData.lineSelect.get(1) != 0){
	    			GameData.lineSelect.set(2, e.getPoint().x);
	    			GameData.lineSelect.set(3, e.getPoint().y);
	    			GameData.lineSelect.set(4, 1);
    			}
    		}
    	} 
    	System.out.println(GameData.lineSelect.toString());
	}
	
	public void mouseMoved(MouseEvent e) {
		GameData.lineSelect.add(2, e.getPoint().x);
		GameData.lineSelect.add(3, e.getPoint().y);
		System.out.println("OK");
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {
		
	}
	
}