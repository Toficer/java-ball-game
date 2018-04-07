package BallGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class Star extends GameObject{
	
	Image star = Toolkit.getDefaultToolkit().getImage("star.png");
	public void paintInCanvas(Graphics g, ImageObserver observer) {
		g.drawImage(star, drawingPosx, drawingPosy, d1, d2, observer);
	}

}
