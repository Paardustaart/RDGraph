package nl.paardustaart.visualisationprototype;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;


public class GraphBlank implements Drawable {
	
	private int x, y;
	private int borderThickness;
	private int width, height;
	private double widthMultiplier, heightMultiplier;
	private String displayText;
	
	public GraphBlank(int x, int y, String text) {
		this.x = x;
		this.y = y;
		
		widthMultiplier = 2;
		heightMultiplier = 3.0;
		
		borderThickness = 2;
		
		displayText = text;
		
		width = (int)(FontCalculator.getInstance().getWidth(displayText) * widthMultiplier);
		height = (int)(FontCalculator.getInstance().getHeight(displayText) * heightMultiplier);
	}
	
	public void draw(Graphics2D g) {
		
		Stroke oldStroke = g.getStroke();
		g.setStroke(new BasicStroke(borderThickness));
		
		g.setColor(Color.BLACK);
		g.drawOval(x, y, width, height);
		g.setColor(Color.black);
		g.drawString(displayText, x + (int)(width / 2 - ((FontCalculator.getInstance().getWidth(displayText) / 2))), y + (int)(height / 2 + (FontCalculator.getInstance().getHeight(displayText) / 2)));
		
		g.setStroke(oldStroke);
	}
	
}
