package nl.paardustaart.visualisationprototype;

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
		
		GraphSubject subject = new GraphSubject(WIDTH / 3, HEIGHT / 3, "Smooth criminal");
		subject.addGraphObject(-200, 200, "Predicate", "Object");
		subject.addGraphObject(0, 200, "Predicate", "Object");
		subject.addGraphObject(200, 200, "Predicate", "Object");
		subject.addGraphObject(400, 200, "Predicate", "Object");
		
		shapes.add(subject);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
		for(Drawable d : shapes) {
			d.draw(g2d);
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
