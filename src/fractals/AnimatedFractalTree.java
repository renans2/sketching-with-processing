package fractals;

import processing.core.PApplet;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class AnimatedFractalTree extends PApplet {
    private static final float WIDTH = 1200;
    private static final float HEIGHT = 800;
    private static final int FRACTAL_DEPTH = 10;
    private static final float DEPTH_LENGTH_MULTIPLIER = 0.13f;
    private static final float BRANCH_LENGTH = 200;
    private static final float BRANCH_ANGLE = 1.3f;
    private static final float NOISE_SCALE = 0.005f;

    public static void main(String[] args) {
        PApplet.main("fractals.AnimatedFractalTree");
    }

    public void settings() {
        size((int)WIDTH, (int)HEIGHT);
    }

    public void setup() {
        background(0);
        stroke(255);
        colorMode(HSB,360,100,100,100);
    }

    public void draw() {
        background(0);
        translate(WIDTH/2, HEIGHT);
        scale(1,-1);
        drawBranch(FRACTAL_DEPTH);
    }

    private void drawBranch(int depth) {
        if(depth > 0){
            float brightness = map(depth, FRACTAL_DEPTH, 1, 100, 40);
            stroke(120, 100, brightness, 100);
            strokeWeight(map(depth, FRACTAL_DEPTH, 1, 10, 0.5f));

            float length = noise((depth * 10000) + frameCount * NOISE_SCALE) * BRANCH_LENGTH;
            float lengthAdjusted = length * (DEPTH_LENGTH_MULTIPLIER * depth);
            line(0, 0, 0, lengthAdjusted);
            translate(0, lengthAdjusted);

            float angleStrength = map(depth, FRACTAL_DEPTH, 1, BRANCH_ANGLE/2, BRANCH_ANGLE);

            push();
            float angle = noise((depth * 10000) + frameCount * NOISE_SCALE) * angleStrength;
            rotate(angle);
            drawBranch(depth - 1);
            pop();

            push();
            angle = noise((depth * 20000) + frameCount * NOISE_SCALE) * angleStrength;
            rotate(-angle);
            drawBranch(depth - 1);
            pop();
        }
    }
}
