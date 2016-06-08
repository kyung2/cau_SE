package Model.ModelUnit;

import Model.ModelUnit.LCSsupport.LCSClassEnum;
import Model.ModelException.MergeLineIllegalException;
import Model.ModelUnit.LCSsupport.LCSGrouping;

import java.util.ArrayList;

/**
 * model unit comparemerge
 * @author Chanwoo Park
 */
class ModelUnitCompareMerge {

    /**
     * 재배열된 텍스트의 lineNum이 포함된 그룹을 병합
     * @param lineNum   재벼열된 텍스트의 index
     * @param direction true : left to right false right to left
     * @param m         modelUnitData
     * @throws IndexOutOfBoundsException ,MergeLineIllegalException
     * @throws MergeLineIllegalException the merge line illegal exception
     */
    static public void mergeBylineNum(int lineNum, boolean direction, ModelUnitData m) throws IndexOutOfBoundsException, MergeLineIllegalException
    {
        LCSGrouping g = new LCSGrouping();
        if(m.group==null) regrouping(m);
        mergeByGroupNum((Integer)(m.group[LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sGroupNum)][0].get(lineNum)), direction,m);
    }

    /**
     * 재배열된 텍스트의 groupNum 포함된 그룹을 병합
     * 텍스트를 update하고 recompare     *
     * @param groupNum  재배열된 텍스트의 group
     * @param direction true : left to right false right to left
     * @param m         modelUnitData
     * @throws MergeLineIllegalException the merge line illegal exception
     */
    static public void mergeByGroupNum(int groupNum, boolean direction, ModelUnitData m) throws MergeLineIllegalException
    {
        LCSGrouping g = new LCSGrouping();
        if (m.group == null) regrouping(m);

        if(groupNum < 0) throw new MergeLineIllegalException();
        if(groupNum >= m.group[LCSClassEnum.find(LCSClassEnum.LCSGroup_sIncludingArrangedLineNum)][0].size()) throw new MergeLineIllegalException();
        if (groupNum % 2 == 1) {

            /*a*/mergeText(groupNum, direction,m);
            regrouping(m);
        }
        else
        {
            throw new MergeLineIllegalException();
        }
    }

    /**
     * merge하기 위해 먼저 해야할 일: 저장되어 있는 문자열을 새로운 문자열로 replace 행하는 메소드
     * @param groupNum  재배열된 텍스트의 group
     * @param direction true : left to right false right to left
     * @param m         modelUnitData
     */
    static protected void mergeText(int groupNum, boolean direction, ModelUnitData m) //
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


    /**
     * model unit 의 그룹 정보를 읽어서 replace 된 문자열을 계산하여 만드는 일을 행함
      * @param m modelUnitData
     */
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


    /**
     * 저장되어 있는 replace 된 문자열을 새로운 재배치된 문자열로 대체
     *
     * @param m modelUnitData
     */
    static protected void regrouping(ModelUnitData m)
    {
        /* 여기서부터 */
        m.codes[0].deleteblank();
        m.codes[1].deleteblank();
        /* 여기까지 끝의 공백을 지우는 일 */
        compareMatrix(m);
        /*재배치된 후의 정보를 얻고,*/
        changeArrangedString(m);
        /*그 정보로 문자열을 재배치한다.*/
    }


    /**
     * 백트래킹 행렬을 받아서 그룹 관련 정보를 산출하는 LCSsupport 패키지를 이용하기 위하여, 사용될 백트래킹 행렬을 만드는 메소드
     *
     * @param m modelUnitData
     * @throws NullPointerException the null pointer exception
     */
    static public void compareMatrix(ModelUnitData m) throws NullPointerException // todo
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

    /**
     * 그룹 정보 모음에서 ArrangeLine_sGroupNum 정보가 위치한 칸을 반환한다.
     *
     * @return int groupnum에 대한 index
     */
    static int getArrangeLine_sGroupNumEnum()
    {
        return LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sGroupNum);
    }


    /**
     * 그룹 정보 모음에서 Group_sIncludingArrangedLineNum 정보가 위치한 칸을 반환한다.
     * @return int 포함된 groupnum에 대한 index
     */
    static int getGroup_sIncludingArrangedLineNumEnum()
    {
        return LCSClassEnum.find(LCSClassEnum.LCSGroup_sIncludingArrangedLineNum);
    }

}
