package BallGame;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * @author Rafal Raczynski
 * Parsowanie plikow poziomow.
 */
public class LevelParser {
	
	
	GameWindow readLevelFile(String name) throws IOException{
		
		GameCanvas canvas = new GameCanvas();
		Object[][] tileObjects = new Object[20][15];
		char[][] objTypes = new char[20][15];
		int pool=1, lives=1;
		
		FileInputStream input = new FileInputStream(name);
		BufferedInputStream levelInput = new BufferedInputStream(input);
		InputStreamReader levelReader = new InputStreamReader(levelInput);
		LineNumberReader levelFile = new LineNumberReader(levelReader);
		
		String reading;
		reading = levelFile.readLine();
		int failsafe = 0;
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
						canvas.ball.setPos(j, i);
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
				else if(objTypes[i][j] == '.') {
					tileObjects[i][j] = new Air();
				}
				else if(objTypes[i][j] == 's') {
					tileObjects[i][j] = new Star();
				}
			}
		}
		
		canvas.tileObjects = tileObjects;
		
		GameWindow window = new GameWindow("Kulka", canvas, lives, pool);
		
		return window;
	}
}
