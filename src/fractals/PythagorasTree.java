package fractals;

import processing.core.PApplet;

public class PythagorasTree extends PApplet {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final float STARTING_SIDE_SIZE = 130;
    private static final int MAX_DEPTH = 17;

    public static void main(String[] args) {
        PApplet.main("fractals.PythagorasTree");
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        background(0);
        rectMode(CENTER);
        fill(255);
        noStroke();
    }

    public void draw() {
        background(0);
        translate((float)width/2, height - 1.75f*STARTING_SIDE_SIZE);
        scale(1,-1);

        int currentMaxDepth = (int) map(mouseX, 0, width, 1, MAX_DEPTH);
        drawPythagorasTree(currentMaxDepth, STARTING_SIDE_SIZE);
    }

    private void drawPythagorasTree(int depth, float sideSize) {
        if(depth > 0) {
            rect(0, 0, sideSize, sideSize);

            float newSideSize = sideSize/sqrt(2);

            drawNewSquare(depth-1, newSideSize, 1);
            drawNewSquare(depth-1, newSideSize, -1);
        }
    }

    private void drawNewSquare(int depth, float sideSize, int direction) {
        push();
        rotate(PI/4 * direction);
        translate(direction * sideSize/2, 3*sideSize/2);
        drawPythagorasTree(depth, sideSize);
        pop();
    }
}
