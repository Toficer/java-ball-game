package BallGame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * Okno pomocy.
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
		JPanel textPanel = new JPanel((new GridLayout(0,1)));
		textPanel.setBackground(Color.lightGray);
		textPanel.add(new Label("Omijaj sciany, zbieraj gwiazdki i dotrzyj do portalu!"));
		textPanel.add(new Label("Zderzenia ze scianami skutkuja utrata zycia,"));
		textPanel.add(new Label("utrata wszystkich zyc konczy gre."), BorderLayout.NORTH);
		textPanel.add(new Label("Przyspieszacze przyspieszaja, kule grawitacyjne zmieniaja grawitacje."));
		helpPanel.add(textPanel, BorderLayout.NORTH);
		JPanel footerPanel = new JPanel(new BorderLayout());
		footerPanel.setBackground(Color.lightGray);
		footerPanel.add(okButton, BorderLayout.EAST);
		helpPanel.add(footerPanel, BorderLayout.SOUTH);
		
		add(helpPanel);
		pack();
	}
	
	public Dimension getPreferredSize() {
		int h = 150;
		int w = 450;
		return new Dimension(w,h);
	}

}
