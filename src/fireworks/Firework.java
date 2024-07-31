package fireworks;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class Firework {
    private static final int MIN_SPARKS = 20;
    private static final int MAX_SPARKS = 30;
    private static final float MIN_DURATION = 500;
    private static final float MAX_DURATION = 3000;
    private final PApplet p;
    private final PVector pos;
    private final int nSparks;
    private final float angleOffset;
    private final int duration;
    private final float hue;
    private List<Spark> sparks;
    private int previousTime;
    private int timer = 0;

    public Firework(PApplet parent, float centerX, float centerY) {
        p = parent;
        pos = new PVector(centerX, centerY);
        nSparks = (int)p.random(MIN_SPARKS, MAX_SPARKS);
        angleOffset = PApplet.TWO_PI / nSparks;
        duration = (int)p.random(MIN_DURATION, MAX_DURATION);
        hue = p.random(360);
        sparks = new ArrayList<>();
        fillSparksList();
        previousTime = p.millis();
    }

    private void fillSparksList() {
        for (int i = 0; i < nSparks; i++)
            sparks.add(new Spark(p, pos.x, pos.y, i * angleOffset));
    }

    public void show() {
        int currentTime = p.millis();
        timer += currentTime - previousTime;
        previousTime = currentTime;
        float alpha = PApplet.map(timer, 0, duration, 100, 0);
        float variableHue = PApplet.map(timer, 0, duration, (hue - 100) % 360, (hue + 100) % 360);

        for (Spark s : sparks)
            s.show(variableHue, alpha);
    }

    public boolean isOver(){
        return timer >= duration;
    }
}
