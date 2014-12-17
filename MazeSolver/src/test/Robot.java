package test;

import java.awt.Graphics;

/**
 * Interface for robot that sets up standard functionality.
 * @author Brandon Adams, Kaya Ota, Guillermo Colin
 *
 */

public interface Robot {
	
	public void traverseMaze(Maze maze);
	
	public void turnRight();
	public void turnLeft();
	public void moveForward();

	public void drawRobot(Graphics g);

}
