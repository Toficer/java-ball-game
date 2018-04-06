package BallGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

public class PlayerBall extends Component{

	int posx = 10;
	int posy = 10;
	int d1 = 10;
	int d2 = 10;
	Color customColor = new Color(127, 159, 255);
	
	public void paint(Graphics g) {
		g.setColor(customColor);
		g.fillOval(posx, posy, d1, d2);
	}
	
	public void paintInCanvas(Graphics g) {
		g.setColor(customColor);
		g.fillOval(posx, posy, d1, d2);
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
