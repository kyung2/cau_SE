package Model.ModelUnit.LCSsupport;

import java.util.ArrayList;

/**
 * Created by User on 2016-05-20.
 */
public class LCSArrangeLine_sNonArrangeLineNum implements LCSSupportUnit {
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
    public ArrayList<Integer>[] whenMerge(ArrayList<Integer>[][] aaa, int groupNum, boolean dir) {
        int aToNonA = LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sNonArrangeLineNum);
        int aToGNum = LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sGroupNum);
        int thisNum = LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sNonArrangeLineNum);
        int in = dir?0:1;
        int newCount = 0;
        int para=0;

        ArrayList<Integer>[] newAL = new ArrayList[2];
        newAL[0] = new ArrayList<Integer>(); newAL[1] = new ArrayList<Integer>();
        for(int i=0;i<aaa[aToGNum][0].size();i++) {

            if ((Integer) aaa[aToGNum][0].get(i) != groupNum) {
                newAL[in].add(aaa[thisNum][in].get(i));
                if ((Integer) aaa[aToNonA][1 - in].get(i) != -1) {
                    newAL[1-in].add(newCount);
                    newCount++;
                }
                else newAL[1-in].add(-1);
            }
            else if ((Integer) aaa[aToGNum][0].get(i) == groupNum) {
                if ((Integer)aaa[aToNonA][in].get(i) != -1) {
                    newAL[1-in].add(newCount);
                    newCount++;
                    newAL[in].add(aaa[thisNum][in].get(i));
                }
            }

        }
        return newAL;
    }
}
