package Model.LCSsupport;

import java.util.ArrayList;

/**
 * Created by User on 2016-05-20.
 */
public class LCSArrangeLine_sNonArrangeLineNum implements LCSSupport {
    public void groupStartingAction(ArrayList<Integer>[] aa) {

    }

    public void groupTrackingAction(ArrayList<Integer>[] aa, LCSGrouping p, boolean dir1, boolean dir2) {
        if(dir1||dir2) aa[0].add(p.i);
        else aa[0].add(-1);
        if(dir1||(!dir2)) aa[1].add(p.j);
        else aa[1].add(-1);
    }

    public void groupChangingAction(ArrayList<Integer>[] aa, LCSGrouping p) {

    }

    @Override
    public void groupEndingAction(ArrayList<Integer>[] aa, LCSGrouping p) {

    }

    @Override
    public ArrayList<Integer>[] whenMerge(ArrayList<Integer>[][] aaa, int index) {
        return null;
    }
}
