package fractals;

import processing.core.PApplet;

public class KochFractal extends PApplet {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int MAX_DEPTH = 7;
    private static final float STARTING_SIZE = 400;

    public static void main(String[] args) {
        PApplet.main("fractals.KochFractal");
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        noStroke();
        fill(0);
    }

    public void draw() {
        background(255);
        translate((float)width/2, (float)height/2);
        drawSingleTriangle(-PI/2, STARTING_SIZE);

        int currentMaxDepth = (int) map(mouseX, 0, WIDTH, 1, MAX_DEPTH);
        drawKochFractal(currentMaxDepth, PI/2, STARTING_SIZE);
    }

    private void drawKochFractal(int depth, float startingAngle, float size) {
        if(depth > 0) {
            drawSingleTriangle(startingAngle, size);

            float newSize = size/3;
            float radius = newSize * 2;

            for(float angle = startingAngle ; angle < TWO_PI + startingAngle - 0.1f ; angle += TWO_PI/6) {
                float x = cos(angle) * radius;
                float y = sin(angle) * radius;
                push();
                translate(x, y);
                drawKochFractal(depth - 1, angle + PI, newSize);
                pop();
            }
        }
    }

    private void drawSingleTriangle(float startingAngle, float size) {
        beginShape();
        for (float i = startingAngle; i <= TWO_PI + startingAngle; i += TWO_PI/3) {
            float x = cos(i) * size;
            float y = sin(i) * size;
            vertex(x, y);
        }
        endShape();
    }
}
