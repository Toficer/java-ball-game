package BallGame;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Obiekt gry: kulka, to nia sterujemy.
 * @author Rafal Raczynski
 */

public class PlayerBall extends GameObject{
	
	Color customColor = new Color(127, 159, 255);
	
	double vVelocity = 0;
	double hVelocity = 0;
	int originalposx, originalposy;
	double dPosx, dPosy;
	
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
	/**
	 * Pozwala przeliczyc zmiennoprzecinkowe wspolrzedne na calkowite, pikselowe, do rysowania.
	 */
	public void calculateIntPos(){
		posx = (int)dPosx;
		posy = (int)dPosy;
	}
	/**
	 * Pozwala ustawic pozycje kulki w notacji zmiennoprzecinkowej.
	 * @param dposx pozycja x
	 * @param dposy pozycja y
	 */
	public void setDoublePos(double dposx, double dposy){
		this.dPosx = dposx;
		this.dPosy = dposy;
		calculateIntPos();
	}

}