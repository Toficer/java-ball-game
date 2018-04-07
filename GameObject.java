package BallGame;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

/**
 * Obiekt gry: podstawowy obiekt gry.
 * @author Rafal Raczynski
 */

public class GameObject extends Component{
	
	int posx = 10, posy = 10;
	int drawingPosx=10, drawingPosy=10;
	int d1 = 10, d2 = 10;
	
	public void setDrawingPos(int multx, int multy, int xoffset, int yoffset) {
		this.drawingPosx = (int)(((float)posx/750)*multx + xoffset);
		this.drawingPosy = (int)(((float)posy/1000)*multy + yoffset);
	}
	
	public void setPos(int posx, int posy) {
		this.posx = posx;
		this.posy = posy;
	}
	
	public void setDimensions(int d1, int d2) {
		this.d1 = d1;
		this.d2 = d2;
	}
}
