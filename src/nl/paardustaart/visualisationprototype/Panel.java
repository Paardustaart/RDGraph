package nl.paardustaart.visualisationprototype;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Panel extends JPanel {
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Drawable> shapes;
	
	public Panel() {
		shapes = new ArrayList<Drawable>();
		
		GraphSubject subject = new GraphSubject(WIDTH, HEIGHT, "The Subject");
		
		List<String[]> predObj = new ArrayList<String[]>();
		String[] arr = {"Predicate", "Object"};
		for(int i = 0; i <= 7; i++) {
			predObj.add(arr);
		}
		
		subject.addGraphObjects(predObj);
		
		shapes.add(subject);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1280, 720);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, WIDTH + (WIDTH/100*2), HEIGHT + (HEIGHT/100*2));
		
		for(Drawable d : shapes) {
			d.draw(g2d);
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Drawing test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new Toolbar(), BorderLayout.NORTH);
		frame.getContentPane().add(new Panel(), BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
