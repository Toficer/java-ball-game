package BallGame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Okno menu. Zawiera przyciski pozwalajace poruszac sie po aplikacji i rozpoczac gre.
 * @author Rafal Raczynski
 */
public class MenuWindow extends JFrame{
	
	JButton startButton, scoreButton, exitButton, helpButton;
	
	MenuWindow(String title, BallGame game){
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
			}
			
		});
		
		Panel mainPanel = new Panel(new GridLayout(0,1));
		
		startButton = new JButton("START GRY");
		scoreButton = new JButton("NAJLEPSZE WYNIKI");
		exitButton = new JButton("WYJSCIE");
		helpButton = new JButton("POMOC");
		
		mainPanel.add(startButton);
		mainPanel.add(scoreButton);
		mainPanel.add(helpButton);
		mainPanel.add(new JButton("SERWER (NIEDOSTEPNY)"));
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
