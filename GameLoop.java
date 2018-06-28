package BallGame;

import java.io.IOException;
import java.awt.BorderLayout;


/**
 * Glowna petla gry.
 * @author Rafal Raczynski
 */
public class GameLoop implements Runnable{
	
	Controller controller;
	BallGame game;
	boolean gameEnd = false;
	double vBooster = 0;
	double hBooster = 0;
	
	GameLoop(Controller controller, BallGame game){
		this.controller = controller;
		this.game = game;
	}

	public void run() {
		while(gameEnd == false) {

			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//jezeli gra jest spauzowana, to nie obliczamy niczego.
			if(!game.isPaused) {
				
				game.window.gameCanvas.requestFocus();
				
				//obliczanie aktualnej pozycji i predkosci kulki.
				
				if(controller.isAcceleratingUp && game.window.getBallvVel()>-5) {
					if(game.window.getBallvVel()>0) {
						game.window.setBallvVel(0);
					}
					game.window.setBallvVel(game.window.getBallvVel()-1);
				}
				if(controller.isAcceleratingDown && game.window.getBallvVel()< (5 + 5*game.window.gravity)) {
					if(game.window.getBallvVel()<0) {
						game.window.setBallvVel(0);
					}
					game.window.setBallvVel(game.window.getBallvVel()+1);
				}
				if(controller.isSlowingVert && game.window.getBallvVel()<5*game.window.gravity) {
					game.window.setBallvVel(game.window.getBallvVel()+game.window.gravity+1);
				}
				if(controller.isSlowingVert && game.window.getBallvVel()>5*game.window.gravity) {
					game.window.setBallvVel(game.window.getBallvVel()-1);
				}
				if(controller.isAcceleratingHor && !controller.horDirection && game.window.getBallhVel()<5) {
					game.window.setBallhVel(game.window.getBallhVel()+1);
				}
				if(controller.isAcceleratingHor && controller.horDirection && game.window.getBallhVel()>-5) {
					game.window.setBallhVel(game.window.getBallhVel()-1);
				}
				if(controller.isSlowingHor) {
					if(!controller.horDirection && game.window.getBallhVel()>0) {
						game.window.setBallhVel(game.window.getBallhVel()-1);
					}
					if(controller.horDirection && game.window.getBallhVel()<0) {
						game.window.setBallhVel(game.window.getBallhVel()+1);
					}
				}
				
				//kulce przypisywana jest nowa pozycja, plansza jest rysowana.
				
				game.window.gameCanvas.ball.setDoublePos(game.window.getBallPosx()+game.window.getBallhVel()+hBooster, game.window.getBallPosy()+game.window.getBallvVel()+vBooster);
				game.window.gameCanvas.ball.calculateIntPos();
				game.window.gameCanvas.repaint();
				game.window.repaint();
				
				//zmniejszanie puli
				game.window.reducePool();
				
				hBooster = 0;
				vBooster = 0;
				
				
				//obliczanie indeksow pola, na ktorym aktualnie znajduje sie kulka.
				int indx = game.window.getBallPosx()/25;
				int indy = game.window.getBallPosy()/25;
				boolean check;
				
				//algorytm wykrywania kolizji sprawdza pole kulki oraz 8 sasiadujacych z nim pol.
				//w razie istnienia na tym polu obiektu innego niz powietrze, wykonywane sa odpowiednie akcje.
				boolean checkingOuter = true;
				for (int i=indx-1; i<indx+2; i++) {
					for (int j=indy-1; j<indy+2; j++) {
						if((indx == 0 || indx == 29 || indy == 0 || indy == 39) && checkingOuter) {
							System.out.println("DEBUG_COLLISION  " + j + " " + i); //println debug
							System.out.println("DEBUG_LIVES: " + game.window.lives);
							game.window.setLives(game.window.lives-1);
							System.out.println("DEBUG_LIVES: " + game.window.lives);
							if(game.window.lives<=0){
								try{
									game.swindow.insertNewScore(game.window.score, game.playerName);

									game.ewindow.score = game.window.score;
									game.ewindow.updateScore();
									game.swindow.updateScoreText();
									for(int k=0; k<5; k++){
										game.ewindow.places[k].setText(game.swindow.places[k].getText());
									}
									System.out.println(game.swindow.places[0].getText() + " " + game.ewindow.places[0].getText());

									game.window.dispose();
									game.ewindow.setVisible(true);

									gameEnd = true;
								}
								catch(IOException ex){}
							}
							game.window.gameCanvas.ball.setPos(game.window.gameCanvas.ball.originalposx, game.window.gameCanvas.ball.originalposy);
							checkingOuter = false;
						}
						else if(checkingOuter) {
							check = game.window.gameCanvas.tileObjects[j][i].checkCollision(game.window.gameCanvas.ball.posx, game.window.gameCanvas.ball.posy);
							
							if(check && game.window.gameCanvas.tileObjects[j][i] instanceof Wall) {
								System.out.println("DEBUG_COLLISION  " + j + " " + i); //println debug
								game.window.gameCanvas.ball.setPos(game.window.gameCanvas.ball.originalposx, game.window.gameCanvas.ball.originalposy);
								System.out.println("DEBUG_LIVES: " + game.window.lives);
								game.window.setLives(game.window.lives-1);
								System.out.println("DEBUG_LIVES: " + game.window.lives);
								if(game.window.lives<=0){
									try{
										game.swindow.insertNewScore(game.window.score, game.playerName);

										game.ewindow.score = game.window.score;
										game.ewindow.updateScore();
										game.swindow.updateScoreText();
										for(int k=0; k<5; k++){
											game.ewindow.places[k].setText(game.swindow.places[k].getText());
										}

										game.window.dispose();
										game.ewindow.setVisible(true);

										gameEnd = true;
									}
									catch(IOException ex){}
								}

							}
							else if(check && game.window.gameCanvas.tileObjects[j][i] instanceof Star) {
								game.window.setTileObject(j, i, new Air());
								game.window.setScore(game.window.score + game.window.starvalue);
							}
							else if(check && game.window.gameCanvas.tileObjects[j][i] instanceof DirectionalBooster) {
								switch( ((DirectionalBooster)game.window.gameCanvas.tileObjects[j][i]).direction ) {
								case UP: vBooster = -game.window.boosterstrength;
									break;
								case DOWN: vBooster = game.window.boosterstrength;
									break;
								case LEFT: hBooster = -game.window.boosterstrength;
									break;
								case RIGHT: hBooster = game.window.boosterstrength;
									break;
								default: hBooster = 0;
									break;
								}
							}
							else if(check && game.window.gameCanvas.tileObjects[j][i] instanceof GravityOrb) {
								game.window.gravity+=((GravityOrb)game.window.gameCanvas.tileObjects[j][i]).change;
								if(game.window.gravity<0){
									game.window.gravity = 0;
								}
								game.window.setTileObject(j, i, new Air());
							}
							else if(check && game.window.gameCanvas.tileObjects[j][i] instanceof Portal) {
								game.window.setScore(game.window.score + game.window.pool);
								if(game.levelNumber < game.levelCount){
									try{
										game.levelNumber++;
										GameLevelContainer temp = game.parser.readLevelFile("levels.txt", game.levelNumber);
										game.window.gameCanvas.tileObjects = temp.tileObjects;
										game.window.setGravity(temp.gravity);
										game.window.setPool(temp.pool);
										game.window.setLives(temp.lives);;
										game.window.gameCanvas.ball = temp.canvas.ball;
										game.window.setTitle("Kulka: level " + game.levelNumber);
									}
									catch (IOException ex){}
								}
								else {
									try{
										game.swindow.insertNewScore(game.window.score, game.playerName);

										game.ewindow.score = game.window.score;
										game.ewindow.updateScore();
										game.swindow.updateScoreText();
										for(int k=0; k<5; k++){
											game.ewindow.places[k].setText(game.swindow.places[k].getText());
										}

										game.window.dispose();
										game.ewindow.setVisible(true);

										gameEnd = true;
									}
									catch(IOException ex){}
								}
							}
						}
					}
				}
				checkingOuter = true;
			}
		}
	}

}
