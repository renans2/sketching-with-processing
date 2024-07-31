package kaleidoscopefractaltrees;

import processing.core.PApplet;
import processing.event.MouseEvent;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class KaleidoscopeFractalTrees extends PApplet {
    private static final float WIDTH = 800;
    private static final float HEIGHT = 800;
    private static final int ROTATIONS = 10;
    private float wheelAccumulator = 0.5f;
    private int depth = 3;
    private int rotations = 3;
    private float angleOffset = TWO_PI/rotations;

    public static void main(String[] args) {
        PApplet.main("kaleidoscopefractaltrees.KaleidoscopeFractalTrees");
    }

    public void settings() {
        size((int)WIDTH, (int)HEIGHT);
    }

    public void setup() {
        background(0);
        stroke(255);
        colorMode(HSB,360,100,100,100);
    }

    public void draw() {
        background(0);
        translate(WIDTH/2, HEIGHT/2);

        for (int i = 0; i < rotations; i++) {
            drawBranch(depth, 100, map(mouseX, 0, WIDTH, 0, 6f));
            rotate(angleOffset);
        }
    }

    private void drawBranch(int depth, float length, float angle) {
        if(depth > 0){
            stroke(map(depth, this.depth, 1, 0, 360),100,100,100);
            strokeWeight(map(depth, this.depth, 1, 1.5f, 0.3f));
            line(0, 0, 0, -length);
            
            push();
            translate(0, -length);
            float lengthMultiplier = map(mouseY, HEIGHT, 0, 0, 3);

            push();
            rotate(angle);
            drawBranch(depth - 1, length * lengthMultiplier, angle * wheelAccumulator);
            pop();

            push();
            rotate(-angle);
            drawBranch(depth - 1, length * lengthMultiplier, angle * wheelAccumulator);
            pop();
            pop();
        }
    }

    public void keyPressed() {
        switch(key) {
            case 'r':
                changeRotations(1);
                break;

            case 'f':
                changeRotations(-1);
                break;

            case 'e':
                changeDepth(1);
                break;

            case 'd':
                changeDepth(-1);

//            case 'c':
//                saveFrame("/home/renan/Pictures/processing-captures/kaleidoscopefractal/kaleidoscopefractal-#######.png");

            default: break;
        }
    }

    public void mouseWheel(MouseEvent event) {
        if(wheelAccumulator + -event.getCount() * 0.1f > 0)
            wheelAccumulator += -event.getCount() * 0.1f;
    }

    private void changeRotations(int val) {
        rotations += val;
        angleOffset = TWO_PI/rotations;
    }

    private void changeDepth(int val) {
        depth += val;
    }
}
