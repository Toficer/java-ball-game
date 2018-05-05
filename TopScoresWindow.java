package BallGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Okno rankingu. Zawiera 5 najlepszych wynikow gier z przeszlosci. TODO: metoda pozwalajaca edytowac wyniki.
 * @author Rafal Raczynski
 */
public class TopScoresWindow extends Frame{
	
	JButton okButton;
	
	TopScoresWindow(String title){
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
			}
			
		});
		
		JPanel mainPanel = new JPanel(new GridLayout(0,1));
		mainPanel.setBackground(Color.lightGray);
		mainPanel.add(new JLabel("1. test1: 11112222"));
		mainPanel.add(new JLabel("2. test2: 1111222"));
		mainPanel.add(new JLabel("3. test3: 111122"));
		mainPanel.add(new JLabel("4. test4: 11112"));
		mainPanel.add(new JLabel("5. test5: 1111"));
		
		okButton = new JButton("OK");
		
		JPanel footerPanel = new JPanel(new GridLayout(1,0));
		footerPanel.add(okButton);
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
