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
	
	private int startRow, startColumn;
	private int endRow, endColumn;
	
	private Point mousePoint;
	
	public Maze(int columns, int rows){
		this.columns = columns;
		this.rows = rows;
		
		this.roomsList = new Room[rows][columns];
		
		this.getWidth();
		
		widthOfRoom = (MazeSolver.WIDTH - 10) / columns;
		if(widthOfRoom > (MazeSolver.HEIGHT * 3 / 4) / rows)
			widthOfRoom = (MazeSolver.HEIGHT * 3 / 4) / rows;
		
		for(int i = 0; i < rows; i++){
			for(int k = 0; k < columns; k++){
				
				roomsList[i][k] = new Room(k * widthOfRoom, i * widthOfRoom, widthOfRoom);
				
			}
		}
		addMouseListener(new MouseAdapter() {
		      
		      @Override
		      public void mousePressed(MouseEvent event) {
		        
		        mousePoint = event.getPoint();
		        
		        int column = (int)mousePoint.getX()/widthOfRoom;
		        int row = (int)mousePoint.getY()/widthOfRoom;
		        
		        int x = (int)mousePoint.getX()%widthOfRoom;
		        int y = (int)mousePoint.getY()%widthOfRoom;

		        System.out.println(mousePoint.getX() + ", " + mousePoint.getX());
		        System.out.println(column + ", " + row);
		        System.out.println(x + ", " + y);
		        
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
			        
			        repaint();
		        }
		        
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
	}
	
	public void drawMaze(){
		//JPanel maze = new JPanel();
		//maze.setSize(MazeSolver.WIDTH - 100, MazeSolver.WIDTH - 100);
		
		repaint();
		
		/*for(int i = 0; i < roomsList.length; i++){
			for(int k = 0; k < roomsList[i].length; k++){
				roomsList[i][k].repaint();
			}
		}*/
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
	
}
