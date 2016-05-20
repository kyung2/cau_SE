package Model.LCSsupport;

import java.util.ArrayList;

/**
 * Created by User on 2016-05-20.
 */
public class LCSGroupNum_sGroupRealLineNum implements LCSSupport {
    @Override
    public void groupStartingAction(ArrayList<Integer>[] aa) {

    }

    @Override
    public void groupTrackingAction(ArrayList<Integer>[] aa, LCSGrouping p, boolean dir1, boolean dir2) {

    }

    @Override
    public void groupChangingAction(ArrayList<Integer>[] aa, LCSGrouping p) {
        aa[0].add(p.iGroupNow);
        aa[1].add(p.jGroupNow);
    }

    @Override
    public void groupEndingAction(ArrayList<Integer>[] aa, LCSGrouping p) {
        aa[0].add(p.iGroupNow);
        aa[1].add(p.jGroupNow);
    }

    @Override
    public ArrayList<Integer>[] whenMerge(ArrayList<Integer>[][] aaa, int index) {
        return null;
    }
}
