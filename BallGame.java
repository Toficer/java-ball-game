package BallGame;

import java.io.IOException;

/**
 * 
 * @author Rafal Raczynski
 * Glowna klasa gry.
 */

public class BallGame {
	
	LevelParser parser;
	GameWindow window;
	Object[][] tileList;
	
	BallGame() throws IOException{
		
		parser = new LevelParser();
		window = parser.readLevelFile("test.txt");
		
		window.setVisible(true);
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		BallGame game = new BallGame();
		//MenuWindow window = new MenuWindow("Kulka");
		//window.setVisible(true);
		
	}
	
	public void gameLoop(GameWindow window) {
		//to do?
	}

}
