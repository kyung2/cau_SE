package Model.ModelUnit.LCSsupport;


import java.util.ArrayList;


/**
 * 두 텍스트의 비교만을 위하여 만들어진, 백트래킹 행렬에서 그룹 관련 정보를 얻어내기 위한 패키지의
 * 실제로 비교된 정보를 얻어내는 역할을 하는 클래스이다.
 * @author Chanwoo Park
 */
public class LCSGrouping {

    int i;
    int j;
    int iBlank;
    int jBlank;
    int iGroupNow;
    int jGroupNow;
    int groupNum;
    public static final int groupXSize = LCSClassEnum.values().length;
    private LCSSupportUnit[] lcs = new LCSSupportUnit[groupXSize];
    public LCSGrouping(){
        i=0; j=0; iBlank=0; jBlank=0; iGroupNow=0; jGroupNow=0; groupNum=0;
        for(int k=0;k<groupXSize;k++)
        {
            try {
                lcs[k] = (LCSSupportUnit) (Class.forName("Model.ModelUnit.LCSsupport."+LCSClassEnum.values()[k].toString()).newInstance());
            }
            catch(Exception e)
            {
                System.out.println("error making class " + e.getClass().toString());
                lcs[k] = null;
            }
        }
    }


    /**
     * 백트래킹 행렬을 받아 그룹 관련 정보를 반환한다.
     * @param LCSBacktrack 백트래킹 행렬
     * @param x 백트래킹 행렬의 가로크기
     * @param y 백트래킹 행렬의 세로크기
     * @return ArrayListInteger 2D 그룹 관련 정보
     * */
    public ArrayList<Integer>[][] start(boolean LCSBacktrack[][][], int x, int y)
    {
        ArrayList<Integer>[][] group = new ArrayList[groupXSize][2];

        for(int k=0;k<groupXSize;k++) for(int l=0;l<2;l++) group[k][l] = new ArrayList<Integer>();

        for(int k=0;k<groupXSize;k++) lcs[k].groupStartingAction(group[k]);

        while( (i<x-1||j<y-1))
        {
            if((groupNum%2==0)!=(LCSBacktrack[i][j][0]&&LCSBacktrack[i][j][1]))
            {

                for(int k=0;k<groupXSize;k++) lcs[k].groupChangingAction(group[k],this);
                groupChangingAction();
            }
            for(int k=0;k<groupXSize;k++) lcs[k].groupTrackingAction(group[k],this,LCSBacktrack[i][j][0],LCSBacktrack[i][j][1]);
            groupTrackingAction(LCSBacktrack[i][j][0],LCSBacktrack[i][j][1]);
        }
        for(int k=0;k<groupXSize;k++) lcs[k].groupEndingAction(group[k], this);
        return group;

    }
    public ArrayList<Integer>[][] merge(ArrayList<Integer>[][] aaa, int groupNum, boolean dir)
    {

        ArrayList<Integer>[][] newAaa = new ArrayList[groupXSize][2];
        for(int k=0;k<groupXSize;k++)
        {
            newAaa[k] = lcs[k].whenMerge(aaa,groupNum,dir);
        }
        return newAaa;
    }
    protected void groupTrackingAction(boolean dir1, boolean dir2)
    {
        if(dir1)
        {
            i++; j++; iGroupNow++; jGroupNow++;
        }
        else if(dir2)
        {
            i++; jBlank++; iGroupNow++;
        }
        else
        {
            iBlank++; j++; jGroupNow++;
        }
    }
    protected void groupChangingAction()
    {
        iGroupNow = jGroupNow = 0;
        groupNum++;
    }

}
