package Model.LCSsupport;

import java.util.ArrayList;

/**
 * Created by User on 2016-05-20.
 */
public class LCSGroup_sIncludingArrangedLineNum implements LCSSupport {

    @Override
    public void groupStartingAction(ArrayList<Integer>[] aa) {

    }

    @Override
    public void groupTrackingAction(ArrayList<Integer>[] aa, LCSGrouping p, boolean dir1, boolean dir2) {

    }

    @Override
    public void groupChangingAction(ArrayList<Integer>[] aa, LCSGrouping p) {
        aa[0].add(Math.max(p.iGroupNow,p.jGroupNow));
        aa[1].add(Math.max(p.iGroupNow,p.jGroupNow));
    }

    @Override
    public void groupEndingAction(ArrayList<Integer>[] aa, LCSGrouping p) {
        aa[0].add(Math.max(p.iGroupNow,p.jGroupNow));
        aa[1].add(Math.max(p.iGroupNow,p.jGroupNow));
    }

    @Override
    public ArrayList<Integer>[] whenMerge(ArrayList<Integer>[][] aaa, int groupNum, boolean dir) {
        int aToNonA = LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sNonArrangeLineNum);
        int aToGNum = LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sGroupNum);
        int thisNum = LCSClassEnum.find(LCSClassEnum.LCSGroup_sIncludingArrangedLineNum);
        int groupMinus = groupNum>0?2:1;
        int groupCount=0;
        int privGroup = 0;
        int in = dir?0:1;
        int para=0;

        ArrayList<Integer>[] newAL = new ArrayList[2];
        newAL[0] = new ArrayList<Integer>(); newAL[1] = new ArrayList<Integer>();
        for(int i=0;i<aaa[aToGNum][0].size();i++) {
            para=aaa[aToGNum][0].get(i);
            if (para < groupNum) {
            }
            else if (para == groupNum) {
                if(aaa[aToNonA][in].get(i)==-1) {
                    continue;
                }
                para = para-groupMinus+1;
            }
            else if (para > groupNum) {
                para = para-groupMinus;
            }
            while(!(privGroup==para))
            {
                newAL[0].add(groupCount);
                newAL[1].add(groupCount);
                groupCount = 0;
                privGroup++;
            }
            groupCount++;
        }
        newAL[0].add(groupCount);
        newAL[1].add(groupCount);
        return newAL;
    }
}
