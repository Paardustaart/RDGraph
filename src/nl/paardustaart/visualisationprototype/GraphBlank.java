package nl.paardustaart.visualisationprototype;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;


public class GraphBlank implements Drawable {
	
	private int x, y;
	private int borderThickness;
	private double widthMultiplier, heightMultiplier;
	private String displayText;
	
	public GraphBlank(int x, int y, String text) {
		this.x = x;
		this.y = y;
		
		widthMultiplier = 2;
		heightMultiplier = 3.0;
		
		borderThickness = 2;
		
		displayText = text;
	}
	
	public void draw(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D rect = fm.getStringBounds(displayText, g);
		
		Stroke oldStroke = g.getStroke();
		g.setStroke(new BasicStroke(borderThickness));
		
		g.setColor(Color.BLACK);
		g.drawOval(x, y, (int)(rect.getWidth() * widthMultiplier), (int)(rect.getHeight() * heightMultiplier));
		g.setColor(Color.black);
		g.drawString(displayText, x + (int)(rect.getWidth() * widthMultiplier / 2 - (rect.getWidth() / 2)), y + (int)(rect.getHeight() * heightMultiplier / 2 + (rect.getHeight() / 3)));
		
		g.setStroke(oldStroke);
	}
	
}
