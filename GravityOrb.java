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
	
	GravityOrb(int change){
		this.change = change;
	}
	
	float change = 0;

	Image gravup = Toolkit.getDefaultToolkit().getImage("gravup.png");
	Image gravdown = Toolkit.getDefaultToolkit().getImage("gravdown.png");
	
	public void paintInCanvas(Graphics g, ImageObserver observer) {
		
		if(change>0) g.drawImage(gravup, drawingPosx, drawingPosy, d1, d2, observer);
		else g.drawImage(gravdown, drawingPosx, drawingPosy, d1, d2, observer);
		
	}
	
}
