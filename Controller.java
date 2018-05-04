package BallGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements Runnable{
	
	boolean isAcceleratingVert = false;
	boolean isSlowingVert = true;
	boolean isAcceleratingHor = false;
	boolean isSlowingHor = false;
	boolean horDirection = false;
	BallGame game;
	
	Controller(BallGame game) {
		this.game = game;
	}

	public void run() {
		
		BallGame.window.gameCanvas.addKeyListener(new KeyListener() {
			
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					isAcceleratingVert = true;
					isSlowingVert = false;
		        }

				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					isAcceleratingHor = true;
					isSlowingHor = false;
					horDirection = false;
		        }
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					isAcceleratingHor = true;
					isSlowingHor = false;
					horDirection = true;
		        }
			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					isAcceleratingVert = false;
					isSlowingVert = true;
		        }

				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					isAcceleratingHor = false;
					isSlowingHor = true;
		        }
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					isAcceleratingHor = false;
					isSlowingHor = true;
		        }
			}

			public void keyTyped(KeyEvent e) {
			}
			
		});
				
	}
	
}
