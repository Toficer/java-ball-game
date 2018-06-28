package BallGame;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * Okno wyboru pseudonimu gracza. Zawiera pole tekstowe umozliwiajace wprowadzenie tekstu oraz przyciski nawigacji.
 * @author Rafal Raczynski
 */
public class NameWindow extends JFrame{

	JTextField nameField;
	JButton startButton;
	JButton cancelButton;
	JLabel infoLabel;
	
	NameWindow(String title){
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
			}
			
		});

		nameField = new JTextField();
		startButton = new JButton("START GRY");
		cancelButton = new JButton("ANULUJ");
		infoLabel = new JLabel("Podaj swoj nick");
		
		JPanel mainPanel = new JPanel(new GridLayout(0,1));
		mainPanel.setBackground(Color.lightGray);
		mainPanel.add(infoLabel);
		mainPanel.add(nameField);
		
		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.add(startButton, BorderLayout.EAST);
		buttonPanel.add(cancelButton, BorderLayout.WEST);
		mainPanel.add(buttonPanel);
		add(mainPanel);
		pack();
	}
	
	public Dimension getPreferredSize() {
		int h = 150;
		int w = 300;
		return new Dimension(w,h);
	}

}
