package flowfield;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class FlowField extends PApplet {
    private static final float WIDTH = 800;
    private static final float HEIGHT = 800;
    private static final float NOISE_SCALE = 0.0035f;
    private final List<Particle> particles = new ArrayList<Particle>();
    private static final int PARTICLES_PER_FRAME = 30;

    public static void main(String[] args) {
        PApplet.main("flowfield.FlowField");
    }

    public void settings() {
        size((int)WIDTH, (int)HEIGHT);
    }

    public void setup() {
        background(0);
        colorMode(HSB, 360, 100, 100, 100);
        noStroke();
    }

    public void draw() {
//        background(0);
//        fill(0,0,0,25);
//        rect(0, 0, WIDTH, HEIGHT);
        addParticles();
        updateParticles();
//        System.out.println((int)frameRate + " " + particles.size());
    }

    private PVector getVector(float x, float y) {
        float noise = noise(x * NOISE_SCALE, y * NOISE_SCALE);
        float angle = noise * TWO_PI;
        float vX = cos(angle);
        float vY = sin(angle);

        return new PVector(vX, vY).normalize().mult(1);
    }

    private void addParticles() {
        for (int i = 0; i < PARTICLES_PER_FRAME; i++)
            particles.add(randomParticle());
    }

    private void updateParticles() {
        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle p = particles.get(i);
            p.move(getVector(p.getX(), p.getY()));

            if(p.isOutOfScreen())
                particles.remove(p);
            else
                p.draw();
        }
    }

    private Particle randomParticle() {
        return new Particle(this, random(width), random(height));
    }
}
