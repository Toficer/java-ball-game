package BallGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class Portal extends GameObject{
	
	Image portal = Toolkit.getDefaultToolkit().getImage("portal2.png");
	
	public void paintInCanvas(Graphics g, ImageObserver observer) {
		g.drawImage(portal, drawingPosx, drawingPosy, d1, d2, observer);
	}

}
