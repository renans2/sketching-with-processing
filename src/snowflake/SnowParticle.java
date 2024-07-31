package snowflake;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.List;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class SnowParticle {
    public static final int DIAMETER = 4;
    private final PApplet p;
    private final PVector startingPos;
    private PVector pos;
    private final float xSpeed;
    private final float yOffset;
    private boolean isFrozen = false;

    public SnowParticle(PApplet parent, float x, float y, float yOffset, float xSpeed){
        p = parent;
        startingPos = new PVector(x, y);
        pos = new PVector(x, y);
        this.yOffset = yOffset;
        this.xSpeed = xSpeed;
    }

    public void update(){
        move();

        if(pos.x < 0){
            if(pos.y < Snowflake.WIDTH_HEIGHT/5 && -Snowflake.WIDTH_HEIGHT/5 < pos.y)
                isFrozen = true;
            else
                pos = startingPos.copy();
        }
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
        p.circle(pos.x, pos.y, DIAMETER);
    }

    public float getX(){
        return pos.x;
    }
}
