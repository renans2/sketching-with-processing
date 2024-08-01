package breathingeffect;

import processing.core.PApplet;
import processing.core.PVector;
import static processing.core.PApplet.TWO_PI;
import static breathingeffect.BreathingEffect.MIN_DIAMETER;
import static breathingeffect.BreathingEffect.MAX_DIAMETER;
import static breathingeffect.BreathingEffect.MIN_OSC_MULTIPLIER;
import static breathingeffect.BreathingEffect.MAX_OSC_MULTIPLIER;
import static breathingeffect.BreathingEffect.ANGLE_INCREMENTER;
import static processing.core.PApplet.map;
import static processing.core.PApplet.sin;

public class Unit {
    private final PApplet p;
    private final PVector startingPos;
    private final PVector pointingVec;
    private float angle;

    public Unit(PApplet parent, PVector pos, float angle) {
        p = parent;
        startingPos = pos.copy();
        this.angle = angle;
        pointingVec = new PVector(-pos.x, -pos.y);
    }

    public void draw() {
        angle = (angle + ANGLE_INCREMENTER) % TWO_PI;
        float sinAngle = sin(angle);

        float multiplier = map(sinAngle, -1, 1, MAX_OSC_MULTIPLIER, MIN_OSC_MULTIPLIER);
        PVector offsetVec = pointingVec.copy().mult(multiplier);
        PVector newPos = PVector.add(startingPos, offsetVec);

        p.circle(newPos.x, newPos.y, map(sinAngle, -1, 1, MIN_DIAMETER, MAX_DIAMETER));
    }
}
