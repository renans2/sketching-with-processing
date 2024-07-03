package snowflake;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.List;

public class SnowParticle {
    public static final int DIAMETER = 5;
    private final PApplet p;
    private final PVector pos;
    private final float xSpeed;
    private final float yOffset;
    private boolean isFrozen = false;

    public SnowParticle(PApplet parent, float x, float y, float yOffset, float xSpeed){
        p = parent;
        pos = new PVector(x, y);
        this.yOffset = yOffset;
        this.xSpeed = xSpeed;
    }

    public void update(){
        move();

        if(pos.x < 0)
            isFrozen = true;
    }

    public void checkIfCollides(List<SnowParticle> particles){
        for (int i = particles.size() - 1; i >= 0; i--) {
            SnowParticle particle = particles.get(i);

            if(PVector.dist(pos, particle.pos) < DIAMETER){
                isFrozen = true;
                break;
            }
        }
    }

    public boolean isFrozen(){
        return isFrozen;
    }

    private void move(){
        pos.x -= xSpeed;
        pos.y += p.random(-yOffset, yOffset);
    }

    public void draw(){
        float hue = PApplet.map(pos.x, 0, Snowflake.MAX_RADIUS, 150, 250);
        p.fill(hue, 100, 100, 100);
        p.circle(pos.x, pos.y, DIAMETER);
    }

    public float getX(){
        return pos.x;
    }
}
