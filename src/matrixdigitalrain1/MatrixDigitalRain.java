package matrixdigitalrain1;

import processing.core.PApplet;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class MatrixDigitalRain extends PApplet {
    public static final String CHARACTERS = "アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int FONT_SIZE = 20;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private static final int N_SECTIONS = 50;
    private static final int SECTION_WIDTH = Math.round((float)WIDTH / N_SECTIONS);
    private static final int MIN_SECTION_CHARS = 4;
    private static final int MAX_SECTION_CHARS = 20;
    private static final int MIN_SECTION_SPEED = 4;
    private static final int MAX_SECTION_SPEED = 8;
    private final Section[][] sections = new Section[N_SECTIONS][2];

    public static void main(String[] args) {
        PApplet.main("matrixdigitalrain1.MatrixDigitalRain");
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        background(0);
        fill(0,255,0);
        noStroke();
        textAlign(LEFT, TOP);
        textFont(createFont("Noto Serif JP Regular", FONT_SIZE));
        textLeading(20);

        for (int i = 0; i < N_SECTIONS; i++) {
           sections[i][0] = createSection(i);
        }
    }

    public void draw() {
        fill(0,0,0,100);
        rect(0, 0, WIDTH, HEIGHT);
        fill(0,255,0);
        for (int i = 0; i < N_SECTIONS; i++) {
            if (sections[i][0] != null) {
                sections[i][0].moveDown();
                sections[i][0].draw();

                if(sections[i][0].getY() + sections[i][0].getTextLength() * 10 > HEIGHT && sections[i][1] == null)
                    sections[i][1] = createSection(i);

                if(sections[i][0].getY() > HEIGHT)
                    sections[i][0] = null;
            }

            if (sections[i][1] != null) {
                sections[i][1].moveDown();
                sections[i][1].draw();

                if(sections[i][1].getY() + sections[i][1].getTextLength() * 10 > HEIGHT && sections[i][0] == null)
                    sections[i][0] = createSection(i);

                if(sections[i][1].getY() > HEIGHT)
                    sections[i][1] = null;
            }
        }

//        System.out.print((int)frameRate + ",");
    }

    private Section createSection(int xOffset) {
        return new Section(this,
                           SECTION_WIDTH * xOffset,
                           randSpeed(),
                           randTextLength());
    }

    private int randSpeed(){
        return (int)random(MIN_SECTION_SPEED, MAX_SECTION_SPEED);
    }

    private int randTextLength(){
        return (int)random(MIN_SECTION_CHARS, MAX_SECTION_CHARS);
    }
}
