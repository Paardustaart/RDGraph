package nl.paardustaart.visualisationprototype;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;


public class GraphObject implements Drawable {
	
	private int x, y;
	private double widthMultiplier, heightMultiplier;
	private String displayText;
	
	public GraphObject(int x, int y, String text) {
		this.x = x;
		this.y = y;
		
		widthMultiplier = 1.5;
		heightMultiplier = 4.0;
		
		displayText = text;
	}
	
	public void draw(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D rect = fm.getStringBounds(displayText, g);
		
		g.setColor(Color.BLACK);
		g.fillRect(x, y, (int)(rect.getWidth() * widthMultiplier), (int)(rect.getHeight() * heightMultiplier));
		g.setColor(Color.WHITE);
		g.drawString(displayText, x + (int)(rect.getWidth() * widthMultiplier / 2 - (rect.getWidth() / 2)), y + (int)(rect.getHeight() * heightMultiplier / 2 + (rect.getHeight() / 3)));
	}
	
}
