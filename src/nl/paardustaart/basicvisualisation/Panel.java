package nl.paardustaart.basicvisualisation;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Panel extends JPanel {
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Drawable> shapes;
	
	public Panel() {
		shapes = new ArrayList<Drawable>();
		
		SimpleSubject subj = new SimpleSubject(WIDTH / 2 - 50, HEIGHT / 2 - 50);
		
		subj.addSimpleObject(200, 200, Location.EAST, Location.WEST);
		subj.addSimpleObject(0, 200, Location.SOUTH, Location.NORTH);
		subj.addSimpleObject(-200, 200, Location.WEST, Location.EAST);
		subj.addSimpleObject(-200, -200, Location.NORTH, Location.SOUTH);
		
		shapes.add(subj);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(Drawable shape : shapes) {
			shape.draw(g2d);
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Drawing test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.add(new Panel());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
