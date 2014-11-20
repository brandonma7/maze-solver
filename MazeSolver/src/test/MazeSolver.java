package test;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MazeSolver {

	public static final int WIDTH = 600;
	public static final int HEIGHT = 750;
	
	private Maze maze;
	
	public static void main(String[] args){
		
		MazeSolver ms = new MazeSolver();
		
	}
	
	public MazeSolver(){
		maze = null;
		this.promptDimensions();
		while(maze == null){
		}

		JFrame window = new JFrame("Maze Solver 1.0");
		
		JPanel mainCont = new JPanel();
		JPanel buttonArea = new JPanel();
		
		mainCont.setLayout(new BorderLayout());
		
		JButton startLocation = new JButton("Set Start Location");
		JButton endLocation = new JButton("Set End Location");
		JButton rotate = new JButton("Rotate Robot");
		JButton clear = new JButton("Clear");
		JButton start = new JButton("Start");
		
		buttonArea.setLayout(new FlowLayout());
		
		buttonArea.add(startLocation);
		buttonArea.add(endLocation);
		buttonArea.add(rotate);
		buttonArea.add(clear);
		buttonArea.add(start);
		
		maze.setSize(maze.getWidth(), maze.getHeight());
		
		maze.drawMaze();

		mainCont.add(buttonArea, BorderLayout.NORTH);
		mainCont.add(maze, BorderLayout.CENTER);
		
		window.add(mainCont);

		window.setSize(WIDTH, HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);

		clear.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				maze.clearMaze();
			}
			
		});

		startLocation.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				maze.setStart();
			}
			
		});

		endLocation.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				maze.setEnd();
			}
			
		});
		
	}
	
	public void promptDimensions(){
		JFrame window = new JFrame("Maze Solver 1.0");
		
		final int FIELD_WIDTH = 5;
		
		JLabel widthLabel = new JLabel("Width:");
		JLabel heightLabel = new JLabel("Height:");
		
		JTextField width = new JTextField(FIELD_WIDTH);
		JTextField height = new JTextField(FIELD_WIDTH);
		
		JButton enter = new JButton("Go!");
		
		enter.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int cols = Integer.parseInt(width.getText());
				int rows = Integer.parseInt(height.getText());
				maze = new Maze(cols, rows);
				
				window.setVisible(false);
				window.dispose();
			}
			
		});
		
		window.add(widthLabel);
		window.add(width);
		window.add(heightLabel);
		window.add(height);
		window.add(enter);
		
		window.setLayout(new FlowLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}
	
	public void solveMaze(){}
	
}
