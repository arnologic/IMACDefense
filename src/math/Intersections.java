package math;

import gameEngine.GameData;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import building.BuildingAbstract;

public class Intersections {
	public static boolean doLinesIntersect(Line2D line1, Line2D line2){
		return  CrossProduct(line1.getP1(), line1.getP2(), line2.getP1()) !=
                CrossProduct(line1.getP1(), line1.getP2(), line2.getP2()) ||
                CrossProduct(line2.getP1(), line2.getP2(), line1.getP1()) !=
                CrossProduct(line2.getP1(), line2.getP2(), line1.getP2());
	}
	
	public static double CrossProduct(Point2D p1, Point2D p2, Point2D p3)
    {
         return (p2.getX() - p1.getX() ) * (p3.getY() - p1.getY()) - (p3.getX() - p1.getX()) * (p2.getY() - p1.getY());
    }
	 
	public static boolean IntersectLineRectangle(Line2D l1, Line2D[] tab){
		if(Geometry.BfindLineSegmentIntersection(GameData.lineIntersect.getX1(), GameData.lineIntersect.getY1(), 
		   GameData.lineIntersect.getX2(), GameData.lineIntersect.getY2(), 
		   tab[0].getX1(), tab[0].getY1(), tab[0].getX2(), tab[0].getY2()) == 1 ||
		   
		   Geometry.BfindLineSegmentIntersection(GameData.lineIntersect.getX1(), GameData.lineIntersect.getY1(), 
				   GameData.lineIntersect.getX2(), GameData.lineIntersect.getY2(), 
				   tab[1].getX1(), tab[1].getY1(), tab[1].getX2(), tab[1].getY2()) == 1 ||
				   
		   Geometry.BfindLineSegmentIntersection(GameData.lineIntersect.getX1(), GameData.lineIntersect.getY1(), 
				   GameData.lineIntersect.getX2(), GameData.lineIntersect.getY2(), 
				   tab[2].getX1(), tab[2].getY1(), tab[2].getX2(), tab[2].getY2()) == 1 ||
				   
		   Geometry.BfindLineSegmentIntersection(GameData.lineIntersect.getX1(), GameData.lineIntersect.getY1(), 
				   GameData.lineIntersect.getX2(), GameData.lineIntersect.getY2(), 
				   tab[3].getX1(), tab[3].getY1(), tab[3].getX2(), tab[3].getY2()) == 1){
			return true;
		}else{
			return false;
		}
	}
	
	public static Line2D[] GenerateRectangle(BuildingAbstract b){
		Line2D tab[] = new Line2D[4];
		
		Line2D lineRec1 = new Line2D.Float(b.getPositionX(), b.getPositionY(), b.getPositionX()+b.getSize(), b.getPositionY());
		Line2D lineRec2 = new Line2D.Float(b.getPositionX(), b.getPositionY(), b.getPositionX(), b.getPositionY()+b.getSize());
		Line2D lineRec3 = new Line2D.Float(b.getPositionX(), b.getPositionY()+b.getSize(), b.getPositionX()+b.getSize(), b.getPositionY()+b.getSize());
		Line2D lineRec4 = new Line2D.Float(b.getPositionX()+b.getSize(), b.getPositionY()+b.getSize(), b.getPositionX()+b.getSize(), b.getPositionY()+b.getSize());
		
		tab[0] = lineRec1;
		tab[1] = lineRec2;
		tab[2] = lineRec3;
		tab[3] = lineRec4;
		
		return tab;
	}
}
