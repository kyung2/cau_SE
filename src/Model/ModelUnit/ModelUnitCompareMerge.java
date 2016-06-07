package Model.ModelUnit;

import Model.ModelUnit.LCSsupport.LCSClassEnum;
import Model.ModelUnit.LCSsupport.LCSGrouping;
import Model.ModelException.MergeLineIllegalException;

import java.util.ArrayList;

/**
 * Created by User on 2016-05-27.
 */
class ModelUnitCompareMerge {
    static public void mergeBylineNum(int lineNum, boolean direction, ModelUnitData m) throws IndexOutOfBoundsException, MergeLineIllegalException
    {
        LCSGrouping g = new LCSGrouping();
        if(m.group==null) regrouping(m);
        mergeByGroupNum((Integer)(m.group[LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sGroupNum)][0].get(lineNum)), direction,m);


    }
    static public void mergeByGroupNum(int groupNum, boolean direction, ModelUnitData m) throws MergeLineIllegalException
    {
        LCSGrouping g = new LCSGrouping();
        if (m.group == null) regrouping(m);

        if(groupNum < 0) throw new MergeLineIllegalException();
        if(groupNum >= m.group[LCSClassEnum.find(LCSClassEnum.LCSGroup_sIncludingArrangedLineNum)][0].size()) throw new MergeLineIllegalException();
        if (groupNum % 2 == 1) {

            m.codes[0].deleteblank();
            m.codes[1].deleteblank();
            mergeTextFirst(groupNum, direction,m);


            m.group = g.merge(m.group, groupNum, direction);
            changeArrangedString(m);
            m.groupChange();
            m.textChange();
        }
        else
        {
            throw new MergeLineIllegalException();
        }
    }

    static protected void mergeTextFirst(int groupNum, boolean direction, ModelUnitData m) //
    {
        int aToNonA = LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sNonArrangeLineNum);
        int aToGNum = LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sGroupNum);
        int thisNum = LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sGroupNum);

        int in = direction?0:1;
        ArrayList<String>[] oldS, newS;
        oldS = new ArrayList[2];
        newS = new ArrayList[2];
        oldS[0] = m.codes[0].lines;
        oldS[1] = m.codes[1].lines;
        newS[0] = new ArrayList<String>();
        newS[1] = new ArrayList<String>();



        for(int i=0;i<m.group[aToGNum][0].size();i++) {
            if ((Integer) m.group[aToGNum][0].get(i) != groupNum) {
                if((Integer)m.group[aToNonA][0].get(i) != -1) {
                    newS[0].add(oldS[0].get((Integer)m.group[aToNonA][0].get(i)));
                }
                if((Integer)m.group[aToNonA][1].get(i) != -1) {
                    newS[1].add(oldS[1].get((Integer)m.group[aToNonA][1].get(i)));
                }
            }
            else if ((Integer) m.group[aToGNum][0].get(i) == groupNum) {
                if((Integer)m.group[aToNonA][in].get(i) != -1) {
                    newS[0].add(oldS[in].get((Integer)m.group[aToNonA][in].get(i)));
                    newS[1].add(oldS[in].get((Integer)m.group[aToNonA][in].get(i)));

                }
            }
        }
        for(int i=0;i<newS[0].size();i++)
        {
        }
        for(int i=0;i<newS[1].size();i++)
        {
        }
        m.codes[0].lines = newS[0];
        m.codes[1].lines = newS[1];

    }

    static protected void regrouping(ModelUnitData m)
    {
        LCSMethod(m);
        changeArrangedString(m);
        m.groupChange();
    }

    static protected void changeArrangedString(ModelUnitData m) //
    {
        m.arrangedString = new ArrayList[2];
        m.arrangedString[0] = new ArrayList<String>();
        m.arrangedString[1] = new ArrayList<String>();
        int aToNonA;
        try {
            aToNonA = LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sNonArrangeLineNum); ////////////////
        }
        catch(Exception e)
        {
            aToNonA = 0;
        }
        for(int i=0;i<m.group[aToNonA][0].size();i++)
        {
            if((Integer)m.group[aToNonA][0].get(i)==-1) {
                m.arrangedString[0].add("");
            }
            else {
                m.arrangedString[0].add(m.codes[0].lines.get((Integer)m.group[aToNonA][0].get(i)));
            }
            if((Integer)m.group[aToNonA][1].get(i)==-1) {
                m.arrangedString[1].add("");
            }
            else {
                m.arrangedString[1].add(m.codes[1].lines.get((Integer)m.group[aToNonA][1].get(i)));
            }
        }
    }
    static private void LCSMethod(ModelUnitData m) throws NullPointerException // todo
    {
        int x=m.codes[0].lines.size()+1,y=m.codes[1].lines.size()+1;
        int LCS[][] = new int[x][y];
        boolean LCSBacktrack[][][] = new boolean[x][y][2];

        for(int i=0;i<x;i++) {
            LCS[i][y-1]=0; LCSBacktrack[i][y-1][0]=false; LCSBacktrack[i][y-1][1]=true;
        }
        for(int j=0;j<y;j++) {
            LCS[x-1][j]=0; LCSBacktrack[x-1][j][0]=false; LCSBacktrack[x-1][j][1]=false;
        }
        for(int i=x-2;i>=0;i--) {
            for(int j=y-2;j>=0;j--) {
                if(m.codes[0].lines.get(i).compareTo(m.codes[1].lines.get(j))==0) {
                    LCS[i][j] = LCS[i + 1][j + 1] + 1;
                    LCSBacktrack[i][j][0] = true;
                    LCSBacktrack[i][j][1] = true;
                }
                else {
                    LCS[i][j]=Math.max(LCS[i+1][j],LCS[i][j+1]);
                    LCSBacktrack[i][j][0]=false;
                    LCSBacktrack[i][j][1]=(LCS[i+1][j]>LCS[i][j+1]);
                    if(LCS[i + 1][j + 1]==Math.max(LCS[i+1][j],LCS[i][j+1]))
                    {
                        LCSBacktrack[i][j][0]=true;
                        LCSBacktrack[i][j][1]=false;
                    }
                }
            }
        }
        LCSGrouping p = new LCSGrouping();
        m.group =  p.start(LCSBacktrack,x,y);
    }

    static int getArrangeLine_sGroupNum()
    {
        return LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sGroupNum);
    }
    static int getGroup_sIncludingArrangedLineNum()
    {
        return LCSClassEnum.find(LCSClassEnum.LCSGroup_sIncludingArrangedLineNum);
    }
}
