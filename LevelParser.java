package BallGame;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import BallGame.DirectionalBooster.ArrowDirection;

/**
 * Parsowanie plikow poziomow. Klasa tworzy nowe okno gry na podstawie odczytanego pliku poziomu.
 * Docelowo odczytywane bedzie wiecej parametrow, jak grawitacja czy szybkosc zmiany puli.
 * Na chwile obecna zadne z tych rzeczy nie sa zaimplementowane, wiec parser ich nie szuka.
 * @author Rafal Raczynski
 */
public class LevelParser {
	
	
	GameWindow readLevelFile(String name) throws IOException{
		
		//Deklaracja wszystkich niezbednych zmiennych i obiektow.
		GameCanvas canvas = new GameCanvas();
		GameObject[][] tileObjects = new GameObject[40][30];
		char[][] objTypes = new char[40][30];
		int pool=1, lives=1, poolDecay = 0, gravity=1;
		
		FileInputStream input = new FileInputStream(name);
		BufferedInputStream levelInput = new BufferedInputStream(input);
		InputStreamReader levelReader = new InputStreamReader(levelInput);
		LineNumberReader levelFile = new LineNumberReader(levelReader);
		
		String reading;
		
		//Wczytujemy pierwsza linie pliku.
		reading = levelFile.readLine();
		int failsafe = 0;
		
		//Ignorujemy wszystko, co znajduje sie przed "end description".
		//Daje nam to mozliwosc dodania legendy w pliku.
		while(!reading.equals("end description")) {
			reading = levelFile.readLine();
		}
		
		//Odczyt wartosci puli punktow oraz liczby zyc.
		//Nieznane parametry sa ignorowane.
		//failsafe ogranicza ilosc linii parametrow do 100.
		reading = levelFile.readLine();
		while(!reading.equals("begin level") && failsafe<100) {
			
			if(reading.contains("pool")) {
				reading = reading.replaceAll("\\D+","");
				pool = Integer.parseInt(reading);
			}
			else if(reading.contains("lives")) {
				reading = reading.replaceAll("\\D+","");
				lives = Integer.parseInt(reading);
			}
			else if(reading.contains("pdecay")) {
				reading = reading.replaceAll("\\D+","");
				poolDecay = Integer.parseInt(reading);
			}
			else if(reading.contains("gravity")) {
				reading = reading.replaceAll("\\D+","");
				gravity = Integer.parseInt(reading);
			}
			else{
				System.out.println("UNKNOWN PARAMETER, IGNORING");
			}
			reading = levelFile.readLine();
			failsafe++;
		}
		
		//Odczyt tablicy znakow 20x15.
		//Znaki reprezentuja obiekty wg. legendy.
		//Nowe linie sa ignorowane.
		for(int i=0; i<40; i++) {
			for(int j=0; j<30; j++) {
				objTypes[i][j] = (char) levelFile.read();
				
				//Szczegolny przypadek: pozycja kulki.
				//Tworzony jest obiekt typu "Air".
				if((objTypes[i][j]) =='k'){
						objTypes[i][j] = '.';
						canvas.ball.setPos(j*25 +1, i*25 + 1);
						canvas.ball.setOriginalPos(j*25 + 1, i*25 +1);
				}
				
				
				if(objTypes[i][j] == '\r' || objTypes[i][j] == '\n') {
					objTypes[i][j] = (char) levelFile.read();
					if(objTypes[i][j] == '\r' || objTypes[i][j] == '\n') {
						objTypes[i][j] = (char) levelFile.read();
					}
				}
			}
		}
		//Zamykamy plik.
		levelFile.close();
		
		//Tworzenie obiektow gry na podstawie znakow z tablicy.
		//Kazdy obiekt otrzymuje pozycje na wzorcowym polu gry 750x1000.
		for(int i=0; i<40; i++) {
			for(int j=0; j<30; j++) {
				if(objTypes[i][j] == 'x') {
					tileObjects[i][j] = new Wall();
				}
				else if(objTypes[i][j] == 'p') {
					tileObjects[i][j] = new Portal();
				}
				else if(objTypes[i][j] == 's') {
					tileObjects[i][j] = new Star();
				}
				else if(objTypes[i][j] == 'u') {
					tileObjects[i][j] = new DirectionalBooster(ArrowDirection.UP);
				}
				else if(objTypes[i][j] == 'd') {
					tileObjects[i][j] = new DirectionalBooster(ArrowDirection.DOWN);
				}
				else if(objTypes[i][j] == 'l') {
					tileObjects[i][j] = new DirectionalBooster(ArrowDirection.LEFT);
				}
				else if(objTypes[i][j] == 'r') {
					tileObjects[i][j] = new DirectionalBooster(ArrowDirection.RIGHT);
				}
				else if(objTypes[i][j] == 'n') {
					tileObjects[i][j] = new GravityOrb(-10);
				}
				else if(objTypes[i][j] == 'm') {
					tileObjects[i][j] = new GravityOrb(10);
				}
				else{
					tileObjects[i][j] = new Air();
				}
				tileObjects[i][j].setPos(j*25, i*25);
			}
		}
		
		//Przekazanie tablicy obiektow gry do pola rysowania.
		canvas.tileObjects = tileObjects;
		
		//Tworzenie okna gry.
		//TODO: Parser bedzie jedynie tworzyl obiekty i zapisywal je w swoich polach.
		//Okno docelowo tworzone bedzie poza parserem.
		GameWindow window = new GameWindow("Kulka", canvas, lives, pool, poolDecay, gravity);
		
		return window;
	}
}
