package nl.paardustaart.visualisationprototype;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Panel extends JPanel {
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Drawable> shapes;
	
	public Panel() {
		shapes = new ArrayList<Drawable>();
		shapes.add(new GraphSubject(100, 100, "Supersmooth Subject"));
		shapes.add(new GraphObject(300, 200, "Overpowered Object"));
		shapes.add(new GraphBlank(100, 250, "BLANK"));
		shapes.add(new GraphPredicate(200, 300, 550, 350, "lel"));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		for(Drawable d : shapes) {
			d.draw((Graphics2D)g);
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Drawing test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setResizable(false);
		frame.add(new Panel());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
