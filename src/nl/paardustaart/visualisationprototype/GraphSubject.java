package nl.paardustaart.visualisationprototype;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;


public class GraphSubject implements Drawable {
	
	private int x, y;
	private int width, height;
	private double widthMultiplier, heightMultiplier;
	private String displayText;
	
	private ArrayList<GraphObject> objects;
	
	public GraphSubject(int x, int y, String text) {
		this.x = x;
		this.y = y;
		
		widthMultiplier = 1.5;
		heightMultiplier = 4.0;
		
		displayText = text;
		
		width = (int)(FontCalculator.getInstance().getWidth(displayText) * widthMultiplier);
		height = (int)(FontCalculator.getInstance().getHeight(displayText) * heightMultiplier);
		
		objects = new ArrayList<GraphObject>();
	}
	
	public void addGraphObject(int xDistance, int yDistance, String predicateText, String objectText) {
		GraphObject currentObject = new GraphObject(x + xDistance, y + yDistance, predicateText, objectText, this);
		objects.add(currentObject);
	}
	
	public Point getCenter() {
		return new Point(x + width / 2, y + height / 2);
	}
	
	public void draw(Graphics2D g) {
		
		for(GraphObject object : objects) {
			object.draw(g);
		}
		
		g.setColor(Color.BLACK);
		g.fillOval(x, y, width, height);
		g.setColor(Color.WHITE);
		g.drawString(displayText, x + (int)(width / 2 - ((FontCalculator.getInstance().getWidth(displayText) / 2))), y + (int)(height / 2 + (FontCalculator.getInstance().getHeight(displayText) / 2)));
		
	}

}
