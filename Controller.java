package BallGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * Kontroler odpowiada za obsluge flag klawiatury i przyciskow.
 * @author Rafal Raczynski
 */
public class Controller implements Runnable{
	
	boolean isAcceleratingUp = false;
	boolean isAcceleratingDown = false;
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
					isAcceleratingUp = true;
					isAcceleratingDown = false;
					isSlowingVert = false;
		        }
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					isAcceleratingUp = false;
					isAcceleratingDown = true;
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
					isAcceleratingUp = false;
					isAcceleratingDown = false;
					isSlowingVert = true;
		        }
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					isAcceleratingUp = false;
					isAcceleratingDown = false;
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
		
		ActionListener startListener = new ActionListener() //przycisk startu gry - wylaczenie pauzy i wybor nicku.
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        game.nwindow.setVisible(true);
		        game.mwindow.setVisible(false);
		    }
		};
		BallGame.mwindow.startButton.addActionListener(startListener);

		ActionListener nameStartListener = new ActionListener() //przycisk startu gry - zamyka okno wyboru nicku i zaczyna gre.
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!game.nwindow.nameField.getText().contains(" ") && game.nwindow.nameField.getText().length() > 0){
					System.out.println("DEBUG_NAMELENGTH " + game.nwindow.nameField.getText().length());
					game.playerName = game.nwindow.nameField.getText();
					game.nwindow.infoLabel.setText("Podaj swoj nick");
					game.window.setVisible(true);
					game.nwindow.dispose();
					game.window.gameCanvas.ball.setPos(game.window.gameCanvas.ball.originalposx, game.window.gameCanvas.ball.originalposy);
					game.unpause();
				}
				else {
					game.nwindow.infoLabel.setText("NIEPRAWIDLOWY NICK!");
				}

			}
		};
		BallGame.nwindow.startButton.addActionListener(nameStartListener);

		ActionListener nameCancelListener = new ActionListener() //przycisk powrotu do menu - zamyka okno wyboru nicku.
		{
			public void actionPerformed(ActionEvent e)
			{
				game.nwindow.setVisible(false);
				game.mwindow.setVisible(true);
			}
		};
		BallGame.nwindow.cancelButton.addActionListener(nameCancelListener);

		ActionListener pauseListener = new ActionListener() //przycisk pauzy - wlacza/wylacza pauze i wyswietla informacje o niej.
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
		game.window.pauseButton.addActionListener(pauseListener);
		
		ActionListener exitGameListener = new ActionListener() //przycisk wyjscia z gry (okno gry) - zamyka program.
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        game.window.dispose();
		        System.exit(0);
		    }
		};
		game.window.exitButton.addActionListener(exitGameListener);
		
		ActionListener scoreListener = new ActionListener() //przycisk najlepszych wynikow - wyswietla okno najlepszych wynikow.
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	try{
					game.swindow.setVisible(true);
					game.swindow.updateScoreText();
				}
		        catch(IOException ex){}
		    }
		};
		game.mwindow.scoreButton.addActionListener(scoreListener);
		
		ActionListener exitMenuListener = new ActionListener() //przycisk wyjscia z gry (menu) - zamyka program.
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	game.mwindow.dispose();
		    	game.window.dispose();
		        System.exit(0);
		    }
		};
		game.mwindow.exitButton.addActionListener(exitMenuListener);
		
		ActionListener exitScoreListener = new ActionListener() //przycisk zamkniecia okna najlepszych wynikow.
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	game.swindow.dispose();
		    }
		};
		game.swindow.okButton.addActionListener(exitScoreListener);
		
		ActionListener helpListener = new ActionListener() //przycisk pomocy - wyswietla okno pomocy.
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        game.hwindow.setVisible(true);
		    }
		};
		game.mwindow.helpButton.addActionListener(helpListener);
		
		ActionListener exitHelpListener = new ActionListener() //przycisk zamkniecia okna pomocy.
		{
			public void actionPerformed(ActionEvent e)
				    {
				        game.hwindow.dispose();
				    }
		};
		game.hwindow.okButton.addActionListener(exitHelpListener);

		ActionListener exitEndListener = new ActionListener() //przycisk zamkniecia okna konca gry.
		{
			public void actionPerformed(ActionEvent e)
			{
				game.ewindow.dispose();
				System.exit(0);
			}
		};
		game.ewindow.okButton.addActionListener(exitEndListener);
				
	}
	
	
}
