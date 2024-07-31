package browniantree;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.List;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class Particle {
    private final PApplet p;
    private final PVector pos;
    private final float diameter;

    public Particle(PApplet parent, float x, float y, float diameter){
        p = parent;
        pos = new PVector(x, y);
        this.diameter = diameter;
    }

    public void move(){
        pos.add(PVector.random2D().mult(5));
        pos.x = PApplet.constrain(pos.x, 0, BrownianTree.WIDTH);
        pos.y = PApplet.constrain(pos.y, 0, BrownianTree.HEIGHT);
    }

    public void draw(){
        p.circle(pos.x, pos.y, diameter);
    }

    public boolean checkIfCollides(List<Particle> frozenParticles){
        for (int i = frozenParticles.size() - 1; i >= 0; i--) {
            Particle frozenParticle = frozenParticles.get(i);

            if (PVector.dist(pos, frozenParticle.pos) < diameter){
                return true;
            }
        }

        return false;
    }
}
