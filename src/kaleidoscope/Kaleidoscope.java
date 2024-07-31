package kaleidoscope;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class Kaleidoscope extends PApplet {
    private static final float WIDTH = 800;
    private static final float HEIGHT = 800;
    private static final int ROTATIONS = 50;
    private static final float ANGLE_OFFSET = TWO_PI / ROTATIONS;
    private final List<PVector> points = new ArrayList<>();
    private float color = 0;
    private static final float COLOR_INCREMENTER = 0.5f;
    private static final float NOISE_SCALE = 0.01f;

    public static void main(String[] args) {
        PApplet.main("kaleidoscope.Kaleidoscope");
    }

    public void settings() {
        size((int)WIDTH, (int)HEIGHT);
    }

    public void setup() {
        background(0);
        stroke(255);
        strokeWeight(0.6f);
        noFill();
        colorMode(HSB, 360, 100, 100, 100);
    }

    public void draw() {
        // Background draw with some transparency
//        push();
//        fill(0,5);
//        noStroke();
//        rect(0, 0, WIDTH, HEIGHT);
//        pop();

        color = (color + COLOR_INCREMENTER) % 360;
        stroke(color, 100, 100, 20);

        translate(WIDTH/2, HEIGHT/2);

        // Random position (Perlin noise)
        float noiseX = noise(frameCount * NOISE_SCALE);
        float noiseY = noise((frameCount + 10000) * NOISE_SCALE);
        float x = noiseX * (1.5f * WIDTH) - WIDTH/2;
        float y = noiseY * (1.5f * HEIGHT) - HEIGHT/2;
        PVector pos = new PVector(x, y);
        points.add(pos);
        if(points.size() > 4) {
            points.removeFirst();
        }

        // Mouse position
//        PVector mousePos = new PVector(mouseX - WIDTH/2, mouseY - HEIGHT/2);
//        points.add(mousePos);
//        if(points.size() > 4) {
//            points.removeFirst();
//        }

        rotate(-PI/2);
        for (int i = 0; i < ROTATIONS; i++) {

            beginShape();
            for(PVector p : points)
                curveVertex(p.x, p.y);
            endShape();

            // Reflection
            push();
            scale(1,-1);
            beginShape();
            for(PVector p : points)
                curveVertex(p.x, p.y);
            endShape();
            pop();

            rotate(ANGLE_OFFSET);
        }

        // Save frames
//        saveFrame("/home/renan/Pictures/processing-captures/rotateddrawing/sketch-#####.png");
    }

//    public void keyPressed() {
//        saveFrame("/home/renan/Pictures/processing-captures/kaleidoscope/sketch-#######.png");
//    }

    public void mousePressed() {
        background(0);
        points.clear();
    }
}
