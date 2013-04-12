package interaction;


import java.awt.event.MouseEvent;
import math.Intersections;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;

import building.BuildingAbstract;
import building.Nano;
import gameEngine.GameData;

public class MouseMethods implements MouseListener, MouseMotionListener {
	
	public MouseMethods() {}

	public void mouseClicked(MouseEvent e) {}
	
	public void mouseMoved(MouseEvent e) {
		if(GameData.lineSelect.get(4) == 1){
			GameData.lineSelect.set(2, e.getPoint().x);
			GameData.lineSelect.set(3, e.getPoint().y);
			GameData.lineIntersect.setLine(GameData.lineSelect.get(0), GameData.lineSelect.get(1), GameData.lineSelect.get(2), GameData.lineSelect.get(3));

			for(BuildingAbstract b : GameData.baseArray){
				if(b.getType() == 0){
	    			//System.out.println(b.toString());
					Line2D[] rectangleGenerated = Intersections.GenerateRectangle(b);
					if(Intersections.IntersectLineRectangle(GameData.lineIntersect, rectangleGenerated)){
						GameData.flaglineIntersect = true;
		    			System.out.println("INTERDIT");
		    		}else{
		    			GameData.flaglineIntersect = false;
		    		}
				}
	    	}
		}
		//System.out.println(GameData.lineSelect.toString());
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		/*
		 * Base Check
		 */
		//System.out.println(e.getPoint());
    	for(BuildingAbstract b : GameData.baseArray){
    		/*
    		 * Base Joueur Check
    		 */
    		if(e.getPoint().x >= (b.getPositionX()) && e.getPoint().x < (b.getPositionX()+b.getSize()) &&
    		   e.getPoint().y >= (b.getPositionY()) && e.getPoint().y < (b.getPositionY()+b.getSize()) &&
    		   b.getType() == 1){
				if(GameData.lineSelect.get(4) == 0){
					GameData.lineSelect.set(0, b.getPositionX()+(b.getSize()/2));
					GameData.lineSelect.set(1, b.getPositionY()+(b.getSize()/2));
					GameData.lineSelect.set(2, b.getPositionX()+(b.getSize()/2));
					GameData.lineSelect.set(3, b.getPositionY()+(b.getSize()/2));
					GameData.lineSelect.set(4, 1);
					GameData.bDepart = b;
				}else if(GameData.lineSelect.get(4) == 1){
					GameData.lineSelect.set(2, b.getPositionX()+(b.getSize()/2));
					GameData.lineSelect.set(3, b.getPositionY()+(b.getSize()/2));
					if(GameData.lineSelect.get(2) == GameData.lineSelect.get(0) && 
					   GameData.lineSelect.get(3) == GameData.lineSelect.get(1)){
				    	for(int i = 0; i<5; i++){
				    		GameData.lineSelect.set(i, 0);
				    	}
				    	GameData.bDepart = null;
					}else{
						Nano nano = new Nano(b.getLifePoint()/2, b.getPositionX()+(b.getSize()/2), b.getPositionY()+(b.getSize()/2),
								GameData.lineSelect.get(0), GameData.lineSelect.get(1), GameData.lineSelect.get(2), GameData.lineSelect.get(3),
								1, 20, 1, b, GameData.bDepart);
						b.setNanoArray(nano);
						nano.getBaseDepart().minusLifeBase(b.getLifePoint()/2);
				    	for(int i = 0; i<5; i++){
				    		GameData.lineSelect.set(i, 0);
				    		GameData.bDepart = null;
				    	}
					}
			    	//b.displayNanoArray();
				}
    		}
			if(e.getPoint().x >= (b.getPositionX()) && e.getPoint().x < (b.getPositionX()+b.getSize()) &&
    		   e.getPoint().y >= (b.getPositionY()) && e.getPoint().y < (b.getPositionY()+b.getSize()) &&
    		   (b.getType() == 2 || b.getType() == 0) && GameData.lineSelect.get(4) == 1){
					GameData.lineSelect.set(2, b.getPositionX()+(b.getSize()/2));
					GameData.lineSelect.set(3, b.getPositionY()+(b.getSize()/2));
					Nano nano = new Nano(GameData.bDepart.getLifePoint()/2, b.getPositionX()+(b.getSize()/2), b.getPositionY()+(b.getSize()/2),
							GameData.lineSelect.get(0), GameData.lineSelect.get(1), GameData.lineSelect.get(2), GameData.lineSelect.get(3),
							1, 20, 1,  b, GameData.bDepart);
					b.setNanoArray(nano);
					nano.getBaseDepart().minusLifeBase(GameData.bDepart.getLifePoint()/2);
					for(int i = 0; i<5; i++){
			    		GameData.lineSelect.set(i, 0);
			    	}
					GameData.bDepart = null;
			 }
    	} 
	}

	public void mouseReleased(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {}	
}