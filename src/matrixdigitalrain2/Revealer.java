package matrixdigitalrain2;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Revealer {
    private static final int MAX_DELAY = 200;
    private static final int MIN_DELAY = 20;
    private final PApplet p;
    private final int length;
    private final int delay;
    private int pos;
    private int timer = 0;
    private int previousTime = 0;

    public Revealer(PApplet parent, int length, int speed) {
        p = parent;
        this.length = length;
        delay = delayFromSpeed(speed);
        pos = -this.length;
    }

    private int delayFromSpeed(int speed) {
        return (int)PApplet.map(speed,
                                MatrixDigitalRain.MIN_REVEALER_SPEED,
                                MatrixDigitalRain.MAX_REVEALER_SPEED,
                                MAX_DELAY,
                                MIN_DELAY);
    }

    public boolean hitBottom() {
        return pos + length > MatrixDigitalRain.N_VERTICAL - 1;
    }

    public boolean isOut() {
        return pos > MatrixDigitalRain.N_VERTICAL - 1;
    }

    public void update() {
        int currentTime = p.millis();
        timer += currentTime - previousTime;
        previousTime = currentTime;

        if(timer > delay) {
            timer = 0;
            move();
        }
    }

    public void move() {
        pos++;
    }

    public List<Integer> getRevealed(){
        List<Integer> l = new ArrayList<>();

        if(pos >= 0) {
            int size;

            if(pos + length < (int)MatrixDigitalRain.N_VERTICAL)
                size = length;
            else
                size = (int)MatrixDigitalRain.N_VERTICAL - pos;

            for(int i = 0; i < size; i++)
                l.add(pos + i);
        } else {
            int size = pos + length;
            for(int i = 0; i < size; i++)
                l.add(i);
        }

        return l;
    }
}
