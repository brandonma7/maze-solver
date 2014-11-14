package test;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Maze {

	private int columns;
	private int rows;
	private JLabel[][] roomsList;
	
	public Maze(int columns, int rows){
		this.columns = columns;
		this.rows = rows;
		
		this.roomsList = new JLabel[rows][columns];
		
		int widthOfRoom = (MazeSolver.WIDTH * 3 / 4) / columns;
		if(widthOfRoom > (MazeSolver.HEIGHT * 3 / 4) / rows)
			widthOfRoom = (MazeSolver.HEIGHT * 3 / 4) / rows;
		
		System.out.println(columns + " X " + rows);
		
		for(int i = 0; i < rows; i++){
			for(int k = 0; k < columns; k++){
				
				roomsList[i][k] = new JLabel(new Room(widthOfRoom));
				
			}
		}
		
	}
	
	public void toggleWall(int column, int row, int direction){
		((Room) roomsList[row][column].getIcon()).toggleWall(direction);
	}
	
	public void drawMaze(){
		JPanel maze = new JPanel();
		maze.setSize(MazeSolver.WIDTH - 100, MazeSolver.WIDTH - 100);
		
		for(int i = 0; i < roomsList.length; i++){
			for(int k = 0; k < roomsList[i].length; k++){
				roomsList[i][k].repaint();
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
		return ((Room) roomsList[column][row].getIcon()).isOpen(direction);
	}

	public void addMazeToPanel(JPanel gridArea) {
		for(int i = 0; i < roomsList.length; i++){
			for(int k = 0; k < roomsList[i].length; k++){
				gridArea.add(roomsList[i][k]);
			}
		}
	}
}
