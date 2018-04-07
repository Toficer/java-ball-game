package BallGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class GameCanvas extends Canvas{
    Image portal = Toolkit.getDefaultToolkit().getImage("portal.png");
    Image brick = Toolkit.getDefaultToolkit().getImage("brick.png");
    
	Object[][] tileObjects;
	PlayerBall ball = new PlayerBall();
	Color airColor = Color.lightGray;
	
	public Dimension getPreferredSize() {
		int h=900;
		int w = 3*h/4;
		return new Dimension(w,h);
	}
	
	public void paint(Graphics g) {
			
		Dimension size2 = getSize();
		int d1 = size2.width/15;
		int d2 = size2.height/20;
		int xoffset = (size2.width%15)/2;
		int yoffset = (size2.height%20)/2;
		for (int i=0; i<15; i++) {
			for (int j=0; j<20; j++) {
				if(tileObjects[j][i] instanceof Wall) {
					((Wall) tileObjects[j][i]).setDimensions((int)(d1), (int)(d2));
					((Wall) tileObjects[j][i]).setDrawingPos(size2.width, size2.height, xoffset, yoffset);
					//((Wall) tileObjects[j][i]).paintInCanvas(g, i*d1+xoffset, j*d2+yoffset, d1, d2, this); old version
					((Wall) tileObjects[j][i]).paintInCanvas(g, this);
				}
				else if(tileObjects[j][i] instanceof Air) {
					((Air) tileObjects[j][i]).setDimensions((int)(d1), (int)(d2));
					((Air) tileObjects[j][i]).setDrawingPos(size2.width, size2.height, xoffset, yoffset);
					//((Air) tileObjects[j][i]).paint(g, i*d1+xoffset, j*d2+yoffset, d1, d2, airColor); old version
					((Air) tileObjects[j][i]).paintInCanvas(g, airColor);
				}
				else if(tileObjects[j][i] instanceof Portal) {
					//((Portal) tileObjects[j][i]).paint(g, i*d1+xoffset, j*d2+yoffset, d1, d2, this); old version
					((Portal) tileObjects[j][i]).setDimensions((int)(d1), (int)(d2));
					((Portal) tileObjects[j][i]).setDrawingPos(size2.width, size2.height, xoffset, yoffset);
					((Portal) tileObjects[j][i]).paintInCanvas(g, this);
				}
				else if(tileObjects[j][i] instanceof Star) {
					((Star) tileObjects[j][i]).setDimensions((int)(d1), (int)(d2));
					((Star) tileObjects[j][i]).setDrawingPos(size2.width, size2.height, xoffset, yoffset);
					((Star) tileObjects[j][i]).paintInCanvas(g, this);
				}
				else if(tileObjects[j][i] instanceof DirectionalBooster) {
					((DirectionalBooster) tileObjects[j][i]).setDimensions((int)(d1), (int)(d2));
					((DirectionalBooster) tileObjects[j][i]).setDrawingPos(size2.width, size2.height, xoffset, yoffset);
					((DirectionalBooster) tileObjects[j][i]).paintInCanvas(g, this);
				}
			}
		}
		ball.setDimensions((int)(d1), (int)(d2));
		ball.setDrawingPos(size2.width, size2.height, xoffset, yoffset);
		ball.paintInCanvas(g);
	}

}
