package test;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;

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
	public void traverseMaze(Maze maze) {

		this.x = maze.getStartColumn();
		this.y = maze.getStartRow();
			
		while(maze.checkWall(y, x, direction))
			turnRight();
		turnLeft();
		
		final Timer timer = new Timer(50, null);
		timer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				turnRight();
				if(!maze.isSolved(x, y)){
					while(!maze.checkWall(y, x, direction))
						turnLeft();
					
					moveForward();
					
					maze.drawMaze();
				} else {
					System.out.println("solved");
					timer.stop();
				}
			}
		});
		
		timer.start();
		
	}
	
	private void moveForward() {
		if(direction == Room.NORTH){
			y--;
		} else if(direction == Room.EAST){
			x++;
		} else if(direction == Room.SOUTH){
			y++;
		} else if(direction == Room.WEST){
			x--;
		}
	}

	public void drawRobot(Graphics g){
		
		Graphics2D g2 = (Graphics2D) g;
		
		int widthOfRoom = width + padding + padding;
		
		g2.setColor(Color.BLUE);
		
		Rectangle2D.Double rect = new Rectangle2D.Double(
				x * widthOfRoom + padding,
				y * widthOfRoom + padding,
				width,
				width
				);
		
		g2.fill(rect);
		
		GeneralPath path = determineDirectionPath();
		g2.setColor(Color.YELLOW);
		g2.fill(path);
		
	}

	@Override
	public boolean hasVisited() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void turnRight() {
		direction = (direction + 1) % 4;
	}

	@Override
	public void turnLeft() {
		turnRight();
		turnRight();
		turnRight();
	}
	
	private GeneralPath determineDirectionPath(){
		
		Point2D.Double r1, r2, r3;
		int widthOfRoom = width + padding + padding;
		
		if(direction == Room.NORTH){
			r1 = new Point2D.Double(x * widthOfRoom + padding + (width / 2), y * widthOfRoom + padding / 2);
			r2 = new Point2D.Double(x * widthOfRoom + padding, y * widthOfRoom + padding);
			r3 = new Point2D.Double(x * widthOfRoom + width + padding, y * widthOfRoom + padding);
		}else if(direction == Room.EAST){
			r1 = new Point2D.Double(x * widthOfRoom + width + (width / 8) + padding, (y * widthOfRoom + y * widthOfRoom + width) / 2 + padding);
			r2 = new Point2D.Double(x * widthOfRoom + width + padding, y * widthOfRoom + padding);
			r3 = new Point2D.Double(x * widthOfRoom + width + padding, y * widthOfRoom + width + padding);
		} else if(direction == Room.SOUTH){
			r1 = new Point2D.Double(x * widthOfRoom + padding + (width / 2), y * widthOfRoom + width + padding + (padding / 2));
			r2 = new Point2D.Double(x * widthOfRoom + padding, y * widthOfRoom + width + padding);
			r3 = new Point2D.Double(x * widthOfRoom + width + padding, y * widthOfRoom + width + padding);
		} else {
			r1 = new Point2D.Double(x * widthOfRoom + (padding / 2), y * widthOfRoom + (width / 2) + padding);
			r2 = new Point2D.Double(x * widthOfRoom + padding, y * widthOfRoom + padding);
			r3 = new Point2D.Double(x * widthOfRoom + padding, y * widthOfRoom + width + padding);
		}
		
		Line2D.Double l1 = new Line2D.Double(r2,r1);
		Line2D.Double l2 = new Line2D.Double(r1,r3);
		
		GeneralPath path = new GeneralPath();
		path.append(l1, true);
		path.append(l2, true);
		
		return path;
		
	}

}
