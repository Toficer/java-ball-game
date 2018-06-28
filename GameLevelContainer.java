package BallGame;
/**
 * Kontener ten zawiera obiekty i parametry utworzone na podstawie pliku wejsciowego, gotowe do tworzenia planszy.
 * @author Rafal Raczynski
 */
public class GameLevelContainer {
    GameCanvas canvas;
    int lives, pool, poolDecay, starvalue, boosterstrength;
    double gballvalue, gravity;
    GameObject[][] tileObjects;

    GameLevelContainer(GameCanvas canvas, GameObject[][] tileObjects, int lives, int pool, int poolDecay, double gravity, int starvalue, int boosterstrength, double gballvalue){
        this.canvas = canvas;
        this.tileObjects = tileObjects;
        this.lives = lives;
        this.pool = pool;
        this.poolDecay = poolDecay;
        this.gravity = gravity;
        this.starvalue = starvalue;
        this.boosterstrength = boosterstrength;
        this.gballvalue = gballvalue;
    }
}
