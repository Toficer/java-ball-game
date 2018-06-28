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
	static NameWindow nwindow;
	static EndWindow ewindow;
	static boolean isPaused = false;
	static int levelNumber;
	static String playerName;
	static int levelCount;
	
	BallGame() throws IOException {

		parser = new LevelParser();
		levelNumber = 1;
		GameLevelContainer temp = parser.readLevelFile("levels.txt", levelNumber);
		levelCount = parser.getLevelCount("levels.txt");
		window = new GameWindow(("Kulka, level: " + levelNumber), temp.canvas, temp.lives, temp.pool, temp.poolDecay, temp.gravity, temp.starvalue, temp.boosterstrength, temp.gballvalue);

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

		//okno wyboru nicku
		nwindow = new NameWindow("Podaj nick");
		game.nwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//okno koncowe
		ewindow = new EndWindow("Koniec gry");
		game.ewindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
