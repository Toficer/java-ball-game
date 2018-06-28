package BallGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

/**
 * Obiekt gry: sciana, uderzenie w nia skutkuje utrata zycia.
 * @author Rafal Raczynski
 */

public class Wall extends GameObject{

	//Obraz reprezentujacy sciane.
	Image brick = Toolkit.getDefaultToolkit().getImage("brick3.png");
	/**
	 * Metoda rysowania obiektu przyjmuje kontekst graficzny canvasu, na ktorym bedzie on rysowany.
	 */
	public void paintInCanvas(Graphics g, ImageObserver observer) {
		g.drawImage(brick, drawingPosx, drawingPosy, d1, d2, observer);
	}
}
