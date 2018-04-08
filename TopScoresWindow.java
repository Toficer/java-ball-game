package BallGame;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * Okno rankingu. Zawiera 5 najlepszych wynikow gier z przeszlosci. TODO: metoda pozwalajaca edytowac wyniki.
 * @author Rafal Raczynski
 */
public class TopScoresWindow extends Frame{
	
	TopScoresWindow(String title){
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
			}
			
		});
		
		Panel mainPanel = new Panel(new GridLayout(0,1));
		mainPanel.setBackground(Color.lightGray);
		mainPanel.add(new Label("1. test1: 11112222"));
		mainPanel.add(new Label("2. test2: 1111222"));
		mainPanel.add(new Label("3. test3: 111122"));
		mainPanel.add(new Label("4. test4: 11112"));
		mainPanel.add(new Label("5. test5: 1111"));
		
		Panel footerPanel = new Panel(new GridLayout(1,0));
		footerPanel.add(new Button("OK"));
		mainPanel.add(footerPanel);
		
		add(mainPanel);
		pack();
	}
	
	public Dimension getPreferredSize() {
		int h = 250;
		int w = 300;
		return new Dimension(w,h);
	}

}
