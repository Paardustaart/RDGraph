package nl.paardustaart.visualisationprototype;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;


public class FontCalculator {
	
	private static FontCalculator instance;
	
	private FontRenderContext context;
	private Font font;
	
	private FontCalculator() {
		context = new FontRenderContext(new AffineTransform(), true, true);
		font = new Font("Dialog", Font.PLAIN, 12);
	}
	
	public int getWidth(String text) {
		return (int)(font.getStringBounds(text, context).getWidth());
	}
	
	public int getHeight(String text) {
		return (int)(font.getStringBounds(text, context).getHeight());
	}
	
	public static FontCalculator getInstance() {
		if(instance == null) {
			instance = new FontCalculator();
		}
		return instance;
	}
	
}
