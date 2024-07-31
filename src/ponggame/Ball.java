package ponggame;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class Ball {
    private final PApplet p;
    private final float diameter;
    private final PVector pos;
    private final PVector xSpeed;
    private final PVector ySpeed = new PVector(0, 0);

    public Ball(PApplet parent, int diameter, int xSpeed) {
        p = parent;
        this.diameter = diameter;
        pos = new PVector((float)p.width/2, (float)p.height/2);
        this.xSpeed = new PVector(xSpeed, 0);
    }

    public void update() {
        pos.add(xSpeed);
        pos.add(ySpeed);
    }

    public void draw() {
        p.circle(pos.x, pos.y, diameter);
    }

    public float getX(){
        return pos.x;
    }

    public float getY(){
        return pos.y;
    }

    public float getRadius(){
        return diameter/2;
    }

    public boolean isGoingDown() {
        return ySpeed.y > 0;
    }

    public boolean isGoingRight() {
        return xSpeed.x > 0;
    }

    public void reflectX() {
        xSpeed.x *= -1;
    }

    public void reflectY() {
        ySpeed.y *= -1;
    }

    public void setNewYSpeed(float yVal) {
        ySpeed.y = yVal;
    }

    public void reset(String newDirection){
        if(newDirection.equals("left"))
            pos.x = (float)p.width * 0.90f;
        else
            pos.x = (float)p.width * 0.10f;

        pos.y = (float)p.height/2;
        ySpeed.y = 0;
        reflectX();
    }
}
