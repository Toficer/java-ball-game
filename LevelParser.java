package BallGame;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import BallGame.DirectionalBooster.ArrowDirection;

/**
 * Parsowanie plikow poziomow. Klasa tworzy nowe okno gry na podstawie odczytanego pliku poziomu.
 * @author Rafal Raczynski
 */
public class LevelParser {
	
	
	GameWindow readLevelFile(String name) throws IOException{
		
		GameCanvas canvas = new GameCanvas();
		GameObject[][] tileObjects = new GameObject[20][15];
		char[][] objTypes = new char[20][15];
		int pool=1, lives=1;
		
		FileInputStream input = new FileInputStream(name);
		BufferedInputStream levelInput = new BufferedInputStream(input);
		InputStreamReader levelReader = new InputStreamReader(levelInput);
		LineNumberReader levelFile = new LineNumberReader(levelReader);
		
		String reading;
		reading = levelFile.readLine();
		int failsafe = 0;
		
		while(!reading.equals("end description")) {
			reading = levelFile.readLine();
		}
		
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
			else{
				System.out.println("UNKNOWN PARAMETER, IGNORING");
			}
			reading = levelFile.readLine();
			failsafe++;
		}
		
		for(int i=0; i<20; i++) {
			for(int j=0; j<15; j++) {
				objTypes[i][j] = (char) levelFile.read();
				if((objTypes[i][j]) =='k'){
						objTypes[i][j] = '.';
						canvas.ball.setPos(j*50, i*50);
				}
				if(objTypes[i][j] == '\r' || objTypes[i][j] == '\n') {
					objTypes[i][j] = (char) levelFile.read();
					if(objTypes[i][j] == '\r' || objTypes[i][j] == '\n') {
						objTypes[i][j] = (char) levelFile.read();
					}
				}
			}
		}
		
		levelFile.close();
		
		for(int i=0; i<20; i++) {
			for(int j=0; j<15; j++) {
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
				tileObjects[i][j].setPos(j*50, i*50);
			}
		}
		
		canvas.tileObjects = tileObjects;
		
		GameWindow window = new GameWindow("Kulka", canvas, lives, pool);
		
		return window;
	}
}
