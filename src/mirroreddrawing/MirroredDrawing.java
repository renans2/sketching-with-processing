package mirroreddrawing;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.LinkedList;
import java.util.List;

public class MirroredDrawing extends PApplet {
    private static final float WIDTH = 800;
    private static final float HEIGHT = 800;
    private final List<PVector> points = new LinkedList<>();

    public static void main(String[] args) {
        PApplet.main("mirroreddrawing.MirroredDrawing");
    }

    public void settings() {
        size((int)WIDTH, (int)HEIGHT);
    }

    public void setup() {
        background(0);
        noFill();
        stroke(255);
        translate(WIDTH/2, HEIGHT/2);
        drawReferenceLines();
    }

    public void draw() {
        stroke(255);
        translate(WIDTH/2, HEIGHT/2);

        points.add(new PVector(mouseX - WIDTH/2, mouseY - HEIGHT/2));
        if(points.size() > 4) {
            points.removeFirst();
        }

        beginShape();
        for(PVector p : points)
            curveVertex(p.x, p.y);
        endShape();

        drawSection(-1, 1);
        drawSection(1, -1);
        drawSection(-1, -1);
    }

    private void drawSection(int xScale, int yScale) {
        push();
        scale(xScale, yScale);
        beginShape();
        for(PVector p : points)
            curveVertex(p.x, p.y);
        endShape();
        pop();
    }

    private void drawReferenceLines(){
        stroke(255,0,0, 100);
        line(0,0,WIDTH/2,0);
        line(0,0,0,HEIGHT/2);
        line(0,0,-WIDTH/2,0);
        line(0,0,0,-HEIGHT/2);
    }

    public void mousePressed() {
        background(0);
        points.clear();
        drawReferenceLines();
    }
}
