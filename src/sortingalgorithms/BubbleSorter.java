package sortingalgorithms;

import java.util.Arrays;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class BubbleSorter implements Sorter {
    private int[] arrayToSort;
    private int a;
    private int b = 0;
    private boolean sorted = false;

    public BubbleSorter(int[] arrayToSort){
        this.arrayToSort = Arrays.copyOf(arrayToSort, arrayToSort.length);
        a = arrayToSort.length;
    }

    @Override
    public void sortFrame() {
        // One frame = Next element in the correct spot
//        while(b+1 < a){
//            if(arrayToSort[b] > arrayToSort[b+1]) {
//                int temp = arrayToSort[b+1];
//                arrayToSort[b+1] = arrayToSort[b];
//                arrayToSort[b] = temp;
//            }
//            b++;
//        }
//        a--;
//        b = 0;
//        if(a == 0) {
//            sorted = true;
//            b = -1;
//            a = -1;
//        }


        // One frame == One movement
        if(b+1 == a){
            a--;
            b = 0;
            if(a == 0) {
                sorted = true;
                b = -1;
                a = -1;
            }
        } else if(arrayToSort[b] > arrayToSort[b+1]) {
            int temp = arrayToSort[b+1];
            arrayToSort[b+1] = arrayToSort[b];
            arrayToSort[b] = temp;
            b++;
        } else if(arrayToSort[b] < arrayToSort[b+1]) {
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
