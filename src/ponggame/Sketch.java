package ponggame;

import processing.core.PApplet;
import processing.event.KeyEvent;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class Sketch extends PApplet {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int BALL_DIAMETER = 30;
    private static final int BALL_X_SPEED = 15;
    private static final int BALL_MAX_Y_SPEED = 5;
    private static final int PADDLE_HEIGHT = 135;
    private static final int PADDLE_WIDTH = 20;
    private static final int PADDLE_SPEED = 10;
    private final boolean[] keys = new boolean[4];
    private PongGame game;

    public static void main(String[] args) {
        PApplet.main("ponggame.Sketch");
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        background(0);
        textSize(100);
        textAlign(CENTER, CENTER);
        noStroke();
        game = new PongGame(this,
                            BALL_DIAMETER,
                            BALL_X_SPEED,
                            BALL_MAX_Y_SPEED,
                            PADDLE_HEIGHT,
                            PADDLE_WIDTH,
                            PADDLE_SPEED);
    }

    public void draw() {
        fill(0,75);
        rect(0,0,WIDTH,HEIGHT);
        fill(255);
        game.update(keys);
        game.draw();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKey() == 'w' && !keys[0])
            keys[0] = true;
        else if (event.getKey() == 's' && !keys[1])
            keys[1] = true;
        else if (event.getKey() == 'o' && !keys[2])
            keys[2] = true;
        else if (event.getKey() == 'l' && !keys[3])
            keys[3] = true;
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKey() == 'w' && keys[0])
            keys[0] = false;
        else if (event.getKey() == 's' && keys[1])
            keys[1] = false;
        else if (event.getKey() == 'o' && keys[2])
            keys[2] = false;
        else if (event.getKey() == 'l' && keys[3])
            keys[3] = false;
    }
}
