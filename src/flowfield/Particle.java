package flowfield;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class Particle {
    private final PApplet p;
    private PVector pos;
    private final float hue;

    public Particle(PApplet parent, float x, float y){
        p = parent;
        pos = new PVector(x, y);
        hue = PApplet.map(x, 0, p.width, 0, 360);
    }

    public void move(PVector v) {
        pos = PVector.add(pos, v);
    }

    public void draw() {
//        p.fill(hue, 100, 100, 100);
        p.stroke(hue, 100, 100, 3);
        p.point(pos.x, pos.y);
    }

    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }

    public boolean isOutOfScreen() {
        return pos.x < 0 || pos.y < 0 || pos.y > p.height;
    }
}
