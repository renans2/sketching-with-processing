package perlincircles;

import processing.core.PApplet;

public class PerlinCircles extends PApplet {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int CIRCLE_EVERY_X_PIXELS = 10;
    private static final int AMOUNT_WIDTH = WIDTH/CIRCLE_EVERY_X_PIXELS;
    private static final int AMOUNT_HEIGHT = HEIGHT/CIRCLE_EVERY_X_PIXELS;
    private static final float NOISE_SCALE = 0.01f;
    private static final float SPEED = 0.015f;

    public static void main(String[] args) {
        PApplet.main("perlincircles.PerlinCircles");
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        background(0);
        noStroke();
        fill(255);
        colorMode(HSB,360,100,100,100);
    }

    public void draw() {
        background(0);
        noStroke();
        for (int i = 0; i < AMOUNT_HEIGHT; i++) {
            for (int j = 0; j < AMOUNT_WIDTH; j++) {
                float noise = noise(i * CIRCLE_EVERY_X_PIXELS * NOISE_SCALE,
                                    j * CIRCLE_EVERY_X_PIXELS * NOISE_SCALE,
                                    frameCount * SPEED);

                float diameter = map(noise, 0, 1, 0, 1.5f * CIRCLE_EVERY_X_PIXELS);

                fill(noise * 400,100,100,100);

                circle(i * CIRCLE_EVERY_X_PIXELS + (float)CIRCLE_EVERY_X_PIXELS/2,
                       j * CIRCLE_EVERY_X_PIXELS + (float)CIRCLE_EVERY_X_PIXELS/2,
                       diameter);
            }
        }
    }
}
