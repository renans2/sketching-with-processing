package halftoneeffect;

import processing.core.PApplet;
import processing.core.PImage;

public class HalftoneEffect extends PApplet {
    private PImage img;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int OFFSET = 5;
    private static final float PIXELS_BY_BLOCK = OFFSET * OFFSET;
    private static final float N_WIDTH  = WIDTH  / OFFSET;
    private static final float N_HEIGHT = HEIGHT / OFFSET;
    private static final float MIN_DIAMETER = 1;
    private static final float MAX_DIAMETER = OFFSET;

    public static void main(String[] args) {
        PApplet.main("halftoneeffect.HalftoneEffect");
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        background(0);
        noStroke();

        img = loadImage("halftoneeffect/bigben.jpg");
        img.loadPixels();
        loadPixels();
        processCircles();
    }

//    public void draw() {
//
//    }

    private void processCircles() {
        for (int i = 0; i < N_WIDTH; i++) {
            for (int j = 0; j < N_HEIGHT; j++) {
                float blockBrightness = processBlock(i * OFFSET, j * OFFSET);
                float circleDiameter = map(blockBrightness, 0, 255, MIN_DIAMETER, MAX_DIAMETER);
                circle(i * OFFSET + OFFSET / 2, j * OFFSET + OFFSET / 2, circleDiameter);
            }
        }
    }

    private float processBlock(int x, int y) {
        float totalBrightness = 0;

        for (int i = x; i < x + OFFSET; i++) {
            for (int j = y; j < y + OFFSET; j++) {
                int index = i + j * WIDTH;
                totalBrightness += brightness(img.pixels[index]);
            }
        }

        return totalBrightness / PIXELS_BY_BLOCK;
    }
}
