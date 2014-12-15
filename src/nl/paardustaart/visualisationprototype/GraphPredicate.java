package nl.paardustaart.visualisationprototype;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class GraphPredicate implements Drawable {
	
	private int x1, y1, x2, y2;
	
	private String displayText;
	
	public GraphPredicate(int x1, int y1, int x2, int y2, String text) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		
		displayText = text;
	}
	
	public void draw(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.BLACK);
		g.drawLine(x1, y1, x2, y2);
		g.drawString(displayText, (x1 + x2) / 2, (y1 + y2) / 2);
	}
	
}
