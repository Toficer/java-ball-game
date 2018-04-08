package BallGame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * Glowne okno gry. Zawiera plansze gry oraz podstawowe przyciski pozwalajace kontrolowac aplikacje.
 * @author Rafal Raczynski
 */
public class GameWindow extends Frame{
	
	int lives=1;
	int pool=1;
	int score=10000;
	GameCanvas gameCanvas;
	Label lives_label, pool_label, score_label;
	
	GameWindow(String title, GameCanvas canvas, int lives_n, int pool_n){
		
		super(title);
		gameCanvas = canvas;
		
		pool = pool_n;
		lives = lives_n;
		
		lives_label = new Label("ZYCIA: " + lives);
		pool_label = new Label("PULA: " + pool);
		score_label = new Label("WYNIK: " + score);
		
		Button exitButton = new Button("WYJSCIE");
		Button pauseButton = new Button("PAUZA");
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
			}	
		});
		
		Panel headerPanel = new Panel(new BorderLayout());
		headerPanel.add(pauseButton, BorderLayout.EAST);
		headerPanel.add(exitButton, BorderLayout.WEST);
		headerPanel.setBackground(Color.lightGray);
		
		Panel footerPanel = new Panel(new BorderLayout());
		footerPanel.add(lives_label, BorderLayout.EAST);
		footerPanel.add(score_label, BorderLayout.WEST);
		footerPanel.add(pool_label, BorderLayout.CENTER);
		footerPanel.setBackground(Color.lightGray);
		
		Panel centralPanel = new Panel(new BorderLayout());
		centralPanel.setBackground(Color.lightGray);
		centralPanel.add(gameCanvas, BorderLayout.CENTER);
		add(centralPanel, BorderLayout.CENTER);
		add(headerPanel, BorderLayout.NORTH);
		add(footerPanel, BorderLayout.SOUTH);
		pack();
	}
	/**
	 * Pozwala na edycje zawartosci pola "ZYCIA".
	 * @param input Zadana ilosc zyc.
	 */
	public void setLives(int input) {
		lives = input;
	}
	/**
	 * Pozwala na edycje zawartosci pola "PULA".
	 * @param input Zadana pula punktow do zdobycia.
	 */
	public void setPool(int input) {
		pool = input;
	}
	/**
	 * Pozwala na edycje zawartosci pola "WYNIK".
	 * @param input Zadany wynik.
	 */
	public void setScore(int input) {
		score = input;
	}
	/**
	 * Pozwala (lub bedzie pozwalac) na podmienienie obiektu z tablicy obiektow gry pola rysowania.
	 * @param object Obiekt do podmiany.
	 */
	public void setTileObject(int i, int j, GameObject object) {
		gameCanvas.tileObjects[i][j] = object;
		//placeholder method for interacting with the canvas (TODO)
	}
}
