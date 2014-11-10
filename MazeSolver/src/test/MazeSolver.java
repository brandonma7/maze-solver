package test;

import java.awt.*;
import java.util.Timer;

import javax.swing.*;

public class MazeSolver {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 1000;
	
	Maze maze;
	Robot rbt;
	Timer timer;
	
	public static void main(String[] args){
		
		JFrame window = new JFrame("Maze Solver 1.0");
		
		final int FIELD_WIDTH = 5;
		
		JTextField width = new JTextField(FIELD_WIDTH);
		JTextField height = new JTextField(FIELD_WIDTH);
		
		JButton enter = new JButton("Go!");
		
		window.setLayout(new FlowLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		
	}
	
	public void buildMaze(){}
	
	public void solveMaze(){}
	
}
