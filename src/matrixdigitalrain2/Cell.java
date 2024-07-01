package matrixdigitalrain2;

import processing.core.PApplet;
import processing.core.PVector;

public class Cell {
    private static final String CHARACTERS = "アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final PApplet p;
    private final PVector pos;
    private char symbol;
    private static final int MIN_DELAY = 800;
    private static final int MAX_DELAY = 3000;
    private int changeDelay;
    private int previousTime = 0;
    private int timer = 0;

    public Cell(PApplet parent, float x, float y){
        p = parent;
        pos = new PVector(x, y);
        setNewSymbol();
        setNewChangeDelay();
    }

    public void draw(int extraColor) {
        int currentTime = p.millis();
        timer += currentTime - previousTime;
        previousTime = currentTime;

        if(timer > changeDelay) {
            setNewSymbol();
            setNewChangeDelay();
            timer = 0;
        }

        p.fill(255,extraColor,extraColor);
        p.text(symbol, pos.x, pos.y);
    }

    private void setNewSymbol(){
        symbol = getNewSymbol();
    }

    private char getNewSymbol(){
        int i = (int)p.random(CHARACTERS.length());
        return CHARACTERS.charAt(i);
    }

    private void setNewChangeDelay(){
        changeDelay = (int)p.random(MIN_DELAY, MAX_DELAY);
    }
}
