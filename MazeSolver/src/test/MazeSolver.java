package test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.*;

public class MazeSolver {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 1000;
	
	private Maze maze;
	private Robot rbt;
	private Timer timer;
	
	public static void main(String[] args){
		
		MazeSolver ms = new MazeSolver();
		
	}
	
	public MazeSolver(){
		maze = null;
		this.promptDimensions();
		while(maze == null);
		
		rbt = new RightHandRobot(0, 0, Room.EAST);
		timer = new Timer();

		JFrame window = new JFrame("Maze Solver 1.0");
		
		JPanel mainCont = new JPanel();
		JPanel gridArea = new JPanel();
		JPanel buttonArea = new JPanel();
		
		window.setSize(WIDTH, HEIGHT);
		mainCont.setLayout(new BoxLayout(mainCont, BoxLayout.PAGE_AXIS));
		
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
		
		mainCont.add(gridArea);
		mainCont.add(buttonArea);
		
		window.add(mainCont);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
		
		System.out.println("end of contructor");
		
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
