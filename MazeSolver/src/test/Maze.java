package test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

/**
 * Maze for the user to edit and the robot to traverse.
 * @author Brandon Adams, Kaya Ota, Guillermo Colin
 *
 */

public class Maze extends JComponent {

	private int columns;
	private int rows;
	private Room[][] roomsList;
	
	private int widthOfRoom;
	
	private Robot robot;
	
	private int startRow, startColumn;
	private int endRow, endColumn;
	
	private boolean settingStart = false;
	private boolean settingEnd = false;
	
	private Point mousePoint;
	
	/**
	 * Instantiates maze with specified number of columns and rows.
	 * @param columns
	 * @param rows
	 */
	
	public Maze(int columns, int rows){
		this.columns = columns;
		this.rows = rows;
		
		this.roomsList = new Room[rows][columns];
		
		this.startColumn = this.startRow = 0;
		this.endColumn = columns - 1;
		this.endRow = rows - 1;
		
		this.widthOfRoom = (MazeSolver.WIDTH - 10) / columns;
		if(this.widthOfRoom > (MazeSolver.HEIGHT * 3 / 4) / rows)
			this.widthOfRoom = (MazeSolver.HEIGHT * 3 / 4) / rows;
		
		for(int i = 0; i < rows; i++){
			for(int k = 0; k < columns; k++){
				
				roomsList[i][k] = new Room(k * widthOfRoom, i * widthOfRoom, widthOfRoom);
				
			}
		}
		
		roomsList[startRow][startColumn].setStart();
		roomsList[endRow][endColumn].setEnd();
		
		robot = new RightHandRobot(
				startRow,
				startColumn,
				Room.EAST,
				((double)widthOfRoom) * 3 / 5,
				((double)widthOfRoom) / 5
		);
		
		addMouseListener(mouseActions());
		
	}
	
	/**
	 * When user clicks a room, we pass the x and y of the click 
	 * relative to that room to this function so that we can determine
	 * which direction they intend to toggle the wall in.
	 * @param x
	 * @param y
	 * @return Direction of wall.
	 */

	public int determineWall(int x, int y){
		
		if(x >= widthOfRoom * 9 / 10)
			return Room.EAST;
		if(x <= widthOfRoom / 10)
			return Room.WEST;
		if(y >= widthOfRoom * 9 / 10)
			return Room.SOUTH;
		if(y <= widthOfRoom / 10){
			return Room.NORTH;
		}
		return -1;
	}
	
	public int getRows(){
		return rows;
	}
	
	public int getColumns(){
		return columns;
	}
	
	/**
	 * Get overall width of maze.
	 */

	public int getWidth(){
		int width;
		width = columns * widthOfRoom;
		return width;
	}
	
	/**
	 * Get overall height of maze.
	 */
	
	public int getHeight(){
		int height;
		height = rows * widthOfRoom;
		return height;
	}
	
	/**
	 * Toggle whether the user is trying to set the start position of robot.
	 */
	
	public void setStart(){
		settingStart = !settingStart;
	}
	
	/**
	 * Toggle whether the user is trying to set the end position of robot.
	 */
	
	public void setEnd(){
		settingEnd = !settingEnd;
	}
	
	//Getters!
	public int getStartColumn(){return startColumn;}
	public int getEndColumn(){return endColumn;}
	public int getStartRow(){return startRow;}
	public int getEndRow(){return endRow;}

	/**
	 * Remove all the walls in the maze.
	 */
	
	public void clearMaze(){
		for(int i = 0; i < roomsList.length; i++){
			for(int k = 0; k < roomsList[i].length; k++){
				roomsList[i][k].clearWalls();
			}
		}
		repaint();
	}
	
	/**
	 * Turn robot right.
	 */
	
	public void rotateRobot(){
		robot.turnRight();
		repaint();
	}
	
	/**
	 * Check if a specific room has a wall in a specific direction
	 * @param row
	 * @param column
	 * @param direction
	 * @return True if there is NO wall in that room in that direction.
	 */
	
	public boolean checkWall(int row, int column, int direction){
		return roomsList[row][column].isOpen(direction);
	}
	
	/**
	 * Fill the outer walls with walls so that the robot does not try to escape.
	 * Call robot to traverse maze.
	 */
	
	public void traverseMaze(){
		createOuterWalls();
		robot.traverseMaze(this);
	}
	
