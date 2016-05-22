package Model.LCSsupport;

import java.util.ArrayList;

/**
 * Created by User on 2016-05-20.
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
    private LCSSupport[] lcs = new LCSSupport[groupXSize];
    public LCSGrouping(){
        i=0; j=0; iBlank=0; jBlank=0; iGroupNow=0; jGroupNow=0; groupNum=0;
        for(int k=0;k<groupXSize;k++)
        {
            try {
                lcs[k] = (LCSSupport) (Class.forName("Model.LCSsupport."+LCSClassEnum.values()[k].toString()).newInstance());
            }
            catch(Exception e)
            {
                System.out.println("error making class " + e.getClass().toString());
                lcs[k] = null;
            }
        }
    }

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
    public ArrayList<Integer>[][] merge(ArrayList<Integer>[][] aaa, int index, boolean dir)
    {

        ArrayList<Integer>[][] newAaa = new ArrayList[groupXSize][2];
        for(int k=0;k<groupXSize;k++)
        {
            newAaa[k] = lcs[k].whenMerge(aaa,index,dir);
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
