package BallGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Obszar rysowania gry. Zawiera liste obiektow oraz wylicza rozmiar pola, co przeklada sie na rozmiar tych obiektow na ekranie.
 * @author Rafal Raczynski
 */

public class GameCanvas extends Canvas{
    Image portal = Toolkit.getDefaultToolkit().getImage("portal.png");
    Image brick = Toolkit.getDefaultToolkit().getImage("brick.png");
    
	GameObject[][] tileObjects;
	PlayerBall ball = new PlayerBall();
	Color airColor = Color.lightGray;
	
	public Dimension getPreferredSize() {
		int h=900;
		int w = 3*h/4;
		return new Dimension(w,h);
	}
	
	public void paint(Graphics g) {
		
		//Metoda wylicza rozmiar komorki prostokatnej siatki obiektow na ekranie.
		Dimension size2 = getSize();
		int d1 = size2.width/15;
		int d2 = size2.height/20;
		
		//Offset obecnie nieuzywany, ale we wczesniejszej wersji rysowania pozwalal na centrowanie planszy.
		//Prawdopodobie zostanie usuniety.
		//int xoffset = (size2.width%15)/2;
		//int yoffset = (size2.height%20)/2;
		
		for (int i=0; i<15; i++) {
			for (int j=0; j<20; j++) {
				
				tileObjects[j][i].setDimensions((int)(d1), (int)(d2));
				tileObjects[j][i].setDrawingPos(size2.width, size2.height, 0, 0);
				
				if(tileObjects[j][i] instanceof Wall) {
					((Wall) tileObjects[j][i]).paintInCanvas(g, this);
				}
				else if(tileObjects[j][i] instanceof Air) {
					((Air) tileObjects[j][i]).paintInCanvas(g, airColor);
				}
				else if(tileObjects[j][i] instanceof Portal) {
					((Portal) tileObjects[j][i]).paintInCanvas(g, this);
				}
				else if(tileObjects[j][i] instanceof Star) {
					((Star) tileObjects[j][i]).paintInCanvas(g, this);
				}
				else if(tileObjects[j][i] instanceof GravityOrb) {
					((GravityOrb) tileObjects[j][i]).paintInCanvas(g, this);
				}
				else if(tileObjects[j][i] instanceof DirectionalBooster) {
					((DirectionalBooster) tileObjects[j][i]).paintInCanvas(g, this);
				}
			}
		}
		//Kulka otrzymuje rozmiary i pozycje oraz jest rysowana.
		ball.setDimensions((int)(d1/1.5), (int)(d2/1.5));
		ball.setDrawingPos(size2.width, size2.height, 0, 0);
		ball.paintInCanvas(g);
	}

}
