package Model.LCSsupport;

import java.util.ArrayList;

/**
 * Created by User on 2016-05-20.
 */
public class LCSArrangeLine_sGroupNum implements LCSSupport {

    @Override
    public void groupStartingAction(ArrayList<Integer>[] aa) {

    }

    @Override
    public void groupTrackingAction(ArrayList<Integer>[] aa, LCSGrouping p, boolean dir1, boolean dir2) {
        aa[0].add(p.groupNum);
        aa[1].add(p.groupNum);
    }

    @Override
    public void groupChangingAction(ArrayList<Integer>[] aa, LCSGrouping p) {

    }

    @Override
    public void groupEndingAction(ArrayList<Integer>[] aa, LCSGrouping p) {

    }

    @Override
    public ArrayList<Integer>[] whenMerge(ArrayList<Integer>[][] aaa, int index, boolean dir) {
        int aToNonA = LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sNonArrangeLineNum);
        int aToGNum = LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sGroupNum);
        int thisNum = LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sNonArrangeLineNum);
        int groupNum = aaa[aToGNum][0].get(index);
        int groupMinus = groupNum>0?2:1;
        int in = dir?1:0;
        int para=0;

        ArrayList<Integer>[] newAL = new ArrayList[2];
        newAL[0] = new ArrayList<Integer>(); newAL[1] = new ArrayList<Integer>();
        for(int i=0;i<aaa[aToGNum][0].size();i++) {
            if ((Integer) aaa[aToGNum][0].get(i) < groupNum) {
                newAL[0].add(aaa[thisNum][0].get(i));
                newAL[1].add(aaa[thisNum][1].get(i));
            }
            if ((Integer) aaa[aToGNum][0].get(i) == groupNum) {
                if ((Integer) aaa[aToNonA][1 - in].get(i) == -1) {

                } else {
                    if ((Integer) aaa[aToNonA][in].get(i) == -1) {
                        para++;
                    }
                    newAL[0].add(aaa[thisNum][0].get(i)-groupMinus+1);
                    newAL[1].add(aaa[thisNum][1].get(i)-groupMinus+1);
                }
            } else if ((Integer) aaa[aToGNum][0].get(i) > groupNum) {
                newAL[0].add(aaa[thisNum][0].get(i)-groupMinus);
                newAL[1].add(aaa[thisNum][1].get(i)-groupMinus);
            }
        }
        return newAL;
    }
}
