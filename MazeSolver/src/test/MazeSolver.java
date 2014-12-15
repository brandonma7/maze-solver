package test;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Class to facilitate maze creation and maze solving. Also holds all the
 * GUI information for the various windows and action listeners for buttons.
 * @author Brandon Adams, Kaya Ota, Guillermo Collin
 *
 */

public class MazeSolver {

	public static final int WIDTH = 600;
	public static final int HEIGHT = 750;
	public static final String TITLE = "Maze Solver 1.0";
	
	private Maze maze;
	
	public static void main(String[] args){
		
		MazeSolver ms = new MazeSolver();
		
	}
	
	/**
	 * Instantiate Maze Solver and all necessary objects, button action
	 * listeners, prompt for dimensions, and other such initialization tasks.
	 */
	
	public MazeSolver(){
		maze = null;
		this.promptDimensions();
		
		//Stall until we have dimensions and thus have instantiated maze.
		while(maze == null){
				System.out.print("flag");
		}

		JFrame window = new JFrame(TITLE);
		
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
		
		rotate.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				maze.rotateRobot();
			}
			
		});
		
		start.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				maze.traverseMaze();
			}
			
		});
		
	}
	/**
	 * Creates window at beginning of run to prompt user for the 
	 * dimensions of the maze they would like to create.
	 */
	
	public void promptDimensions(){
		JFrame window = new JFrame(TITLE);
		
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
	
	/**
	 * Creates pop up box for traversal completion, whether the traversal was 
	 * successful or not.
	 * @param count Number of rooms robot visited
	 * @param success Whether traversal was successful or not.
	 * @param maze Instance of maze for action listener buttons.
	 */
	
	public static void finishPrompt(int count, boolean success, Maze maze){
		JFrame window = new JFrame(TITLE);
		JLabel statement;
		if(success){
			statement = new JLabel("Success! Took " + count + " room visits.");
		} else {
			statement = new JLabel("Your maze is unsolvable with the current algorithm!");
		}
		JButton restart = new JButton("Restart");
		JButton cont = new JButton("Continue");
		
		restart.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				maze.clearMaze();
				window.setVisible(false);
				window.dispose();
			}
			
		});
		
		cont.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				window.setVisible(false);
				window.dispose();
			}
			
		});
		
		window.add(statement);
		window.add(restart);
		window.add(cont);
		
		window.setLayout(new FlowLayout());
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}
	
}