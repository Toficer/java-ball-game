package BallGame;
/**
 * Glowna petla gry.
 * @author Rafal Raczynski
 */
public class GameLoop implements Runnable{
	
	Controller controller;
	BallGame game;
	boolean gameEnd = false;
	boolean upBooster = false;
	boolean downBooster = false;
	boolean leftBooster = false;
	boolean rightBooster = false;
	int vBooster = 0;
	int hBooster = 0;
	
	GameLoop(Controller controller, BallGame game){
		this.controller = controller;
		this.game = game;
	}

	public void run() {
		while(gameEnd == false) {
			
			//klatka co 21ms.
			try {
				Thread.sleep(21);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//jezeli gra jest spauzowana, to nie obliczamy niczego.
			if(!game.isPaused) {
				
				game.window.gameCanvas.requestFocus();
				
				//obliczanie aktualnej pozycji i predkosci kulki.
				
				if(controller.isAcceleratingVert && game.window.getBallvVel()>-5) {
					if(game.window.getBallvVel()>0) {
						game.window.setBallvVel(0);
					}
					game.window.setBallvVel(game.window.getBallvVel()-1);
				}
				if(controller.isSlowingVert && game.window.getBallvVel()<5*game.window.gravity) {
					game.window.setBallvVel(game.window.getBallvVel()+game.window.gravity);
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
				
				game.window.gameCanvas.ball.setPos(game.window.getBallPosx()+game.window.getBallhVel()+hBooster, game.window.getBallPosy()+game.window.getBallvVel()+vBooster);
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
				//TODO: uzaleznienie wartosci boosterow, gwiazdek i kul grawitacyjnych od pliku konfiguracyjnego.
				for (int i=indx-1; i<indx+2; i++) {
					for (int j=indy-1; j<indy+2; j++) {
						
						if(indx == 0 || indx == 29 || indy == 0 || indy == 39) {
							System.out.println("COLLISION  " + j + " " + i); //println debug
							game.window.gameCanvas.ball.setPos(game.window.gameCanvas.ball.originalposx, game.window.gameCanvas.ball.originalposy);
						}
						else {
							check = game.window.gameCanvas.tileObjects[j][i].checkCollision(game.window.gameCanvas.ball.posx, game.window.gameCanvas.ball.posy);
							
							if(check && game.window.gameCanvas.tileObjects[j][i] instanceof Wall) {
								System.out.println("COLLISION  " + j + " " + i); //println debug
								game.window.gameCanvas.ball.setPos(game.window.gameCanvas.ball.originalposx, game.window.gameCanvas.ball.originalposy);
							}
							else if(check && game.window.gameCanvas.tileObjects[j][i] instanceof Star) {
								game.window.setTileObject(j, i, new Air());
								game.window.setScore(game.window.score + 50);
							}
							else if(check && game.window.gameCanvas.tileObjects[j][i] instanceof DirectionalBooster) {
								switch( ((DirectionalBooster)game.window.gameCanvas.tileObjects[j][i]).direction ) {
								case UP: vBooster = -2;
									break;
								case DOWN: vBooster = 2;
									break;
								case LEFT: hBooster = -2;
									break;
								case RIGHT: hBooster = 2;
									break;
								default: hBooster = 0;
									break;
								}
							}
							else if(check && game.window.gameCanvas.tileObjects[j][i] instanceof GravityOrb) {
								game.window.setTileObject(j, i, new Air());
							}
							else if(check && game.window.gameCanvas.tileObjects[j][i] instanceof Portal) {
								gameEnd = true; //petla jest przerywana po najechaniu na portal - koniec planszy
							}
						}
					}
				}
			}
		}
	}

}
