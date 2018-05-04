package BallGame;

import java.io.IOException;

/**
 * Glowna klasa gry.
 * @author Rafal Raczynski
 */

public class BallGame {
	
	LevelParser parser;
	static GameWindow window;
	Object[][] tileList;
	
	BallGame() throws IOException {
		
		//TODO: okno bedzie jedynie odczytywac wartosci z parsera, a nie byc przez niego tworzone.
		parser = new LevelParser();
		window = parser.readLevelFile("test.txt");
		
		window.setVisible(true);
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		BallGame game = new BallGame();
		game.window.gameCanvas.setFocusable(true);
		
		Controller controller = new Controller(game);
		new Thread(controller).start();
		GameLoop loop = new GameLoop(controller, game);
		new Thread(loop).start();
	}

}
