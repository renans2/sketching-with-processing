package fractalsquares;

import processing.core.PApplet;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class FractalSquares extends PApplet {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int MAX_DEPTH = 7;
    private static final float STARTING_SIZE = 700;
    private int currentMaxDepth = 0;

    public static void main(String[] args) {
        PApplet.main("fractalsquares.FractalSquares");
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        background(0);
        strokeWeight(1.5f);
        rectMode(CENTER);
        noFill();
        colorMode(HSB,360,100,100,100);
    }

    public void draw() {
        translate((float)width/2, (float)height/2);
        background(0);
        currentMaxDepth = (int)map(mouseX, 0, width, 0, MAX_DEPTH);

        push();
        strokeWeight(3);
        stroke(360);
        rect(0, 0, STARTING_SIZE, STARTING_SIZE);
        pop();

        drawFracSquares(0, 0, STARTING_SIZE/3, currentMaxDepth);
    }

    private void drawFracSquares(float xx, float yy, float size, int depth) {
        if(depth > 0) {
            stroke(map(xx + yy, -STARTING_SIZE, STARTING_SIZE, 0, 300), 100, 100, 100);
            rect(xx, yy, size, size);

            float x;
            float y;
            float epsilon = 0.00001f;

            for (float angle = 0; angle < TWO_PI - epsilon; angle += TWO_PI/8) {
                push();
                if     (cos(angle) < -epsilon) x = -size;
                else if(cos(angle) >  epsilon) x = size;
                else                           x = 0;

                if     (sin(angle) < -epsilon) y = -size;
                else if(sin(angle) >  epsilon) y = size;
                else                           y = 0;

                drawFracSquares(x+xx, y+yy, size/3, depth-1);
                pop();
            }
        }
    }
}
