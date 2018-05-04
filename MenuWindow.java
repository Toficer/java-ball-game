package BallGame;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * Okno menu. Zawiera przyciski pozwalajace poruszac sie po aplikacji i rozpoczac gre.
 * @author Rafal Raczynski
 */
public class MenuWindow extends JFrame{
	
	Button startButton, scoreButton, exitButton;
	
	MenuWindow(String title, BallGame game){
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
			}
			
		});
		
		Panel mainPanel = new Panel(new GridLayout(0,1));
		
		startButton = new Button("START GRY");
		scoreButton = new Button("NAJLEPSZE WYNIKI");
		exitButton = new Button("WYJSCIE");
		
		mainPanel.add(startButton);
		mainPanel.add(scoreButton);
		mainPanel.add(new Button("POMOC"));
		mainPanel.add(new Button("SERWER"));
		mainPanel.add(exitButton);
		
		add(mainPanel);
		pack();
	}
	
	public Dimension getPreferredSize() {
		int h = 500;
		int w = 300;
		return new Dimension(w,h);
	}
	
}
