package test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

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
		
		robot = new RightHandRobot(startRow, startColumn, Room.EAST, widthOfRoom * 3 / 5, widthOfRoom / 5);
		
		addMouseListener(new MouseAdapter() {
		      
		      @Override
		      public void mousePressed(MouseEvent event) {
		        
		        mousePoint = event.getPoint();
		        
		        int column = (int)mousePoint.getX()/widthOfRoom;
		        int row = (int)mousePoint.getY()/widthOfRoom;
		        
		        int x = (int)mousePoint.getX()%widthOfRoom;
		        int y = (int)mousePoint.getY()%widthOfRoom;
		        
		        System.out.println("clicked" + column + " " + row);
		        System.out.println("set" + startColumn + " " + startRow);
		        
		        if(settingStart){
		        	
		        	if(row >= rows) row = rows - 1;
		        	if(column >= columns) column = columns - 1;
		        	
		        	roomsList[startRow][startColumn].setStart();
		        	roomsList[row][column].setStart();
		        	
		        	startRow = row;
		        	startColumn = column;
		        	
		        	settingStart = false;
		        	
		        } else if(settingEnd){
		        	
		        	if(row >= rows) row = rows - 1;
		        	if(column >= columns) column = columns - 1;
		        	
		        	roomsList[endRow][endColumn].setEnd();
		        	roomsList[row][column].setEnd();
		        	
		        	endRow = row;
		        	endColumn = column;
		        	
		        	settingEnd = false;
		        	
		        } else {
			        
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
		        
		        repaint();
		        
		      }
		});
		
	}

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
	
	public void paintComponent(Graphics g){
	    Graphics2D g2 = (Graphics2D) g;
	    
	    for(int i = 0; i < roomsList.length; i++){
	    	for(int k = 0; k < roomsList[i].length; k++){
	    		roomsList[i][k].draw(g2);
	    	}
	    }
	    
	    robot.drawRobot(g2);
	}
	
	public void drawMaze(){
		repaint();
	}
	
	public void setRows(int rows){
		this.rows = rows;
	}
	
	public int getRows(){
		return rows;
	}
	
	public void setColumns(int columns){
		this.columns = columns;
	}
	
	public int getColumns(){
		return columns;
	}
	
	public boolean isOpen(int row, int column, int direction){
		return true;//((Room) roomsList[column][row].getIcon()).isOpen(direction);
	}

	public int getWidth(){
		int width;
		width = columns * widthOfRoom;
		return width;
	}
	
	public int getHeight(){
		int height;
		height = rows * widthOfRoom;
		return height;
	}
	
	public void setStart(){
		settingStart = !settingStart;
	}
	
	public void setEnd(){
		settingEnd = !settingEnd;
	}
	
	public void clearMaze(){
		for(int i = 0; i < roomsList.length; i++){
			for(int k = 0; k < roomsList[i].length; k++){
				roomsList[i][k].clearWalls();
			}
		}
		repaint();
	}
	
}
