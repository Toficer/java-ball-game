package BallGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * Okno pomocy, WIP. Docelowo zawierac bedzie zasady gry i opisy obiektow.
 * @author Rafal Raczynski
 */
public class HelpWindow extends Frame{
	
	JButton okButton;

	HelpWindow(String title){
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
			}
			
		});
		
		okButton = new JButton("OK");
		
		JPanel helpPanel = new JPanel(new BorderLayout());
		helpPanel.setBackground(Color.lightGray);
		helpPanel.add(new Label("Radz sobie."), BorderLayout.NORTH);
		JPanel footerPanel = new JPanel(new BorderLayout());
		footerPanel.setBackground(Color.lightGray);
		footerPanel.add(okButton, BorderLayout.EAST);
		helpPanel.add(footerPanel, BorderLayout.SOUTH);
		
		add(helpPanel);
		pack();
	}
	
	public Dimension getPreferredSize() {
		int h = 100;
		int w = 250;
		return new Dimension(w,h);
	}

}
