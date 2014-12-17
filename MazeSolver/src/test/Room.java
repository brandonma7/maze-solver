package test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * This class holds all necessary attributes of a room in the maze.
 * @author Brandon Adams, Kaya Ota, Guillermo Colin
 *
 */

public class Room {

	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	private final int OPEN_DOOR_WIDTH = 1;
	private final int CLOSED_DOOR_WIDTH = 5;
	
	private boolean[] walls;

	private int x;
	private int y;
	private final int width;
	
	private boolean start, end;

	/**
	 * Creates a Room object with specified x, y, and width
	 * @param x Specific x coordinate of room.
	 * @param y Specific y coordinate of room.
	 * @param width
	 */
	
	public Room(int x, int y, int width){
		
		this.x = x;
		this.y = y;
		
		this.walls = new boolean[4];
		walls[0] = false;
		walls[1] = false;
		walls[2] = false;
		walls[3] = false;
		
		this.width = width;
		
	}
	
	/**
	 * If wall is present in specified direction, remove it.
	 * If wall is absent in specified direction, add one.
	 * @param direction
	 */
	
	public void toggleWall(int direction){
		walls[direction] = !walls[direction];
	}
	
	/**
	 * Is there a wall in specified direction?
	 * @param direction
	 * @return True if there is no wall.
	 */
	
	public boolean isOpen(int direction){
		return !walls[direction];
	}
	
	/**
	 * Set this room as the starting point for the maze.
	 */
	
	public void setStart(){
		start = !start;
	}
	
	/**
	 * Set this room as the end point for the maze.
	 */
	
	public void setEnd(){
		end = !end;
	}
	
	public boolean isStart(){
		return start;
	}
	
	public boolean isEnd(){
		return end;
	}
	
	/**
	 * Remove all walls of this room.
	 */
	
	public void clearWalls(){
		for(int i = 0; i < walls.length; i++){
			walls[i] = false;
		}
	}
	
	/**
	 * Draw room and walls.
	 * If starting point-room is green.
	 * If end point-room is red.
	 * If neither-room is white.
	 * If wall is present-thick black border.
	 * If wall is absent-thin gray border.
	 * @param g2
	 */
	
	public void draw(Graphics2D g2){
		
		g2.setColor(Color.BLACK);
		
		//Rooms appear slightly off, adding this offset fixes that.
		int widthOffset = -1;

		Point2D.Double r1 = new Point2D.Double(x, y);
		Point2D.Double r2 = new Point2D.Double(x + width + widthOffset, y);
		Point2D.Double r3 = new Point2D.Double(x + width + widthOffset, y + width + widthOffset);
		Point2D.Double r4 = new Point2D.Double(x, y + width + widthOffset);
		
		Line2D.Double top = new Line2D.Double(r1, r2);
		Line2D.Double right = new Line2D.Double(r2, r3);
		Line2D.Double bottom = new Line2D.Double(r3, r4);
		Line2D.Double left = new Line2D.Double(r4, r1);
		
		if(start){
			Rectangle2D.Double rect = new Rectangle2D.Double(x, y, x + width + widthOffset, y + width + widthOffset);
			g2.setColor(Color.GREEN);
			g2.fill(rect);
		} else if(end){
			Rectangle2D.Double rect = new Rectangle2D.Double(x, y, x + width + widthOffset, y + width + widthOffset);
			g2.setColor(Color.RED);
			g2.fill(rect);
		} else {
			Rectangle2D.Double rect = new Rectangle2D.Double(x, y, x + width + widthOffset, y + width + widthOffset);
			g2.setColor(Color.WHITE);
			g2.fill(rect);
		}
		
		if(walls[NORTH]){
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(CLOSED_DOOR_WIDTH));
		} else {
			g2.setColor(Color.GRAY);
			g2.setStroke(new BasicStroke(OPEN_DOOR_WIDTH));
		}
		g2.draw(top);
		
		if(walls[EAST]){
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(CLOSED_DOOR_WIDTH));
		} else {
			g2.setColor(Color.GRAY);
			g2.setStroke(new BasicStroke(OPEN_DOOR_WIDTH));
		}
		g2.draw(right);
		
		if(walls[SOUTH]){
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(CLOSED_DOOR_WIDTH));
		} else {
			g2.setColor(Color.GRAY);
			g2.setStroke(new BasicStroke(OPEN_DOOR_WIDTH));
		}
		g2.draw(bottom);
		
		if(walls[WEST]){
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(CLOSED_DOOR_WIDTH));
		} else {
			g2.setColor(Color.GRAY);
			g2.setStroke(new BasicStroke(OPEN_DOOR_WIDTH));
		}
		g2.draw(left);
	}
}