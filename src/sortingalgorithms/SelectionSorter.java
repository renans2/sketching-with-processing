package sortingalgorithms;

import java.util.Arrays;

public class SelectionSorter implements Sorter{
    private int[] arrayToSort;
    private int a = 0;
    private int b = 0;
    private int minIndex = 0;
    private boolean sorted = false;

    public SelectionSorter(int[] arrayToSort){
        this.arrayToSort = Arrays.copyOf(arrayToSort, arrayToSort.length);
    }

    @Override
    public void sortFrame() {
        if(b >= arrayToSort.length){
            int temp = arrayToSort[a];
            arrayToSort[a] = arrayToSort[minIndex];
            arrayToSort[minIndex] = temp;
            a++;
            b = a;
            minIndex = b;
            if(a == arrayToSort.length)
                sorted = true;
        } else if(arrayToSort[b] < arrayToSort[minIndex]){
            minIndex = b;
            b++;
        } else {
            b++;
        }
    }

    @Override
    public int[] getCurrentArray() {
        return arrayToSort;
    }

    @Override
    public boolean isSorted() {
        return sorted;
    }

    @Override
    public int getCurrentIndex() {
        return b;
    }

    @Override
    public int getBorderIndex() {
        return a;
    }
}
