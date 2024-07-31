package sortingalgorithms;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class Sketch extends PApplet {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int AMOUNT = 75;
    private static final float X_OFFSET = (float)WIDTH/AMOUNT;
    private static final float Y_OFFSET = (float)HEIGHT/AMOUNT;
    private int[] array = new int[AMOUNT];
    private Sorter insertionSort;
    private Sorter bubbleSorter;
    private Sorter selectionSorter;

    public static void main(String[] args) {
        PApplet.main("sortingalgorithms.Sketch");
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        background(0);
        fill(255);

        array = getShuffledArray(array.length);
        insertionSort = new InsertionSorter(array);
        bubbleSorter = new BubbleSorter(array);
        selectionSorter = new SelectionSorter(array);
    }

    public void draw() {
        background(0);

        int i = 0;
        for (int elem : insertionSort.getCurrentArray()) {
            if(i == insertionSort.getCurrentIndex())
                fill(0,255,0);
            else if(i == insertionSort.getBorderIndex())
                fill(255,0,0);
            else
                fill(255);

            float h = elem * Y_OFFSET/3;
            rect(i * X_OFFSET, (float)height/3 - h, X_OFFSET, h);
            i++;
        }

        if(!insertionSort.isSorted())
            insertionSort.sortFrame();





        i = 0;
        for (int elem : bubbleSorter.getCurrentArray()) {
            if(i == bubbleSorter.getCurrentIndex())
                fill(0,255,0);
            else if(i == bubbleSorter.getBorderIndex())
                fill(255,0,0);
            else
                fill(255);

            float h = elem * Y_OFFSET/3;
            rect(i * X_OFFSET, (float)height * 2f/3 - h, X_OFFSET, h);
            i++;
        }

        if(!bubbleSorter.isSorted())
            bubbleSorter.sortFrame();





        i = 0;
        for (int elem : selectionSorter.getCurrentArray()) {
            if(i == selectionSorter.getCurrentIndex())
                fill(0,255,0);
            else if(i == selectionSorter.getBorderIndex())
                fill(255,0,0);
            else
                fill(255);

            float h = elem * Y_OFFSET/3;
            rect(i * X_OFFSET, height - h, X_OFFSET, h);
            i++;
        }

        if(!selectionSorter.isSorted())
            selectionSorter.sortFrame();
    }

    private int[] getShuffledArray(int length) {
        int[] result = new int[length];
        List<Integer> values = new ArrayList<>();

        for (int i = 1; i <= length; i++)
            values.add(i);

        for (int i = 0; i < length; i++) {
            Integer val = values.get((int)(Math.random() * values.size()));
            values.remove(val);
            result[i] = val;
        }

        return result;
    }
}
