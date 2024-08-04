package stackgame;

import processing.core.PApplet;
import processing.core.PVector;
import static stackgame.StackGame.WIDTH;
import static stackgame.StackGame.LAYER_OFFSET_THRESHOLD;

public class Layer {
    private static final int LEFT = -1;
    private static final int RIGHT = 1;
    private final PApplet p;
    private float width;
    private final float height;
    private float speed;
    private final PVector pos;

    public Layer(PApplet parent,
                 float width,
                 float height,
                 int spawnSide,
                 float speed,
                 float y) {
        p = parent;
        this.width = width;
        this.height = height;

        if(spawnSide == LEFT) {
            pos = new PVector(-width, y);
            this.speed = speed;
        } else {
            pos = new PVector(WIDTH, y);
            this.speed = -speed;
        }
    }

    public Layer(PApplet parent,
                 float width,
                 float height,
                 float y) {
        p = parent;
        this.width = width;
        this.height = height;
        pos = new PVector((float)WIDTH/2 - width/2, y);
    }

    public void move() {
        pos.x += speed;

        if(speed < 0 && pos.x < 0)
            speed *= -1;

        else if(speed > 0 && pos.x + width > WIDTH)
            speed *= -1;
    }

    public void draw() {
        p.rect(pos.x, pos.y, width, height);
    }

    public float getY() {
        return pos.y;
    }

    public float stackAndGetNewWidth(Layer prevLayer) {
        float newWidth;
        float offset = Math.abs(pos.x - prevLayer.pos.x);

        if(offset < LAYER_OFFSET_THRESHOLD) {
            newWidth = width;
            pos.x = prevLayer.pos.x;
        } else {
            newWidth = PApplet.constrain(width - Math.abs(pos.x - prevLayer.pos.x), 0, WIDTH);
            width = newWidth;

            if(pos.x < prevLayer.pos.x)
                pos.x = prevLayer.pos.x;
        }

        return newWidth;
    }
}
