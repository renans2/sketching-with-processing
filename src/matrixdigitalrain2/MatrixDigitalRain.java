package matrixdigitalrain2;

import processing.core.PApplet;

public class MatrixDigitalRain extends PApplet {
    private static final int FONT_SIZE = 20;
    private static final float WIDTH = 1200;
    private static final float HEIGHT = 600;
    private static final float SIDE_LENGTH = FONT_SIZE;
    private static final float N_HORIZONTAL = WIDTH / SIDE_LENGTH;
    public static final float N_VERTICAL = HEIGHT / SIDE_LENGTH;
    private final Cell[][] cells = new Cell[(int)N_HORIZONTAL][(int)N_VERTICAL];
    private static final int MIN_REVEALER_LENGTH = 3;
    private static final int MAX_REVEALER_LENGTH = 13;
    public static final int MIN_REVEALER_SPEED = 1;
    public static final int MAX_REVEALER_SPEED = 15;
    private final Revealer[][] revealers = new Revealer[(int)N_HORIZONTAL][2];

    public static void main(String[] args) {
        PApplet.main("matrixdigitalrain2.MatrixDigitalRain");
    }

    public void settings() {
        size((int)WIDTH, (int)HEIGHT);
    }

    public void setup() {
        background(0);
        noStroke();
        textAlign(LEFT, TOP);
        textFont(createFont("Noto Serif JP Regular", FONT_SIZE));
        textLeading(20);

        for(int i = 0; i < N_HORIZONTAL; i++) {
            revealers[i][0] = createRevealer();

            for(int j = 0; j < N_VERTICAL; j++) {
                cells[i][j] = new Cell(this, i * SIDE_LENGTH, j * SIDE_LENGTH);
            }
        }
    }

    public void draw() {
        fill(0,0,0,20);
        rect(0,0,width,height);

        for(int i = 0; i < N_HORIZONTAL; i++) {
            Revealer r1 = revealers[i][0];

            if(r1 != null) {
                for (int pos : r1.getRevealed()){
                    cells[i][pos].draw(70);
                }

                if(!r1.getRevealed().isEmpty()) {
                    cells[i][r1.getRevealed().getLast()].draw(255);
                }

                r1.update();
                if(r1.isOut())
                    revealers[i][0] = null;
                else if(r1.hitBottom() && revealers[i][1] == null){
                    revealers[i][1] = createRevealer();
                }
            }

            Revealer r2 = revealers[i][1];
            if(r2 != null) {
                for (int pos : r2.getRevealed()){
                    cells[i][pos].draw(70);
                }

                if(!r2.getRevealed().isEmpty()) {
                    cells[i][r2.getRevealed().getLast()].draw(255);
                }

                r2.update();
                if(r2.isOut())
                    revealers[i][1] = null;
                else if(r2.hitBottom() && revealers[i][0] == null){
                    revealers[i][0] = createRevealer();
                }
            }
        }
    }

    private Revealer createRevealer() {
        int length = (int)random(MIN_REVEALER_LENGTH, MAX_REVEALER_LENGTH);
        int speed  = (int)random(MIN_REVEALER_SPEED, MAX_REVEALER_SPEED);
        return new Revealer(this, length, speed);
    }
}
