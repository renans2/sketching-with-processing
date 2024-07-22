package sortingalgorithms;

import java.util.Arrays;

public class InsertionSorter implements Sorter{
    private int[] arrayToSort;
    private int a = 0;
    private int b = 0;
    private boolean sorted = false;

    public InsertionSorter(int[] arrayToSort){
        this.arrayToSort = Arrays.copyOf(arrayToSort, arrayToSort.length);
    }

    @Override
    public void sortFrame() {
        // One frame = Next element in the correct spot
//        while(b-1 >= 0 && arrayToSort[b-1] > arrayToSort[b]) {
//            int temp = arrayToSort[b - 1];
//            arrayToSort[b-1] = arrayToSort[b];
//            arrayToSort[b] = temp;
//            b--;
//        }
//        a++;
//        b = a;
//        if(a >= arrayToSort.length)
//            sorted = true;


        // One frame = One movement
        if(b-1 < 0 || arrayToSort[b-1] < arrayToSort[b]){
            a++;
            b = a;
            if(a >= arrayToSort.length)
                sorted = true;
        } else if(arrayToSort[b-1] > arrayToSort[b]) {
            int temp = arrayToSort[b - 1];
            arrayToSort[b-1] = arrayToSort[b];
            arrayToSort[b] = temp;
            b--;
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
