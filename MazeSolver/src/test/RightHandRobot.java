package test;

import java.awt.Graphics;

public class RightHandRobot implements Robot {

	private int x;
	private int y;
	private int direction;
	
	public RightHandRobot(int x, int y, int direction){
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	@Override
	public void traverseMaze() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawRobot(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasVisited() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void turnRight(int numTurns) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnLeft(int numTurns) {
		// TODO Auto-generated method stub
		
	}

}
