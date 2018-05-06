package BallGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Kontroler odpowiada za obsluge flag klawiatury i przyciskow.
 * @author Rafal Raczynski
 */
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
				if (e.getKeyCode() == KeyEvent.VK_P) {
					if(game.isPaused) {
			        	game.unpause();
			        }
			        else {
			        	game.pause();
			        }
			        game.window.gameCanvas.togglePause();
		        	game.window.gameCanvas.repaint();
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
		
		ActionListener al1 = new ActionListener() //przycisk startu gry - wylaczenie pauzy i wyswietlenie okna gry.
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
		
		ActionListener al2 = new ActionListener() //przycisk pauzy - wlacza/wylacza pauze i wyswietla informacje o niej.
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
		
		ActionListener al3 = new ActionListener() //przycisk wyjscia z gry (okno gry) - zamyka program.
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        game.window.dispose();
		        System.exit(0);
		    }
		};
		game.window.exitButton.addActionListener(al3);
		
		ActionListener al4 = new ActionListener() //przycisk najlepszych wynikow - wyswietla okno najlepszych wynikow.
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        game.swindow.setVisible(true);
		    }
		};
		game.mwindow.scoreButton.addActionListener(al4);
		
		ActionListener al5 = new ActionListener() //przycisk wyjscia z gry (menu) - zamyka program.
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	game.mwindow.dispose();
		    	game.window.dispose();
		        System.exit(0);
		    }
		};
		game.mwindow.exitButton.addActionListener(al5);
		
		ActionListener al6 = new ActionListener() //przycisk zamkniecia okna najlepszych wynikow.
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	game.swindow.dispose();
		    }
		};
		game.swindow.okButton.addActionListener(al6);
		
		ActionListener al7 = new ActionListener() //przycisk pomocy - wyswietla okno pomocy.
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        game.hwindow.setVisible(true);
		    }
		};
		game.mwindow.helpButton.addActionListener(al7);
		
		ActionListener al8 = new ActionListener() //przycisk zamkniecia okna pomocy.
				{
				    public void actionPerformed(ActionEvent e)
				    {
				        game.hwindow.dispose();
				    }
				};
				game.hwindow.okButton.addActionListener(al8);
				
	}
	
	
}
