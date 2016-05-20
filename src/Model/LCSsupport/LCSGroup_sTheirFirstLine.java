package Model.LCSsupport;

import java.util.ArrayList;

/**
 * Created by User on 2016-05-20.
 */
public class LCSGroup_sTheirFirstLine implements LCSSupport {
    @Override
    public void groupStartingAction(ArrayList<Integer>[] aa) {
        aa[0].add(0);
        aa[1].add(0);
    }

    @Override
    public void groupTrackingAction(ArrayList<Integer>[] aa, LCSGrouping p, boolean dir1, boolean dir2) {

    }

    @Override
    public void groupChangingAction(ArrayList<Integer>[] aa, LCSGrouping p) {
        aa[0].add(p.i);
        aa[1].add(p.j);
    }

    @Override
    public void groupEndingAction(ArrayList<Integer>[] aa, LCSGrouping p) {

    }

    @Override
    public ArrayList<Integer>[] whenMerge(ArrayList<Integer>[][] aaa, int index) {
        return null;
    }
}
