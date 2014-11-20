package test;

import java.awt.Graphics;

public interface Robot {
	
	public void traverseMaze();
	
	public boolean hasVisited();
	
	public void turnRight(int numTurns);
	public void turnLeft(int numTurns);

	public void drawRobot(Graphics g);
	
}
