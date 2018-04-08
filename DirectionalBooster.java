package BallGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

/**
 * Obiekt gry: booster, przyspiesza kulke w kierunku strzalki.
 * @author Rafal Raczynski
 */

public class DirectionalBooster extends GameObject{
	/**
	 * Konstruktor pozwala wybrac kierunek przyspieszacza.
	 */
	DirectionalBooster(ArrowDirection direction){
		this.direction = direction;
	}
	
	public enum ArrowDirection{
		UP, DOWN, LEFT, RIGHT
	}
	
	ArrowDirection direction = ArrowDirection.UP;

	Image arrowup = Toolkit.getDefaultToolkit().getImage("arrowup.png");
	Image arrowdown = Toolkit.getDefaultToolkit().getImage("arrowdown.png");
	Image arrowleft = Toolkit.getDefaultToolkit().getImage("arrowleft.png");
	Image arrowright = Toolkit.getDefaultToolkit().getImage("arrowright.png");
	/**
	 * Metoda rysowania obiektu przyjmuje kontekst graficzny canvasu, na ktorym bedzie on rysowany.
	 * W zaleznosci od kierunku przyspieszacza, wybierany jest odpowiedni obrazek go reprezentujacy.
	 */
	public void paintInCanvas(Graphics g, ImageObserver observer) {
		
		switch(direction) {
		case UP: g.drawImage(arrowup, drawingPosx, drawingPosy, d1, d2, observer);
			break;
		case DOWN: g.drawImage(arrowdown, drawingPosx, drawingPosy, d1, d2, observer);
			break;
		case LEFT: g.drawImage(arrowleft, drawingPosx, drawingPosy, d1, d2, observer);
			break;
		case RIGHT: g.drawImage(arrowright, drawingPosx, drawingPosy, d1, d2, observer);
			break;
		default: g.drawImage(arrowup, drawingPosx, drawingPosy, d1, d2, observer);
			break;
		
		}
	}
	
}
