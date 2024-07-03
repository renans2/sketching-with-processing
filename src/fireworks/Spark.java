package fireworks;

import processing.core.PApplet;
import processing.core.PVector;

public class Spark {
    private static final float MIN_DIAMETER = 2;
    private static final float MAX_DIAMETER = 3;
    private static final float MIN_SPEED = 2f;
    private static final float MAX_SPEED = 2.15f;
    private static final float GRAVITY_STRENGTH = 0.02f;
    private static final PVector GRAVITY = new PVector(0, GRAVITY_STRENGTH);
    private static final float VELOCITY_ROTATION_OFFSET = 0.040f;
    private final PApplet p;
    private PVector pos;
    private final float angle;
    private final float speed;
    private PVector velocity;
    private final float diameter;

    public Spark(PApplet parent, float centerX, float centerY, float angle) {
        p = parent;
        pos = new PVector(centerX, centerY);
        this.angle = angle;
        speed = p.random(MIN_SPEED, MAX_SPEED);
        velocity = PVector.fromAngle(this.angle).mult(this.speed);
        diameter = p.random(MIN_DIAMETER, MAX_DIAMETER);
    }

    public void show(float hue, float alpha) {
        move();
        draw(hue, alpha);
    }

    public void move() {
        velocity.rotate(p.random(-VELOCITY_ROTATION_OFFSET, VELOCITY_ROTATION_OFFSET));
        velocity = PVector.add(GRAVITY, velocity);
        pos = PVector.add(pos, velocity);
    }

    public void draw(float hue, float alpha){
        p.push();
        p.fill(hue,100,100,alpha);
        p.circle(pos.x, pos.y, diameter);
        p.pop();
    }
}
