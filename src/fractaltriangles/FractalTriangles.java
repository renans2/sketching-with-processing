package fractaltriangles;

import processing.core.PApplet;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class FractalTriangles extends PApplet {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int MAX_DEPTH = 8;
    private static final float STARTING_SIZE = 400;

    public static void main(String[] args) {
        PApplet.main("fractaltriangles.FractalTriangles");
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        background(0);
        stroke(255);
        noFill();
    }

    public void draw() {
        translate((float)width/2, height * 0.60f);
        background(0);
        rotate(-PI/2);
        int currentMaxDepth = (int) map(mouseX, 0, width, 0, MAX_DEPTH);
        drawSingleTriangle(STARTING_SIZE);
        drawFracTriangles(STARTING_SIZE/2, currentMaxDepth);
    }

    private void drawFracTriangles(float size, int depth) {
        if(depth > 0) {
            push();
            rotate(PI);
            drawSingleTriangle(size);
            pop();

            for (float angle = 0; angle < TWO_PI; angle += TWO_PI/3) {
                push();
                float x = cos(angle) * size;
                float y = sin(angle) * size;
                translate(x, y);
                drawFracTriangles(size/2, depth-1);
                pop();
            }
        }
    }

    private void drawSingleTriangle(float size) {
        beginShape();
        for (float i = 0; i <= TWO_PI; i += TWO_PI/3) {
            float x = cos(i) * size;
            float y = sin(i) * size;
            vertex(x, y);
        }
        endShape();
    }
}
