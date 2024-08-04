package stackgame;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class StackGame extends PApplet {
    public static final int WIDTH = 700;
    private static final int HEIGHT = 800;
    private static final float LAYER_HEIGHT = 15;
    private static final float LAYER_STARTING_WIDTH = 400;
    private static final float LAYER_STARTING_SPEED = 10;
    public static final float LAYER_OFFSET_THRESHOLD = 20;
    private List<Layer> layers = new ArrayList<>();
    private int nextSpawnSide = -1;

    public static void main(String[] args) {
        PApplet.main("stackgame.StackGame");
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        layers.add(new Layer(this,LAYER_STARTING_WIDTH,LAYER_HEIGHT,0));
        layers.add(new Layer(this,
                             LAYER_STARTING_WIDTH,
                             LAYER_HEIGHT,
                             nextSpawnSide,
                             LAYER_STARTING_SPEED,
                             LAYER_HEIGHT));
    }

    public void draw() {
        translate(0, height);
        scale(1, -1);
        background(0);

        layers.getLast().move();

        for (Layer layer : layers) {
            layer.draw();
        }
    }

    public void mousePressed() {
        Layer current = layers.getLast();
        Layer lastStacked = layers.get(layers.size()-2);

        float newWidth = current.stackAndGetNewWidth(lastStacked);
        nextSpawnSide *= -1;

        layers.add(new Layer(this,
                             newWidth,
                             LAYER_HEIGHT,
                             nextSpawnSide,
                             LAYER_STARTING_SPEED,
                             current.getY() + LAYER_HEIGHT));
    }
}
