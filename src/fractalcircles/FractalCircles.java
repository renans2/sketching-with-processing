package fractalcircles;

import processing.core.PApplet;

public class FractalCircles extends PApplet {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final float FIRST_CIRCLE_DIAMETER = 0.95f * WIDTH;
    private static final int DEPTH = 8;
    private static final float ANGLE_INC = 0.01f;
    private float angle = 0;

    public static void main(String[] args) {
        PApplet.main("fractalcircles.FractalCircles");
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        background(0);
        stroke(0);
    }

    public void draw() {
        background(255);
        translate((float)width/2, (float)height/2);
        angle = (angle + ANGLE_INC) % TWO_PI;
        rotate(angle);

        drawFracCircles(0, 0, FIRST_CIRCLE_DIAMETER, DEPTH, 40, angle);
    }

    private void drawFracCircles(float x, float y, float diameter, int depth, float alpha, float angle) {
        if(depth > 0) {
            fill(0, alpha);
            circle(x, y, diameter);

            push();
            translate(-diameter/4, 0);
            rotate(angle*2f);
            drawFracCircles(x, y, diameter/2, depth - 1, 1.2f * alpha, -angle*2f);
            pop();

            push();
            translate(diameter/4, 0);
            rotate(angle*2f);
            drawFracCircles(x, y, diameter/2, depth - 1, 1.2f * alpha, -angle*2f);
            pop();
        }
    }
}