	/**
	 * Check if current location is end of maze.
	 * @param row
	 * @param column
	 * @return True if room is the end of the maze.
	 */
	
	public boolean isSolved(int row, int column){
		return roomsList[row][column].isEnd();
	}
	
	/**
	 * Creates all the outer walls.
	 */
	
	private void createOuterWalls(){
		for(int i = 0; i < columns; i++){
			if(roomsList[0][i].isOpen(Room.NORTH))
				roomsList[0][i].toggleWall(Room.NORTH);
		}
		for(int i = 0; i < rows; i++){
			if(roomsList[i][columns - 1].isOpen(Room.EAST))
				roomsList[i][columns - 1].toggleWall(Room.EAST);
		}
		for(int i = 0; i < columns; i++){
			if(roomsList[rows - 1][i].isOpen(Room.SOUTH))
				roomsList[rows - 1][i].toggleWall(Room.SOUTH);
		}
		for(int i = 0; i < rows; i++){
			if(roomsList[i][0].isOpen(Room.WEST))
				roomsList[i][0].toggleWall(Room.WEST);
		}
		repaint();
	}
	
	/**
	 * Creates MouseAdapter for mouse events.
	 * @return MouseAdapter for mouse events.
	 */
	
	public MouseAdapter mouseActions(){
		return new MouseAdapter() {
		      
		      @Override
		      public void mousePressed(MouseEvent event) {
		        
		        mousePoint = event.getPoint();
		        
		        int column = (int)mousePoint.getX()/widthOfRoom;
		        int row = (int)mousePoint.getY()/widthOfRoom;
		        
		        int x = (int)mousePoint.getX()%widthOfRoom;
		        int y = (int)mousePoint.getY()%widthOfRoom;
		        
		        if(settingStart){
		        	setStart(row, column);
		        } else if(settingEnd){
		        	setEnd(row, column);
		        } else {
		        	toggleWall(row, column, x, y);
		        }
		        
		        repaint();
		        
		      }
		};
	}
	
	/**
	 * Sets the start location for the maze.
	 * @param row
	 * @param column
	 */
	
	private void setStart(int row, int column){

    	if(row >= rows) row = rows - 1;
    	if(column >= columns) column = columns - 1;
    	
    	roomsList[startRow][startColumn].setStart();
    	roomsList[row][column].setStart();
    	
    	startRow = row;
    	startColumn = column;
    	
    	settingStart = false;
	}
	
	/**
	 * Sets the end location of the maze.
	 * @param row
	 * @param column
	 */
	
	private void setEnd(int row, int column){
    	
    	if(row >= rows) row = rows - 1;
    	if(column >= columns) column = columns - 1;
    	
    	roomsList[endRow][endColumn].setEnd();
    	roomsList[row][column].setEnd();
    	
    	endRow = row;
    	endColumn = column;
    	
    	settingEnd = false;
	}
	
	/**
	 * Toggles whether wall is present or absent.
	 * @param row
	 * @param column
	 * @param x
	 * @param y
	 */
	
	private void toggleWall(int row, int column, int x, int y){
        
        int wall = determineWall(x, y);
        
        if(row >= rows || column >= columns) wall = -1;
        
        if(wall != -1){
	        roomsList[row][column].toggleWall(wall);
	        try{
		        switch(wall){
			        case(Room.NORTH): roomsList[row-1][column].toggleWall(Room.SOUTH);break;
			        case(Room.EAST): roomsList[row][column+1].toggleWall(Room.WEST);break;
			        case(Room.SOUTH): roomsList[row+1][column].toggleWall(Room.NORTH);break;
			        case(Room.WEST): roomsList[row][column-1].toggleWall(Room.EAST);break;
		        }
	        } catch(IndexOutOfBoundsException e){
	        	//do nothing
	        }
        }
	}

	/**
	 * Paint rooms and robot.
	 */
	
	@Override
	public void paintComponent(Graphics g){
	
	    Graphics2D g2 = (Graphics2D) g;
	    
	    for(int i = 0; i < roomsList.length; i++){
	    	for(int k = 0; k < roomsList[i].length; k++){
	    		roomsList[i][k].draw(g2);
	    	}
	    }
	    
	    robot.drawRobot(g2);
	}

	/**
	 * Call repaint method.
	 */
	
	public void drawMaze(){
		repaint();
	}
	
}
