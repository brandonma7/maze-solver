package test;

import java.awt.Graphics;

public interface Robot {
	
	public void traverseMaze(Maze maze);
	
	public void turnRight();
	public void turnLeft();
	public void moveForward();

	public void drawRobot(Graphics g);

}
