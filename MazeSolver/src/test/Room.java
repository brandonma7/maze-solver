package test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.swing.Icon;
import javax.swing.JLabel;

public class Room {

	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	private final int OPEN_DOOR_WIDTH = 1;
	private final int CLOSED_DOOR_WIDTH = 5;
	
	private boolean[] walls;

	private int x;
	private int y;
	private final int width;
	
	public Room(int x, int y, int width){
		
		this.x = x;
		this.y = y;
		
		this.walls = new boolean[4];
		walls[0] = false;
		walls[1] = false;
		walls[2] = false;
		walls[3] = false;
		
		this.width = width;
		
	}
	
	public void toggleWall(int x, int y){
		
		int direction = 0;
		
		walls[direction] = !walls[direction];
	}
	
	public void toggleWall(int direction){
		walls[direction] = !walls[direction];
	}
	
	public boolean isOpen(int direction){
		return walls[direction];
	}
	
	public void draw(Graphics2D g2){
		
		g2.setColor(Color.BLACK);
		
		Point2D.Double r1,r2,r3,r4;

		r1 = new Point2D.Double(x, y);
		r2 = new Point2D.Double(x + width - 1, y);
		r3 = new Point2D.Double(x + width - 1, y + width - 1);
		r4 = new Point2D.Double(x, y + width - 1);
		
		Line2D.Double top, right, bottom, left;
		top = new Line2D.Double(r1, r2);
		right = new Line2D.Double(r2, r3);
		bottom = new Line2D.Double(r3, r4);
		left = new Line2D.Double(r4, r1);
		
		if(walls[NORTH]){
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(CLOSED_DOOR_WIDTH));
		} else {
			g2.setColor(Color.GRAY);
			g2.setStroke(new BasicStroke(OPEN_DOOR_WIDTH));
		}
		g2.draw(top);
		
		if(walls[EAST]){
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(CLOSED_DOOR_WIDTH));
		} else {
			g2.setColor(Color.GRAY);
			g2.setStroke(new BasicStroke(OPEN_DOOR_WIDTH));
		}
		g2.draw(right);
		
		if(walls[SOUTH]){
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(CLOSED_DOOR_WIDTH));
		} else {
			g2.setColor(Color.GRAY);
			g2.setStroke(new BasicStroke(OPEN_DOOR_WIDTH));
		}
		g2.draw(bottom);
		
		if(walls[WEST]){
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(CLOSED_DOOR_WIDTH));
		} else {
			g2.setColor(Color.GRAY);
			g2.setStroke(new BasicStroke(OPEN_DOOR_WIDTH));
		}
		g2.draw(left);
	}
}