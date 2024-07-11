package avoidingwalkers;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Sketch extends PApplet {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final int OFFSET = 10;
    private static final float STROKE_WEIGHT_MULTIPLIER = 0.5f;
    private final Board b = new Board(this, WIDTH, HEIGHT, OFFSET);
    private final List<Walker> walkers = new ArrayList<>();
    private static final int N_WALKERS = 500;

    public static void main(String[] args) {
        PApplet.main("avoidingwalkers.Sketch");
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        frameRate(3);
        background(0);
        strokeWeight(OFFSET * STROKE_WEIGHT_MULTIPLIER);
        colorMode(HSB,360,100,100,100);

        for (int i = 0; i < N_WALKERS; i++) {
            Walker w = createRandomWalker();
            w.draw();
            walkers.add(w);
        }
    }

    public void draw() {
        for (int i = walkers.size()-1; i >= 0; i--) {
            Walker w = walkers.get(i);
            if(w.canWalk()){
                w.walk();
                w.draw();
            } else {
                walkers.remove(w);
            }
        }
    }

    private Walker createRandomWalker() {
        return new Walker(this, b, b.getRandomFreeCell());
    }
}
