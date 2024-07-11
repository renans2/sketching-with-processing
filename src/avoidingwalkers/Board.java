package avoidingwalkers;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final PApplet p;
    private final int nHorizontalCells;
    private final int nVerticalCells;
    private boolean[][] cells;
    private List<PVector> freeCells;

    public Board(PApplet parent, int width, int height, int offset) {
        p = parent;
        nHorizontalCells = width / offset;
        nVerticalCells = height / offset;
        cells = new boolean[nHorizontalCells][nVerticalCells];
        freeCells = new ArrayList<>();
        setFreeCellsList();
    }

    public void positionTaken(PVector pos) {
        freeCells.remove(pos);
        cells[(int)pos.x][(int)pos.y] = false;
    }

    public List<PVector> getPossibleMoves(PVector currentPos) {
        List<PVector> result = new ArrayList<>();

        int x = (int)currentPos.x;
        int y = (int)currentPos.y;

        if (0 <= x-1 && cells[x-1][y])
            result.add(new PVector(x-1, y));

        if (x+1 < nHorizontalCells && cells[x+1][y])
            result.add(new PVector(x+1, y));

        if (0 <= y-1 && cells[x][y-1])
            result.add(new PVector(x, y-1));

        if (y+1 < nVerticalCells && cells[x][y+1])
            result.add(new PVector(x, y+1));

        return result;
    }

    public PVector getRandomFreeCell() {
        int randIndex = (int)p.random(freeCells.size());
        PVector freeCell = freeCells.get(randIndex).copy();
        freeCells.remove(randIndex);
        cells[(int)freeCell.x][(int)freeCell.y] = false;

        return freeCell;
    }

    private void setFreeCellsList() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = true;
                freeCells.add(new PVector(i, j));
            }
        }
    }
}
