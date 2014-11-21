package test;

import java.awt.Graphics;

public interface Robot {
	
	public void traverseMaze(Maze maze);
	
	public boolean hasVisited();
	
	public void turnRight();
	public void turnLeft();

	public void drawRobot(Graphics g);

}
