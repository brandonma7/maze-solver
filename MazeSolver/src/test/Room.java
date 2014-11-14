package test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.swing.Icon;
import javax.swing.JLabel;

public class Room implements Icon {

	public static int NORTH = 0;
	public static int EAST = 1;
	public static int SOUTH = 2;
	public static int WEST = 3;
	
	private boolean[] walls;
	private int width;
	
	public Room(int width){
		
		this.walls = new boolean[4];
		walls[0] = false;
		walls[1] = false;
		walls[2] = false;
		walls[3] = false;
		
		this.width = width;
		
	}
	
	public void toggleWall(int direction){
		walls[direction] = !walls[direction];
	}
	
	public boolean isOpen(int direction){
		return walls[direction];
	}
	
	public void draw(Graphics2D g2, int x, int y){
		
		g2.setColor(Color.BLACK);
		
		Point2D.Double r1,r2,r3,r4;

		r1 = new Point2D.Double(x, y);
		r2 = new Point2D.Double(x + width - 1, y);
		r3 = new Point2D.Double(x + width - 1, y + width - 1);
		r4 = new Point2D.Double(x, y + width - 1);
		
		System.out.println("1 " + r1.getX() + "," + r1.getY());
		System.out.println("2 " + r2.getX() + "," + r2.getY());
		System.out.println("3 " + r3.getX() + "," + r3.getY());
		System.out.println("4 " + r4.getX() + "," + r4.getY());
		
		Line2D.Double top, right, bottom, left;
		top = new Line2D.Double(r1, r2);
		right = new Line2D.Double(r2, r3);
		bottom = new Line2D.Double(r3, r4);
		left = new Line2D.Double(r4, r1);
		
		g2.draw(top);
		g2.draw(right);
		g2.draw(bottom);
		g2.draw(left);
		
		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(2));
		//g2.drawRect(x, y, width, width);
	}

	@Override
	public int getIconHeight() {
		return width;
	}

	@Override
	public int getIconWidth() {
		return width;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		this.draw(g2, x, y);
	}
}