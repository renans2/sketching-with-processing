package avoidingwalkers;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.List;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class Walker {
    private final PApplet p;
    private final Board b;
    private PVector previousPos;
    private PVector pos;
    private boolean canWalk;
    private final int color;

    public Walker(PApplet parent, Board board, PVector pos) {
        p = parent;
        b = board;
        this.previousPos = pos;
        this.pos = pos;
        canWalk = true;
        color = p.color((int)p.random(360),100,100,100);
    }

    public void walk() {
        List<PVector> possibleMoves = b.getPossibleMoves(pos);

        if (possibleMoves.isEmpty())
            canWalk = false;
        else {
            PVector nextPos;
            int idx = (int)p.random(possibleMoves.size());
            nextPos = possibleMoves.get(idx);
            previousPos = pos;
            pos = nextPos.copy();
            b.positionTaken(nextPos);
        }
    }

    public void draw() {
        p.stroke(color);
        p.line(previousPos.x * Sketch.OFFSET + Sketch.OFFSET / 2, previousPos.y * Sketch.OFFSET + Sketch.OFFSET / 2,
               pos.x * Sketch.OFFSET + Sketch.OFFSET / 2, pos.y * Sketch.OFFSET + Sketch.OFFSET / 2);
    }

    public boolean canWalk(){
        return canWalk;
    }
}
