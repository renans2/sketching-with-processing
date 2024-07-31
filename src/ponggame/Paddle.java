package ponggame;

import processing.core.PApplet;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class Paddle {
    private final PApplet p;
    private final int height;
    private final int width;
    private final int speed;
    private final int x;
    private int y;

    public Paddle(PApplet parent, int height, int width, int speed, String side) {
        p = parent;
        this.height = height;
        this.width = width;
        this.speed = speed;

        if(side.equals("left"))
            x = 0;
        else
            x = p.width - this.width;

        y = p.height/2 - this.height/2;
    }

    public void update(boolean upKey, boolean downKey) {
        if(upKey)
            moveUp();
        if(downKey)
            moveDown();
    }

    public void draw() {
        p.rect(x, y, width, height);
    }

    private void moveUp() {
        y -= speed;
        y = PApplet.constrain(y, 0, p.height - this.height);
    }

    private void moveDown() {
        y += speed;
        y = PApplet.constrain(y, 0, p.height - this.height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void reset() {
        y = p.height/2 - this.height/2;
    }
}
