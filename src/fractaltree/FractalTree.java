package fractaltree;

import processing.core.PApplet;
import processing.event.MouseEvent;

public class FractalTree extends PApplet {
    private static final float WIDTH = 800;
    private static final float HEIGHT = 800;
    private static final int FRACTAL_DEPTH = 6;
    private int mouseWheelAcumulator = 7;

    public static void main(String[] args) {
        PApplet.main("fractaltree.FractalTree");
    }

    public void settings() {
        size((int)WIDTH, (int)HEIGHT);
    }

    public void setup() {
        background(0);
        stroke(255);
        strokeWeight(0.1f);
        translate(WIDTH/2, HEIGHT);
        scale(1,-1);
        drawBranch(FRACTAL_DEPTH, 150, 1f);
    }

    public void draw() {
        background(0);
        translate(WIDTH/2, HEIGHT);
        scale(1,-1);
        drawBranch(FRACTAL_DEPTH, 150, mouseWheelAcumulator * 0.1f);

        if(frameCount % 60 == 0) {
            System.out.println(frameRate);
        }
    }

    private void drawBranch(int depth, float length, float angle) {
        if(depth > 0){
            line(0, 0, 0, length);
            translate(0, length);
            float lengthMultiplier = map(mouseY, 0, HEIGHT, 0, 1.25f);;
            float angleMultiplier = map(mouseX, 0, WIDTH, 0, 2.5f);

            push();
            rotate((angle/4) * 2);
            drawBranch(depth - 1, length * lengthMultiplier, angle * angleMultiplier);
            pop();

            push();
            rotate(angle/4);
            drawBranch(depth - 1, length * lengthMultiplier * 1.2f, angle * angleMultiplier);
            pop();

            push();
            drawBranch(depth - 1, length * lengthMultiplier * 1.5f, angle * angleMultiplier);
            pop();

            push();
            rotate(-angle/4);
            drawBranch(depth - 1, length * lengthMultiplier * 1.2f, angle * angleMultiplier);
            pop();

            push();
            rotate(-1 * (angle/4) * 2);
            drawBranch(depth - 1, length * lengthMultiplier, angle * angleMultiplier);
            pop();
        }
    }

    public void mouseWheel(MouseEvent event) {
        mouseWheelAcumulator += event.getCount();
    }
}
