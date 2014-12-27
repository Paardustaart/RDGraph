package nl.paardustaart.visualisationprototype;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class GraphSubject implements Drawable {
	
	private int x, y;
	private int width, height;
	private double widthMultiplier, heightMultiplier;
	private String displayText;
	
	private ArrayList<GraphObject> objects;
	
	public GraphSubject(int x, int y, String text) {
		
		widthMultiplier = 1.5;
		heightMultiplier = 4.0;
		
		displayText = text;
		
		width = (int)(FontCalculator.getInstance().getWidth(displayText) * widthMultiplier);
		height = (int)(FontCalculator.getInstance().getHeight(displayText) * heightMultiplier);
		
		objects = new ArrayList<GraphObject>();
		
		this.x = (x / 2) - (width / 2);
		this.y = (y / 2) - (height / 2);
	}
	
	public void addGraphObject(int xDistance, int yDistance, String predicateText, String objectText) {
		GraphObject currentObject = new GraphObject(getCenter().x + xDistance, getCenter().y + yDistance, predicateText, objectText, this);
		objects.add(currentObject);
	}
	
	public void addGraphObjects(List<String[]> objects) {
		
		int[][] location = {{0, -200}, {150, -150}, {200, 0}, {150, 150}, {0, 200}, {-150, 150}, {-200, 0}, {-150, -150}};
		
		int loc = 0;
		int multiplier = 8 / objects.size();
		
		for(int i = 0; i < objects.size(); i++) {
			int x = location[loc][0];
			int y = location[loc][1];
			this.addGraphObject(x, y, objects.get(i)[0], objects.get(i)[1]);
			loc += multiplier;
		}
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
