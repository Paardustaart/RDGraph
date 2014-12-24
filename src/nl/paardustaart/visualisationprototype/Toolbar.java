package nl.paardustaart.visualisationprototype;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class Toolbar extends JToolBar {

	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
	
	private JButton previous, next;
	
	public Toolbar() {
		textField = new JTextField(10);
		previous = new JButton("<<");
		next = new JButton(">>");
		
		textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
		
		previous.setBackground(Color.WHITE);
		next.setBackground(Color.WHITE);
		
		this.add(textField);
		this.addSeparator(new Dimension(3, 10));
		this.add(previous);
		this.add(next);
		
		this.setBorderPainted(false);
		this.setBackground(Color.WHITE);
		
		this.setFloatable(false);
		this.setRollover(true);
		
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(textField.getText());
			}
		});
		
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Previous");
			}
		});
		
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Next");
			}
		});
		
		
	}
}
