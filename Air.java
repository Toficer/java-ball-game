package BallGame;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Obiekt gry: powietrze, w nim mozemy sie poruszac.
 * @author Rafal Raczynski
 */

public class Air extends GameObject{

	public void paintInCanvas(Graphics g, Color aircolor) {
		g.setColor(aircolor);
		g.fillRect(drawingPosx, drawingPosy, d1, d2);
	}
	
}
