package BallGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

public class Air extends Component{
	
	public void paint(Graphics g, int d1, int d2, int d3, int d4, Color test) {
		g.setColor(test);
		g.fillRect(d1, d2, d3, d4);
	}
	
}
