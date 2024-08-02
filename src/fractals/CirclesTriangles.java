package fractals;

import processing.core.PApplet;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class CirclesTriangles extends PApplet {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int DEPTH = 6;
    private static final float STARTING_SIZE = 400;

    public static void main(String[] args) {
        PApplet.main("fractals.CirclesTriangles");
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        background(255);
        translate((float)width/2, height * 0.6f);

        drawSingleTriangle(-PI/2, STARTING_SIZE);
        drawFrac(DEPTH, STARTING_SIZE);
    }

    private void drawFrac(int depth, float size) {
        if(depth > 0) {
            circle(0,0,size);
            drawSingleTriangle(-PI/2, size/2);
            drawFrac(depth - 1, size/2);

            for(float angle = -PI/2; angle <= -PI/2 + TWO_PI; angle += TWO_PI/3) {
                push();
                float x = cos(angle) * 2 * (size/3);
                float y = sin(angle) * 2 * (size/3);
                translate(x, y);
                drawFrac(depth-1, size/3);
                pop();
            }
        }
    }

    private void drawSingleTriangle(float startingAngle, float size) {
        beginShape();
        for (float i = startingAngle; i < TWO_PI + startingAngle; i += TWO_PI/3) {
            float x = cos(i) * size;
            float y = sin(i) * size;
            vertex(x, y);
        }
        endShape(CLOSE);
    }
}
