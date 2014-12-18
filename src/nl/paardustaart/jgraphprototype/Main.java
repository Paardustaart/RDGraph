package nl.paardustaart.jgraphprototype;

import javax.swing.JFrame;

public class Main {
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("JGraphX Prototype");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.add(new GraphPanel(WIDTH, HEIGHT));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
}
