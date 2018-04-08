package BallGame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * Okno pomocy, WIP. Docelowo zawierac bedzie zasady gry i opisy obiektow.
 * @author Rafal Raczynski
 */
public class HelpWindow extends Frame{
	
	HelpCanvas help;
	
	HelpWindow(String title){
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
			}
			
		});
		
		help = new HelpCanvas();
		Panel helpPanel = new Panel(new BorderLayout());
		helpPanel.add(help, BorderLayout.CENTER);
		Panel footerPanel = new Panel(new BorderLayout());
		footerPanel.setBackground(Color.lightGray);
		footerPanel.add(new Button("OK"), BorderLayout.EAST);
		helpPanel.add(footerPanel, BorderLayout.SOUTH);
		
		add(helpPanel);
		pack();
	}
	
	public Dimension getPreferredSize() {
		int h = 470;
		int w = 700;
		return new Dimension(w,h);
	}

}
