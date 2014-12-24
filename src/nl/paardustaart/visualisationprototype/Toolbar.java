package nl.paardustaart.visualisationprototype;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		this.add(textField);
		this.add(previous);
		this.add(next);
		
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
