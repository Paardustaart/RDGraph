package nl.paardustaart.visualisationprototype;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;


public class GraphObject implements Drawable {
	
	private int x, y;
	private int width, height;
	private double widthMultiplier, heightMultiplier;
	private String predicateText, displayText;
	
	private GraphSubject subject;
	
	public GraphObject(int x, int y, String predicate, String display, GraphSubject subject) {
		this.x = x;
		this.y = y;
		
		this.subject = subject;
		
		widthMultiplier = 2;
		heightMultiplier = 2.5;
		
		predicateText = predicate;
		displayText = display;
		
		width = (int)(FontCalculator.getInstance().getWidth(displayText) * widthMultiplier);
		height = (int)(FontCalculator.getInstance().getHeight(displayText) * heightMultiplier);
		
	}
	
	public Point getCenter() {
		return new Point(x + width / 2, y + height / 2);
	}
	
	public Point getLineCenter() {
		Point p1 = subject.getCenter();
		Point p2 = this.getCenter();
		return new Point((p1.x + p2.x)/2, (p1.y + p2.y)/2);
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(Color.BLACK);
		g.drawLine(subject.getCenter().x, subject.getCenter().y, x + width / 2, y + height / 2);
		
		g.setColor(Color.WHITE);
		g.fillRect(getLineCenter().x - ((int)FontCalculator.getInstance().getWidth(predicateText) / 2), getLineCenter().y - (int)FontCalculator.getInstance().getHeight(predicateText) +3, (int)FontCalculator.getInstance().getWidth(predicateText), (int)FontCalculator.getInstance().getHeight(predicateText));
		
		g.setColor(Color.BLACK);
		g.drawString(predicateText, getLineCenter().x - ((int)FontCalculator.getInstance().getWidth(predicateText) / 2), getLineCenter().y);
		
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
		g.setColor(Color.WHITE);
		g.drawString(displayText, x + (int)(width / 2 - ((FontCalculator.getInstance().getWidth(displayText) / 2))), y + (int)(height / 2 + (FontCalculator.getInstance().getHeight(displayText) / 2)));
	}
	
}
