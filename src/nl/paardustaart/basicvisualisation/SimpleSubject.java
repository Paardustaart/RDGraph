package nl.paardustaart.basicvisualisation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class SimpleSubject implements Drawable {
	
	private int x, y, width, height;
	
	private ArrayList<Drawable> connections;
	
	public SimpleSubject(int x, int y) {
		this.x = x;
		this.y = y;
		
		this.width = 75;
		this.height = 40;
		
		connections = new ArrayList<Drawable>();
	}
	
	public void addSimpleObject(int objectX, int objectY, int origin, int destination) {
		SimpleObject currentSO = new SimpleObject((x + width / 2) + objectX, (y + height / 2) + objectY);
		
		Point originPoint = new Point();
		Point destinationPoint = new Point();
		
		switch(origin) {
		case Location.NORTH:
			originPoint.setLocation(x + (width / 2), y);
			break;
		case Location.EAST:
			originPoint.setLocation(x + width, y + (height / 2));
			break;
		case Location.SOUTH:
			originPoint.setLocation(x + (width / 2), y + height);
			break;
		case Location.WEST:
			originPoint.setLocation(x, y + (height / 2));
			break;
		default: 
			originPoint.setLocation(x + (width / 2), y);
			break;
		}
		
		switch(destination) {
		case Location.NORTH:
			destinationPoint.setLocation(currentSO.getX() + (currentSO.getWidth() / 2), currentSO.getY());
			break;
		case Location.EAST:
			destinationPoint.setLocation(currentSO.getX() + currentSO.getWidth(), currentSO.getY() + (currentSO.getHeight() / 2));
			break;
		case Location.SOUTH:
			destinationPoint.setLocation(currentSO.getX() + (currentSO.getWidth() / 2), currentSO.getY() + currentSO.getHeight());
			break;
		case Location.WEST:
			destinationPoint.setLocation(currentSO.getX(), currentSO.getY() + (currentSO.getHeight() / 2));
			break;
		default: 
			destinationPoint.setLocation(currentSO.getX() + (currentSO.getWidth() / 2), currentSO.getY());
			break;
		}
		
		SimplePredicate currentSP = new SimplePredicate((int)originPoint.getX(), (int)originPoint.getY(), (int)destinationPoint.getX(), (int)destinationPoint.getY());
		
		connections.add(currentSP);
		connections.add(currentSO);
		
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillOval(x, y, width, height);
		
		for(Drawable connection : connections) {
			connection.draw(g);
		}
	}
	
}
