package BallGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

/**
 * Obiekt gry: portal, do niego chcemy dojsc.
 * @author Rafal Raczynski
 */

public class Portal extends GameObject{
	
	Image portal = Toolkit.getDefaultToolkit().getImage("portal2.png");
	/**
	 * Metoda rysowania obiektu przyjmuje kontekst graficzny canvasu, na ktorym bedzie on rysowany.
	 */
	public void paintInCanvas(Graphics g, ImageObserver observer) {
		g.drawImage(portal, drawingPosx, drawingPosy, d1, d2, observer);
	}
	
	public boolean checkCollision(int ballx, int bally) {
		int distance = (int)Math.sqrt( ((ballx + 8)-(posx+12))*((ballx + 8)-(posx+12)) + ((bally + 8)-(posy+12))*((bally + 8)-(posy+12)) );
		return (distance <= 20);
	}

}
