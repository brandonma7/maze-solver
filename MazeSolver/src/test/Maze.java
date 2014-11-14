package test;

import java.util.List;

import javax.swing.JPanel;

public class Maze {

	private int columns;
	private int rows;
	private Room[][] roomsList;
	
	public Maze(int columns, int rows){
		this.columns = columns;
		this.rows = rows;
		
		this.roomsList = new Room[columns][rows];
		
		for(int i = 0; i < columns; i++){
			for(int k = 0; k < rows; k++){
				
				roomsList[i][k] = new Room();
				
			}
		}
		
	}
	
	public void toggleWall(int column, int row, int direction){
		roomsList[column][row].toggleWall(direction);
	}
	
	public void drawMaze(){
		JPanel maze = new JPanel();
		maze.setSize(MazeSolver.WIDTH - 100, MazeSolver.WIDTH - 100);
		
		int widthOfRoom = (MazeSolver.WIDTH - 100) / columns;
		if(widthOfRoom > (MazeSolver.HEIGHT - 100) / rows)
			widthOfRoom = (MazeSolver.HEIGHT - 100) / rows;
		
		for(int i = 0; i < roomsList.length; i++){
			for(int k = 0; k < roomsList[i].length; k++){
				
				maze.add(roomsList[i][k].drawRoom(widthOfRoom, widthOfRoom * k, widthOfRoom * i));
				
			}
		}
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
		return roomsList[column][row].isOpen(direction);
	}
}
