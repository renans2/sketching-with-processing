package fireworks;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class Sketch extends PApplet {
    private static final float WIDTH = 1000;
    private static final float HEIGHT = 800;
    private final List<Firework> fireworks = new ArrayList<Firework>();

    public static void main(String[] args) {
        PApplet.main("fireworks.Sketch");
    }

    public void settings() {
        size((int)WIDTH, (int)HEIGHT);
    }

    public void setup() {
        background(0);
        colorMode(HSB, 360, 100, 100, 100);
        noStroke();
    }

    public void draw() {
        if(frameCount % 7 == 0)
            fireworks.add(new Firework(this, random(WIDTH), random(HEIGHT)));

        fill(0, 5);
        rect(0, 0, WIDTH, HEIGHT);

        for (int i = fireworks.size() - 1; i >= 0; i--){
            Firework f = fireworks.get(i);
            f.show();
            if(f.isOver()){
                fireworks.remove(i);
            }
        }
    }

    public void mousePressed() {
        fireworks.add(new Firework(this, mouseX, mouseY));
    }

    public void keyPressed() {
        background(0);
        fireworks.clear();
    }
}
