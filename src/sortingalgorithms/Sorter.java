package sortingalgorithms;

/**
 * @author Renan Silva -> @renans2 on github
 */
public interface Sorter {
    void sortFrame();
    int[] getCurrentArray();
    boolean isSorted();
    int getCurrentIndex();
    int getBorderIndex();
}
