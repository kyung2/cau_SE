package Model.ModelUnit.LCSsupport;

import java.util.ArrayList;

/**
 * LCS Algorithm
 * @author Chanwoo Park
 */
public interface LCSSupport {

    public void groupStartingAction(ArrayList<Integer>[] aa);
    public void groupTrackingAction(ArrayList<Integer>[] aa, LCSGrouping p, boolean dir1, boolean dir2);
    public void groupChangingAction(ArrayList<Integer>[] aa, LCSGrouping p);
    public void groupEndingAction(ArrayList<Integer>[] aa, LCSGrouping p);

    public ArrayList<Integer>[] whenMerge(ArrayList<Integer>[][] aaa, int index, boolean dir);
}
