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
        int groupNum = aaa[aToGNum][0].get(index);
        int groupMinus = groupNum>0?2:1;
        int iminus=0;
        int in = dir?1:0;

        ArrayList[] newAL = new ArrayList[2];
        newAL[0] = new ArrayList<Integer>(); newAL[1] = new ArrayList<Integer>();
        for(int i=0;i<aaa[aToNonA][0].size();i++)
        {
            if((Integer)aaa[aToGNum][0].get(i)==groupNum)
            {
                if((Integer)aaa[aToNonA][0].get(i)==-1)
                {


                }
            }
            else if((Integer)aaa[aToGNum][0].get(i)>groupNum)
            {

            }
        }
        return null;
    }
}
