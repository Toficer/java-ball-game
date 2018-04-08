package BallGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

/**
 * Obiekt gry: gwiazdka, zebranie jej skutkuje zdobyciem dodatkowych punktow.
 * @author Rafal Raczynski
 */

public class Star extends GameObject{
	
	//Obraz reprezentujacy gwiazdke.
	Image star = Toolkit.getDefaultToolkit().getImage("star.png");
	/**
	 * Metoda rysowania obiektu przyjmuje kontekst graficzny canvasu, na ktorym bedzie on rysowany.
	 */
	public void paintInCanvas(Graphics g, ImageObserver observer) {
		g.drawImage(star, drawingPosx, drawingPosy, d1, d2, observer);
	}

}
