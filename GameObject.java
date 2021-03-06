package BallGame;

/**
 * Obiekt gry: podstawowy obiekt gry.
 * @author Rafal Raczynski
 */

public class GameObject{
	
	//Pozycja absolutna na polu gry 750x1000.
	//Od niej zalezec bedzie obliczanie kolizji itd.
	int posx = 0, posy = 0;
	//Pozycja na polu rysowania.
	int drawingPosx=0, drawingPosy=0;
	//Wymiary.
	int d1 = 10, d2 = 10;
	
	/**
	 * Metoda ta przelicza absolutna pozycje na polu gry na pozycje na polu rysowania.
	 */
	public void setDrawingPos(int multx, int multy, int xoffset, int yoffset) {
		this.drawingPosx = (int)(((float)posx/750)*multx + xoffset);
		this.drawingPosy = (int)(((float)posy/1000)*multy + yoffset);
	}
	
	/**
	 * Metoda ta pozwala ustawic absolutna pozycje obiektu.
	 */
	public void setPos(int posx, int posy) {
		this.posx = posx;
		this.posy = posy;
	}
	
	/**
	 * Metoda ta pozwala ustawic wymiary obiektu.
	 */
	public void setDimensions(int d1, int d2) {
		this.d1 = d1;
		this.d2 = d2;
	}
	/**
	 * Metoda ta sprawdza, czy kulka o podanych X i Y zderza sie z obiektem.
	 * @param ballx Pozycja kulki na osi OX
	 * @param bally Pozycja kulki na osi OY
	 */
	public boolean checkCollision(int ballx, int bally) {
		return (ballx >= (posx - 15) && ballx <= (posx+24) && bally>=(posy-15) && bally<=(posy+24));
	}
}
