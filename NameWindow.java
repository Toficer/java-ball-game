package BallGame;

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
public class NameWindow extends Frame{
	
	NameWindow(String title){
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
			}
			
		});
		
		Panel mainPanel = new Panel(new GridLayout(0,1));
		mainPanel.setBackground(Color.lightGray);
		mainPanel.add(new Label("Podaj swoj nick:"));
		mainPanel.add(new TextField());
		
		Panel buttonPanel = new Panel(new BorderLayout());
		buttonPanel.add(new Button("START GRY"), BorderLayout.EAST);
		buttonPanel.add(new Button("ANULUJ"), BorderLayout.WEST);
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
