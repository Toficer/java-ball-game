package BallGame;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
/**
 * Canvas obrazu pomocy, WIP. Obraz okazal sie byc nieczytelny przy nieodpowiednich rozdzielczosciach.
 * Klasa do przeprojektowania.
 * @author Rafal Raczynski
 */
public class HelpCanvas extends Canvas{

	Image help = Toolkit.getDefaultToolkit().getImage("help.png");
	
	public Dimension getPreferredSize() {
		int h = 450;
		int w = 700;
		return new Dimension(w,h);
	}
	
	public void paint(Graphics g) {
		Dimension size = getSize();
		g.drawImage(help, 0, 0, size.width, size.height, this);
	}
	
}
