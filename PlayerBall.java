package BallGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

/**
 * Obiekt gry: kulka, to nia sterujemy.
 * @author Rafal Raczynski
 */

public class PlayerBall extends GameObject{
	
	Color customColor = new Color(127, 159, 255);
	
	int vVelocity = 0;
	int hVelocity = 0;
	int originalposx, originalposy;
	
	public void paintInCanvas(Graphics g) {
		g.setColor(customColor);
		g.fillOval(drawingPosx, drawingPosy, d1, d2);
	}
	
	public int returnPosx() {
		return this.posx;
	}

	public int returnPosy() {
		return this.posy;
	}
	
	public void setOriginalPos(int x, int y) {
		this.originalposx = x;
		this.originalposy = y;
	}

}