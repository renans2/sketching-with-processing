package sortingalgorithms;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Sketch extends PApplet {
    private static final int WIDTH = 1500;
    private static final int HEIGHT = 800;
    private static final int AMOUNT = 1000;
    private static final float X_OFFSET = (float)WIDTH/AMOUNT;
    private static final float Y_OFFSET = (float)HEIGHT/AMOUNT;
    private int[] array = new int[AMOUNT];
    private Sorter insertionSort;
    private Sorter bubbleSorter;

    public static void main(String[] args) {
        PApplet.main("sortingalgorithms.Sketch");
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        background(0);
        fill(255);
        frameRate(60);

        array = getShuffledArray(array.length);
        insertionSort = new InsertionSorter(array);
        bubbleSorter = new BubbleSorter(array);
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

            float h = elem * Y_OFFSET/2;
            rect(i * X_OFFSET, height - h, X_OFFSET, h);
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

            float h = elem * Y_OFFSET/2;
            rect(i * X_OFFSET, (float)height/2 - h, X_OFFSET, h);
            i++;
        }

        if(!bubbleSorter.isSorted())
            bubbleSorter.sortFrame();

        if(frameCount % 60 == 0)
            System.out.println(frameRate);
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
