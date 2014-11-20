package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class RightHandRobot implements Robot {

	private int x;
	private int y;
	private int width;
	private int direction;
	private int padding;
	
	public RightHandRobot(int x, int y, int direction, int width, int padding){
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.width = width;
		this.padding = padding;
	}
	
	@Override
	public void traverseMaze() {
		// TODO Auto-generated method stub
		
	}
	
	public void drawRobot(Graphics g){

		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.BLUE);
		
		Rectangle2D.Double rect = new Rectangle2D.Double(x + padding, y + padding, x + width, y + width);
		
		g2.fill(rect);
		
		GeneralPath path = determineDirection();
		
		g2.fill(path);
		
	}

	@Override
	public boolean hasVisited() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void turnRight(int numTurns) {
		direction = (direction + numTurns) % 4;
	}

	@Override
	public void turnLeft(int numTurns) {
		direction = (direction - numTurns) % 4;
	}
	
	private GeneralPath determineDirection(){
		
		Point2D.Double r1, r2, r3;
		
		if(direction == Room.NORTH){
			r1 = new Point2D.Double(x + padding + (width / 2), padding / 2);
			r2 = new Point2D.Double(x + padding, y + padding);
			r3 = new Point2D.Double(x + width + padding, y + padding);
		}else if(direction == Room.EAST){
			r1 = new Point2D.Double(x + width + (width / 8) + padding, (y + y + width) / 2 + padding);
			r2 = new Point2D.Double(x + width + padding, y + padding);
			r3 = new Point2D.Double(x + width + padding, y + width + padding);
		} else if(direction == Room.SOUTH){
			r1 = new Point2D.Double(x + width + (width / 8) + padding, (y + y + width) / 2 + padding);
			r2 = new Point2D.Double(x + width + padding, y + padding);
			r3 = new Point2D.Double(x + width + padding, y + width + padding);
		} else {
			r1 = new Point2D.Double(x + width + (width / 8) + padding, (y + y + width) / 2 + padding);
			r2 = new Point2D.Double(x + width + padding, y + padding);
			r3 = new Point2D.Double(x + width + padding, y + width + padding);
		}
		
		Line2D.Double l1 = new Line2D.Double(r2,r1);
		Line2D.Double l2 = new Line2D.Double(r1,r3);
		
		GeneralPath path = new GeneralPath();
		path.append(l1, true);
		path.append(l2, true);
		
		return path;
		
	}

}
