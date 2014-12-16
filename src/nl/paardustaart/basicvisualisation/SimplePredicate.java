package nl.paardustaart.basicvisualisation;

import java.awt.Graphics2D;

public class SimplePredicate implements Drawable {
	
	private int x1, y1, x2, y2;
	
	public SimplePredicate(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public void draw(Graphics2D g) {
		g.drawLine(x1, y1, x2, y2);
	}

}
