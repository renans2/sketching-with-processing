package matrixdigitalrain1;

import processing.core.PApplet;
import processing.core.PVector;

public class Section {
    private final PApplet parent;
    private final PVector pos;
    private final int speed;
    private String text;

    private static final int TIME_TO_CHANGE = 100;
    private int previousTime = 0;
    private int timer = 0;

    public Section(PApplet parent, int x, int speed, int textLength) {
        this.parent = parent;
        this.speed = speed;
        int height = 20 * textLength;
        this.pos = new PVector(x, -height);
        setText(textLength);
    }

    public void draw(){
        int currentTime = parent.millis();
        timer += currentTime - previousTime;
        previousTime = currentTime;

        if(timer > TIME_TO_CHANGE){
            timer = 0;
            changeText();
        }

        parent.text(jumpLines(), pos.x, pos.y);
    }

    public void moveDown(){
        pos.y += speed;
    }

    public int getY(){
        return (int)pos.y;
    }

    public int getTextLength(){
        return text.length();
    }

    private void setText(int length){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; i++){
            int randIndex = (int)parent.random(MatrixDigitalRain.CHARACTERS.length());
            sb.append(MatrixDigitalRain.CHARACTERS.charAt(randIndex));
        }

        text = sb.toString();
    }

    private void changeText(){
        StringBuilder sb = new StringBuilder();
        int index = (int)parent.random(text.length());

        for(int i = 0; i < text.length(); i++){
            if(i == index){
                index = (int)parent.random(MatrixDigitalRain.CHARACTERS.length());
                sb.append(MatrixDigitalRain.CHARACTERS.charAt(index));
            } else {
                sb.append(text.charAt(i));
            }
        }

        text = sb.toString();
    }

    private String jumpLines(){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < text.length(); i++){
            sb.append(text.charAt(i));
            sb.append("\n");
        }

        return sb.toString();
    }
}
