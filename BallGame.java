package BallGame;

import java.io.IOException;

/**
 * Glowna klasa gry.
 * @author Rafal Raczynski
 */

public class BallGame {
	
	LevelParser parser;
	GameWindow window;
	Object[][] tileList;
	
	BallGame() throws IOException{
		
		//TODO: okno bedzie jedynie odczytywac wartosci z parsera, a nie byc przez niego tworzone.
		parser = new LevelParser();
		window = parser.readLevelFile("test.txt");
		
		window.setVisible(true);
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		BallGame game = new BallGame();
		
		//Prototypy okien menu, wynikow, pomocy i wyboru nicku.
		//Docelowo dolaczone beda do obiektu BallGame.
		
		//MenuWindow menu = new MenuWindow("Kulka");
		//menu.setVisible(true);
		//TopScoresWindow scores = new TopScoresWindow("Najlepsze wyniki");
		//scores.setVisible(true);
		//HelpWindow help = new HelpWindow("Zasady gry");
		//help.setVisible(true);
		//NameWindow name = new NameWindow("Podaj nick");
		//name.setVisible(true);
	}
	
	/**
	 * TODO: petla gry?
	 */
	public void gameLoop(GameWindow window) {
		//to do?
	}

}
