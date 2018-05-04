package BallGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
					System.out.println("up");
		        }

				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					isAcceleratingHor = true;
					isSlowingHor = false;
					horDirection = false;
					System.out.println("r");
		        }
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					isAcceleratingHor = true;
					isSlowingHor = false;
					horDirection = true;
					System.out.println("l");
		        }
			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					isAcceleratingVert = false;
					isSlowingVert = true;
					System.out.println("up rel");
		        }

				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					isAcceleratingHor = false;
					isSlowingHor = true;
					System.out.println("l rel");
		        }
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					isAcceleratingHor = false;
					isSlowingHor = true;
					System.out.println("r rel");
		        }
			}

			public void keyTyped(KeyEvent e) {
			}
			
		});
		
		ActionListener al1 = new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        game.window.setVisible(true);
		        game.mwindow.dispose();
		        game.window.gameCanvas.ball.setPos(game.window.gameCanvas.ball.originalposx, game.window.gameCanvas.ball.originalposy);
		       	game.unpause();
		    }
		};
		BallGame.mwindow.startButton.addActionListener(al1);
		
		ActionListener al2 = new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        if(game.isPaused) {
		        	game.unpause();
		        }
		        else {
		        	game.pause();
		        }
		        game.window.gameCanvas.togglePause();
	        	game.window.gameCanvas.repaint();
		    }
		};
		game.window.pauseButton.addActionListener(al2);
		
		ActionListener al3 = new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        game.window.dispose();
		        System.exit(0);
		    }
		};
		game.window.exitButton.addActionListener(al3);
		
		ActionListener al4 = new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        game.swindow.setVisible(true);
		    }
		};
		game.mwindow.scoreButton.addActionListener(al4);
		
		ActionListener al5 = new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	game.mwindow.dispose();
		    	game.window.dispose();
		        System.exit(0);
		    }
		};
		game.mwindow.exitButton.addActionListener(al5);
				
	}
	
	
}
