package BallGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

/**
 * Obszar rysowania gry. Zawiera liste obiektow oraz wylicza rozmiar pola, co przeklada sie na rozmiar tych obiektow na ekranie.
 * @author Rafal Raczynski
 */

public class GameCanvas extends JPanel{
    Image pauseImage = Toolkit.getDefaultToolkit().getImage("pause.png");
    
	GameObject[][] tileObjects;
	PlayerBall ball = new PlayerBall();
	Color airColor = Color.lightGray;
	boolean pause = false;
	
	public Dimension getPreferredSize() {
		int h=1000;
		int w = 3*h/4;
		return new Dimension(w,h);
	}
	
	public void paint(Graphics g) {
		
		//Metoda wylicza rozmiar komorki prostokatnej siatki obiektow na ekranie.
		Dimension size = getSize();
		int d1 = size.width/30;
		int d2 = size.height/40;
		
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, size.width, size.height);
		
		for (int i=0; i<30; i++) {
			for (int j=0; j<40; j++) {
				
				tileObjects[j][i].setDimensions((int)(d1), (int)(d2));
				tileObjects[j][i].setDrawingPos(size.width, size.height, 0, 0);
				
				if(tileObjects[j][i] instanceof Wall) {
					((Wall) tileObjects[j][i]).paintInCanvas(g, this);
				}
				else if(tileObjects[j][i] instanceof Air) {
					//((Air) tileObjects[j][i]).paintInCanvas(g, airColor);
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
		ball.setDrawingPos(size.width, size.height, 0, 0);
		ball.paintInCanvas(g);
		
		if(pause) {
			g.setColor(new Color(255,255,255,170));
			g.fillRect(0, 0, size.width, size.height);
			g.setColor(Color.black);
			g.fillRect(size.width/2-(5*d1), size.height/2-(3*d2), 10*d1, 6*d2);
			g.drawImage(pauseImage, size.width/2-(5*d1), size.height/2-(3*d2), 10*d1, 6*d2, this);
		}
	}
	
	public void togglePause() {
		pause = !pause;
	}

}
