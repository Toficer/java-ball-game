package BallGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

public class PlayerBall extends Component{

	int posx = 10, posy = 10;
	int drawingPosx=10, drawingPosy=10;
	int d1 = 10, d2 = 10;
	Color customColor = new Color(127, 159, 255);
	
	public void paintInCanvas(Graphics g) {
		g.setColor(customColor);
		g.fillOval(drawingPosx, drawingPosy, d1, d2);
	}
	
	public void setPos(int posx, int posy) {
		this.posx = posx;
		this.posy = posy;
	}
	public void setDrawingPos(int multx, int multy, int xoffset, int yoffset) {
		this.drawingPosx = (int)(((float)posx/750)*multx + xoffset);
		this.drawingPosy = (int)(((float)posy/1000)*multy + yoffset);
	}
	public void setDimensions(int d1, int d2) {
		this.d1 = d1;
		this.d2 = d2;
	}
}
