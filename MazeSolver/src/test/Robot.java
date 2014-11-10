package test;

import java.awt.Graphics;

public interface Robot {
	
	public void traverseMaze();
	
	public void drawRobot(Graphics g);
	
	public boolean hasVisited();
	
	public void turnRight(int numTurns);
	public void turnLeft(int numTurns);
	
}
