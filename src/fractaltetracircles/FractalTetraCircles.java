package fractaltetracircles;

import processing.core.PApplet;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class FractalTetraCircles extends PApplet {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int MAX_DEPTH = 7;
    private static final int STARTING_RADIUS = 130;
    private static final float RADIUS_MULTIPLIER = .5f;

    public static void main(String[] args) {
        PApplet.main("fractaltetracircles.FractalTetraCircles");
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        background(0);
//        noStroke();
        noFill();
        colorMode(HSB,360,100,100,100);
        System.out.println(275/2);
    }

    public void draw() {
        background(0);
        translate((float)width/2, (float)height/2);
        int currentMaxDepth = ceil(map(mouseX, 0, width, 0, MAX_DEPTH));

        drawFracTetraCircles(0, 0, STARTING_RADIUS, currentMaxDepth);
    }

    private void drawFracTetraCircles(float xx, float yy, float radius, int depth) {
        if(depth > 0) {
            stroke(map(xx+yy, -400, 400, 360, 0), 100, 100, 100);
            circle(xx, yy, radius * 2);

            float newRadius = radius * RADIUS_MULTIPLIER;

            drawFracTetraCircles(xx - radius - newRadius, yy, newRadius, depth - 1);
            drawFracTetraCircles(xx, yy + radius + newRadius, newRadius, depth - 1);
            drawFracTetraCircles(xx + radius + newRadius, yy, newRadius, depth - 1);
            drawFracTetraCircles(xx, yy - radius - newRadius, newRadius, depth - 1);
        }
    }
}
