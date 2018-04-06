package BallGame;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class Wall extends Component{
	
	Image brick = Toolkit.getDefaultToolkit().getImage("brick.png");
	
	public void paint(Graphics g, int d1, int d2, int d3, int d4, ImageObserver observer) {
		g.drawImage(brick, d1, d2, d3, d4, observer);
	}
	
}
