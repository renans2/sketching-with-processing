package fractals;

import processing.core.PApplet;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class InsideCircles1 extends PApplet {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int STARTING_RADIUS = 395;
    private static final int DEPTH = 5;

    public static void main(String[] args) {
        PApplet.main("fractals.InsideCircles1");
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        background(0);
        stroke(0);
        noFill();
        colorMode(HSB,360,100,100,100);

        translate((float)width/2, (float)height/2);
        drawFracCircles(0, 0, STARTING_RADIUS, DEPTH);
    }

    private void drawFracCircles(float xx, float yy, float radius, int depth) {
        if(depth > 0) {
            stroke(map(xx, -300, 300, 0, 360),100,100,100);
            circle(xx, yy, radius * 2);

            float newRadius = radius/3;

            drawFracCircles(xx, yy, newRadius, depth - 1);

            for(float angle = PI/2; angle < TWO_PI + PI/2; angle += TWO_PI/6) {
                float x = xx + cos(angle) * (newRadius * 2);
                float y = yy + sin(angle) * (newRadius * 2);
                drawFracCircles(x, y, newRadius, depth - 1);
            }
        }
    }
}
