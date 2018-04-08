package BallGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

/**
 * Obiekt gry: kula grawitacyjna, zmniejsza lub zwieksza grawitacje poziomu po zebraniu.
 * @author Rafal Raczynski
 */

public class GravityOrb extends GameObject{
	
	/**
	 * Konstruktor pozwala na wybranie poziomu zmiany grawitacji po zebraniu kuli.
	 */
	GravityOrb(int change){
		this.change = change;
	}
	
	float change = 0;

	Image gravup = Toolkit.getDefaultToolkit().getImage("gravup.png");
	Image gravdown = Toolkit.getDefaultToolkit().getImage("gravdown.png");
	/**
	 * Metoda rysowania obiektu przyjmuje kontekst graficzny canvasu, na ktorym bedzie on rysowany.
	 * Wybierany jest odpowiedni obrazek w zaleznosci od tego, jaka zmiane grawitacji wprowadza kula.
	 */
	public void paintInCanvas(Graphics g, ImageObserver observer) {
		
		if(change>0) g.drawImage(gravup, drawingPosx, drawingPosy, d1, d2, observer);
		else g.drawImage(gravdown, drawingPosx, drawingPosy, d1, d2, observer);
		
	}
	
}
