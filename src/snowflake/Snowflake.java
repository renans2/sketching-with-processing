package snowflake;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Snowflake extends PApplet {
    public static final float WIDTH_HEIGHT = 800;
    public static final float MAX_RADIUS = 0.5f * WIDTH_HEIGHT;
    private static final int BRANCHES = 6;
    private static final float ANGLE_OFFSET = TWO_PI / BRANCHES;
    private static final float MAX_OFFSET = 5f;
    private static final float MIN_OFFSET = 1f;
    private static final float MIN_SPEED = 0.5f;
    private static final float MAX_SPEED = 1f;
    private final List<SnowParticle> particles = new ArrayList<>();


    public static void main(String[] args) {
        PApplet.main("snowflake.Snowflake");
    }

    public void settings() {
        size((int)WIDTH_HEIGHT, (int)WIDTH_HEIGHT);
    }

    public void setup() {
        background(0);
        colorMode(HSB, 360, 100, 100, 100);
        noFill();
    }

    public void draw() {
        stroke(255);
        strokeWeight(0.5f);
        translate(WIDTH_HEIGHT/2, WIDTH_HEIGHT/2);
        rotate(-PI/2);

        SnowParticle current = new SnowParticle(this, WIDTH_HEIGHT, 0, random(MIN_OFFSET, MAX_OFFSET), random(MIN_SPEED, MAX_SPEED));

        while(!current.isFrozen()){
            current.update();
            current.checkIfCollides(particles);
        }

        if(current.getX() > MAX_RADIUS){
            noLoop();
        } else {
            particles.add(current);

            for(int i = 0; i < BRANCHES; i++){
                current.draw();
                push();
                scale(1, -1);
                current.draw();
                pop();
                rotate(ANGLE_OFFSET);
            }
        }
    }

    private void reset() {
        particles.clear();
        background(0);
    }

    public void mousePressed() {
        System.out.println(frameRate + " " + particles.size());
    }

//    public void keyPressed() {
//        if (key == 'c' || key == 'C') {
//            saveFrame("/home/renan/Pictures/processing-captures/snowflake/snowflake-###.png");
//        }
//    }
}
