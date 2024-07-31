package randomfractaltrees;

import processing.core.PApplet;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class RandomFractalTrees extends PApplet {
    private static final float WIDTH = 800;
    private static final float HEIGHT = 800;
    private static final int FRACTAL_DEPTH = 8;
    private static final int MIN_BRANCHES = 1;
    private static final int MAX_BRANCHES = 7;

    public static void main(String[] args) {
        PApplet.main("randomfractaltrees.RandomFractalTrees");
    }

    public void settings() {
        size((int)WIDTH, (int)HEIGHT);
    }

    public void setup() {
        background(0);
        stroke(255);
        strokeWeight(0.7f);

        translate(WIDTH/2, HEIGHT);
        scale(1,-1);
        drawBranch(FRACTAL_DEPTH, 150, 1.5f);
    }

    public void draw() {
        if(frameCount % 60 == 0) {
            System.out.println(frameRate);
        }
    }

    private void drawBranch(int depth, float length, float angle) {
        if(depth > 0){
            stroke(255, map(depth, FRACTAL_DEPTH, 1, 100, 20));
            strokeWeight(map(depth, FRACTAL_DEPTH, 1, 5, 0.5f));
            line(0, 0, 0, length);
            translate(0, length);

            int nBranches = (int)random(MIN_BRANCHES, MAX_BRANCHES);
            float angleOffset = angle/nBranches;
            float halfAngle = nBranches/2 * angleOffset;

            for(float a = -halfAngle;
                      a <= halfAngle;
                      a += angleOffset){
                push();
                rotate(a);
                drawBranch(depth - 1, length * random(0.5f, 1f), angle * random(0.75f, 1f));
                pop();
            }
        }
    }

//    public void keyPressed() {
//        if(key == 'c' || key == 'C') {
//            saveFrame("/home/renan/Pictures/processing-captures/fractaltrees/fractaltree-#######.png");
//        }
//    }

    public void mouseClicked() {
        background(0);
        translate(WIDTH/2, HEIGHT);
        scale(1,-1);
        drawBranch(FRACTAL_DEPTH, 150, 1.5f);
    }
}
