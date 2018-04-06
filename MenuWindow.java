package BallGame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuWindow extends Frame{
	
	MenuWindow(String title){
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
			}
			
		});
		
		Panel mainPanel = new Panel(new GridLayout(0,1));
		
		mainPanel.add(new Button("START GRY"));
		mainPanel.add(new Button("NAJLEPSZE WYNIKI"));
		mainPanel.add(new Button("POMOC"));
		mainPanel.add(new Button("SERWER"));
		mainPanel.add(new Button("WYJSCIE"));
		
		add(mainPanel);
		pack();
	}
	
	public Dimension getPreferredSize() {
		int h = 500;
		int w = 300;
		return new Dimension(w,h);
	}
	
}
