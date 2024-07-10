package asciivideo;

import processing.core.PApplet;
import processing.video.Movie;

public class AsciiVideo extends PApplet {
    private static final int WIDTH = 480;
    private static final int HEIGHT = 854;
    private static final int OFFSET = 10;
    private static final float PIXELS_BY_BLOCK = OFFSET * OFFSET;
    private static final float N_WIDTH  = WIDTH  / OFFSET;
    private static final float N_HEIGHT = HEIGHT / OFFSET;
    private static final String ASCII_CHARS = "Ñ@#W$9876543210?!abc;:+=-,._ ";
    private Movie vid;

    public static void main(String[] args) {
        PApplet.main("asciivideo.AsciiVideo");
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        frameRate(30);
        background(0);
        noStroke();
        textSize(OFFSET * 1.35f);
        textAlign(CENTER, CENTER);
        vid = new Movie(this, "./src/asciivideo/video.mp4");
        vid.play();
    }

    public void draw() {
        if(vid.available()){
            background(0);
            vid.read();
            vid.loadPixels();
            processAsciiChars();
        }
    }

    private void processAsciiChars() {
        for (int i = 0; i < N_WIDTH; i++) {
            for (int j = 0; j < N_HEIGHT; j++) {
                float blockBrightness = processBlock(i * OFFSET, j * OFFSET);
                int index = (int)map(blockBrightness, 255, 0, 0, ASCII_CHARS.length() - 1);
                char asciiChar = ASCII_CHARS.charAt(index);
                text(asciiChar, i * OFFSET + OFFSET / 2, j * OFFSET + OFFSET / 2);
            }
        }
    }

    private float processBlock(int x, int y) {
        float totalBrightness = 0;

        for (int i = x; i < x + OFFSET; i++) {
            for (int j = y; j < y + OFFSET; j++) {
                int index = i + j * WIDTH;
                totalBrightness += brightness(vid.pixels[index]);
            }
        }

        return totalBrightness / PIXELS_BY_BLOCK;
    }
}
