package fractals;

import processing.core.PApplet;

public class BarnsleyFern extends PApplet {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int DEPTH = 5;
    private static final float STARTING_LENGTH = 65;
    private static final float STARTING_ROTATION = PI/2.5f;
    private static final int BRANCHES = 50;

    public static void main(String[] args) {
        PApplet.main("fractals.BarnsleyFern");
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        background(0);
        stroke(0,255,0,50);

        translate((float)width/2, height);
        scale(1,-1);
        drawFern(STARTING_LENGTH, DEPTH, -1, STARTING_ROTATION, BRANCHES);
    }

    private void drawFern(float length, int depth, int rotationType, float rotation, int branches) {
        if(depth > 0) {
            strokeWeight(map(depth, DEPTH, 1, 3, 0.1f));

            float currentLength = length;
            float currentRotation = rotation;

            for(int i = 0; i < branches; i++) {
                line(0, 0, 0, currentLength);
                translate(0, currentLength);

                push();
                if(i % 2 == 0) {
                    rotate(currentRotation);
                    drawFern(currentLength * 0.4f, depth - 1, -1, rotation * 0.6f, (int)(branches * 0.7f));
                } else {
                    rotate(-currentRotation);
                    drawFern(currentLength * 0.4f, depth - 1, 1, rotation * 0.6f, (int)(branches * 0.7f));
                }
                pop();

                currentLength *= 0.925f;
                currentRotation *= 0.975f;

                rotate(rotationType * PI/100);
            }
        }
    }
}
