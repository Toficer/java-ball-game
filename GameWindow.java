package BallGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Glowne okno gry. Zawiera plansze gry oraz podstawowe przyciski pozwalajace kontrolowac aplikacje.
 * @author Rafal Raczynski
 */
public class GameWindow extends JFrame{
	
	int lives=1; //zycia
	int pool=1; //pula
	int score=0; //wynik
	int poolDecay = 0; //predkosc zmniejszania puli
	int gravity = 1;
	GameCanvas gameCanvas; //obszar animacji
	JLabel lives_label, pool_label, score_label; //teksty UI
	JButton pauseButton, exitButton; //przyciski pauzy i wyjscia z gry
	
	GameWindow(String title, GameCanvas canvas, int lives_n, int pool_n, int poolDecay_n, int gravity_n){
		
		super(title);
		
		//pola ustawiane sa na podstawie wartosci danych konstruktorowi przez parser (odczytanych z pliku).
		
		gameCanvas = canvas;
		pool = pool_n;
		lives = lives_n;
		poolDecay = poolDecay_n;
		gravity = gravity_n;
		
		//teksty UI sa tworzone z zadanymi w pliku wartosciami poczatkowymi.
		
		lives_label = new JLabel("ZYCIA: " + lives);
		pool_label = new JLabel("PULA: " + pool);
		score_label = new JLabel("WYNIK: " + score);
		
		//przyciski sa tworzone.
		
		exitButton = new JButton("WYJSCIE");
		pauseButton = new JButton("PAUZA");
		
		//mozliwosc zamkniecia okna.
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
			}	
		});
		
		//Okno zbudowane jest z paneli wypelnionych przyciskami, tekstami i polem rysowania.
		
		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.add(pauseButton, BorderLayout.EAST);
		headerPanel.add(exitButton, BorderLayout.WEST);
		headerPanel.setBackground(Color.lightGray);
		
		JPanel footerPanel = new JPanel(new BorderLayout());
		JPanel leftPanel = new JPanel(new FlowLayout());
		leftPanel.setBackground(Color.lightGray);
		footerPanel.add(lives_label, BorderLayout.EAST);
		leftPanel.add(score_label);
		leftPanel.add(pool_label);
		footerPanel.add(leftPanel, BorderLayout.WEST);
		footerPanel.setBackground(Color.lightGray);
		
		JPanel centralPanel = new JPanel(new BorderLayout());
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
	 * Pozwala na edycje sily grawitacji.
	 * @param input Zadana grawitacja.
	 */
	public void setGravity(int input) {
		gravity = input;
	}
	/**
	 * Pozwala na edycje zawartosci pola "PULA".
	 * @param input Zadana pula punktow do zdobycia.
	 */
	public void setPool(int input) {
		pool = input;
		pool_label.setText("PULA: " + pool);
	}
	/**
	 * Pozwala na edycje zawartosci pola "WYNIK".
	 * @param input Zadany wynik.
	 */
	public void setScore(int input) {
		score = input;
		score_label.setText("WYNIK: " + score);
	}
	/**
	 * Pozwala (lub bedzie pozwalac) na podmienienie obiektu z tablicy obiektow gry pola rysowania.
	 * @param object Obiekt do podmiany.
	 */
	public void setTileObject(int i, int j, GameObject object) {
		gameCanvas.tileObjects[i][j] = object;
		gameCanvas.tileObjects[i][j].setPos(i*25, j*25);
	}
	/**
	 * Pozwala na dekrementacje zawartosci pola "PULA" o ustalony poziom spaku.
	 */
	public void reducePool() {
		pool -= poolDecay;
		if(pool<0) {
			pool = 0;
		}
		pool_label.setText("PULA: " + pool);
	}
	/**
	 * Zwraca pozycje kulki na osi OX.
	 */
	public int getBallPosx() {
		return gameCanvas.ball.returnPosx();
	}
	/**
	 * Zwraca pozycje kulki na osi OY.
	 */
	public int getBallPosy() {
		return gameCanvas.ball.returnPosy();
	}
	/**
	 * Zwraca horyzontalna predkosc kulki.
	 */
	public int getBallhVel() {
		return gameCanvas.ball.hVelocity;
	}
	/**
	 * Zwraca wertykalna predkosc kulki.
	 */
	public int getBallvVel() {
		return gameCanvas.ball.vVelocity;
	}
	/**
	 * Pozwala ustawic horyzontalna predkosc kulki.
	 * @param v Wartosc predkosci.
	 */
	public void setBallhVel(int v) {
		gameCanvas.ball.hVelocity = v;
	}
	/**
	 * Pozwala ustawic wertykalna predkosc kulki.
	 * @param v Wartosc predkosci.
	 */
	public void setBallvVel(int v) {
		gameCanvas.ball.vVelocity = v;
	}
}
