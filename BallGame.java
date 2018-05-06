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
	static HelpWindow hwindow;
	static boolean isPaused = false;
	Object[][] tileList;
	
	BallGame() throws IOException {
		
		//TODO: okno bedzie jedynie odczytywac wartosci z parsera, a nie byc przez niego tworzone.
		parser = new LevelParser();
		window = parser.readLevelFile("test.txt");

	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		//okno gry
		BallGame game = new BallGame();
		game.window.gameCanvas.setFocusable(true);
		game.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//okno menu
		mwindow = new MenuWindow("Kulka: Menu Glowne", game);
		game.mwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mwindow.setVisible(true);
		
		//okna najlepszych wynikow i pomocy
		swindow = new TopScoresWindow("Najlepsze Wyniki");
		hwindow = new HelpWindow("Pomoc");
		
		//obsluga klawiatury i flag ruchu
		Controller controller = new Controller(game);
		new Thread(controller).start();
		
		//glowna petla gry
		GameLoop loop = new GameLoop(controller, game);
		new Thread(loop).start();
		
		//gra jest spauzowana do czasu "startu gry".
		isPaused=true;
	}
	/**
	 * Pozwala na pauzowanie gry.
	 */
	public void pause() {
		if(!isPaused) {
			isPaused=true;
		}
	}
	/**
	 * Pozwala na wylaczenie pauzy.
	 */
	public void unpause() {
		if(isPaused) {
			isPaused=false;
		}
	}

}
