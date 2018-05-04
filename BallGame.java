package BallGame;

import java.io.IOException;
import javax.swing.JFrame;

/**
 * Glowna klasa gry.
 * @author Rafal Raczynski
 */

public class BallGame {
	
	LevelParser parser;
	static GameWindow window;
	static MenuWindow mwindow;
	static TopScoresWindow swindow;
	static boolean isPaused = false;
	Object[][] tileList;
	
	BallGame() throws IOException {
		
		//TODO: okno bedzie jedynie odczytywac wartosci z parsera, a nie byc przez niego tworzone.
		parser = new LevelParser();
		window = parser.readLevelFile("test.txt");
		
		//window.setVisible(true);
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		BallGame game = new BallGame();
		game.window.gameCanvas.setFocusable(true);
		game.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mwindow = new MenuWindow("Kulka: Menu Glowne", game);
		game.mwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mwindow.setVisible(true);
		
		swindow = new TopScoresWindow("Najlepsze Wyniki");
		
		Controller controller = new Controller(game);
		new Thread(controller).start();
		
		GameLoop loop = new GameLoop(controller, game);
		Thread th2 = new Thread(loop);
		th2.start();
		isPaused=true;
	}
	
	public void pause() {
		if(!isPaused) {
			isPaused=true;
		}
	}
	public void unpause() {
		if(isPaused) {
			isPaused=false;
		}
	}

}
