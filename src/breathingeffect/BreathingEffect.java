package breathingeffect;

import processing.core.PApplet;
import processing.core.PVector;

public class BreathingEffect extends PApplet {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int ROWS = 30;
    private static final int COLS = 30;
    public static final float MIN_OSC_MULTIPLIER = 0f;
    public static final float MAX_OSC_MULTIPLIER = 0.65f;
    public static final float ANGLE_INCREMENTER = -0.05f;
    private static final float OFFSET_X = (float)WIDTH/COLS;
    private static final float OFFSET_Y = (float)HEIGHT/ROWS;
    public static final float MIN_DIAMETER = 1;
    public static final float MAX_DIAMETER = 15;
    private final Unit[][] units = new Unit[COLS][ROWS];

    public static void main(String[] args) {
        PApplet.main("breathingeffect.BreathingEffect");
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        noStroke();
        fill(255);

        for (int i = 0; i < COLS; i++)
            for (int j = 0; j < ROWS; j++){
                float x = map(i, 0, COLS, -(float)WIDTH/2,  (float)WIDTH/2)  + OFFSET_X/2;
                float y = map(j, 0, ROWS, -(float)HEIGHT/2, (float)HEIGHT/2) + OFFSET_Y/2;
                float distanceToCenter = dist(0, 0, x, y);
                float angle = map(distanceToCenter, 0, 400, 0, PI/1.5f);

                units[i][j] = new Unit(this, new PVector(x, y), angle);
            }
    }

    public void draw() {
        background(0);
        translate((float)width/2, (float)height/2);

        for (int i = 0; i < COLS; i++)
            for (int j = 0; j < ROWS; j++)
                units[i][j].draw();
    }
}
