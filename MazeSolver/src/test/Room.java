package test;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JLabel;

public class Room {

	public static int NORTH = 0;
	public static int EAST = 1;
	public static int SOUTH = 2;
	public static int WEST = 3;
	
	private boolean[] walls;
	
	public Room(){
		
		this.walls = new boolean[4];
		walls[0] = false;
		walls[1] = false;
		walls[2] = false;
		walls[3] = false;
		
	}
	
	public void toggleWall(int direction){
		walls[direction] = !walls[direction];
	}
	
	public boolean isOpen(int direction){
		return walls[direction];
	}
	
	public void drawRoom(int width, int x, int y){
		JLabel room = new JLabel();
		Rectangle rect = new Rectangle(width, width, x, y);
		Icon icon = new Icon();
	}
}