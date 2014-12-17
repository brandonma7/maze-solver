package test;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;

/**
 * Specific implementation of Robot interface. The exact algorithm of this robot
 * to solve the maze in not actually relevant to project, it is just an example
 * to show that the program works.
 * @author Brandon Adams, Kaya Ota, Guillermo Colin
 *
 */

public class RightHandRobot implements Robot {

	private int x;
	private int y;
	private double width;
	private int direction;
	private double padding;//Distance between robot and walls of room
	private int counter;//How many rooms the robot has visited
	final private int SPEED = 250;//How fast robot moves from one room to another
	
	/**
	 * Instantiate robot that follows right hand rule to solve maze.
	 * @param x Start column
	 * @param y Start row
	 * @param direction Start direction
	 * @param width 
	 * @param padding Distance between robot and north/west walls
	 */
	
	public RightHandRobot(int x, int y, int direction, double width, double padding){
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.width = width;
		this.padding = padding;
		this.counter = 0;
	}
	
	/**
	 * Robot will attempt to solve maze via right hand rule
	 * maze Maze instance to traverse
	 */
	
	@Override
	public void traverseMaze(Maze maze) {

		counter = 0;
		
		this.x = maze.getStartColumn();
		this.y = maze.getStartRow();
			
		//Setting start direction
		while(maze.checkWall(y, x, direction))
			turnRight();
		turnLeft();
		
		final Timer timer = new Timer(SPEED, null);
		timer.addActionListener(solveAlgorithm(maze, timer));
		
		timer.start();
		
	}
	
	/**
	 * Specific algorithm for right hand rule
	 * @param maze Instance of maze to solve.
	 * @param timer Instance of timer that facilitates the loop for this algorithm
	 * @return ActionListener to return to timer. ActionPerformed should contain code
	 * that is repeated over and over until maze is solved.
	 */
	
	public ActionListener solveAlgorithm(Maze maze, Timer timer){
		
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				turnRight();
				if(!maze.isSolved(y, x)){
					while(!maze.checkWall(y, x, direction))
						turnLeft();
					
					moveForward();
					
					maze.drawMaze();
				} else {
					MazeSolver.finishPrompt(counter, true, maze);
					timer.stop();
				}
			}
		};
		
	}
	
	/**
	 * Move robot one square forward in chosen direction.
	 */
	
	public void moveForward() {
		if(direction == Room.NORTH){
			y--;
		} else if(direction == Room.EAST){
			x++;
		} else if(direction == Room.SOUTH){
			y++;
		} else if(direction == Room.WEST){
			x--;
		}
		counter++;
	}

	/**
	 * Rotates robot 90 degrees clockwise.
	 */
	
	@Override
	public void turnRight() {
		direction = (direction + 1) % 4;
	}

	/**
	 * Rotates robot 90 degrees counter-clockwise. (Technically it rotates
	 * robot 270 degrees clockwise, but now we're just splitting hairs)
	 */

	@Override
	public void turnLeft() {
		turnRight();
		turnRight();
		turnRight();
	}
	
	/**
	 * How to draw robot.
	 */

	public void drawRobot(Graphics g){
		
		Graphics2D g2 = (Graphics2D) g;
		
		double widthOfRoom = width + padding + padding;
		
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
	
	/**
	 * This method uses the robot's direction and coordinates to create a 
	 * GeneralPath object to give a visual representation of robot's direction.
	 * @return GeneralPath to draw for front of robot based on direction.
	 */
	
	private GeneralPath determineDirectionPath(){
		
		Point2D.Double r1, r2, r3;
		double widthOfRoom = width + padding + padding;
		
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
