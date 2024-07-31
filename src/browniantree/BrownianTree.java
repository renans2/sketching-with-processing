package browniantree;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class BrownianTree extends PApplet {
    public static final float WIDTH = 800;
    public static final float HEIGHT = 800;
    private static final int PARTICLES = 8000;
    private static final float DIAMETER = 4;
    private final List<Particle> frozenParticles = new ArrayList<>();
    private final List<Particle> movingParticles = new ArrayList<>();

    public static void main(String[] args) {
        PApplet.main("browniantree.BrownianTree");
    }

    public void settings() {
        size((int)WIDTH, (int)HEIGHT);
    }

    public void setup() {
        background(0);
        noStroke();

//        Particle initialParticle = new Particle(this, WIDTH/2, HEIGHT/2, DIAMETER);
//        frozenParticles.add(initialParticle);
//        initialParticle.draw();

        for (float i = 0; i < TWO_PI; i += 0.01f) {
            Particle p = new Particle(this, (cos(i) * 300) + WIDTH/2, (sin(i) * 300) + HEIGHT/2, DIAMETER);
            frozenParticles.add(p);
            p.draw();
        }

        for (int i = 0; i < PARTICLES; i++) {
            movingParticles.add(createRandomParticle());
        }
    }

    public void draw() {
        for (int i = movingParticles.size() - 1; i >= 0 ; i--) {
            Particle current = movingParticles.get(i);
            current.move();
            if(current.checkIfCollides(frozenParticles)) {
                movingParticles.remove(current);
                frozenParticles.add(current);
                current.draw();
            }
        }
    }

    private Particle createRandomParticle() {
        return new Particle(this, random(200,WIDTH-200), random(200,HEIGHT-200), DIAMETER);
    }
}
