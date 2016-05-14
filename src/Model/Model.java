package Model;
import java.lang.String;
import java.util.ArrayList;
/**
 * Created by User on 2016-05-14.
 */

abstract public class Model {

    abstract class SavedText
    {
        ArrayList<String> lines;

        SavedText() {
            lines = new ArrayList<String>();
        }
        abstract void ReadFromOuter(String s);
        abstract void WriteFromOuter(String s);

        public int NumOfLine()
        {
            return lines.size();
        }
        public String Read(int i) throws IndexOutOfBoundsException
        {
            return lines.get(i);
        }
        public void Write(int i, String s) throws IndexOutOfBoundsException
        {
            lines.set(i,s);
        }
        public ArrayList<String> ReadAll()
        {
            return new ArrayList<String>(lines);
        }
        public void WriteAll(ArrayList<String> s)
        {
            lines = new ArrayList<String>(s);
        }
    }

    public final int NUMOFTEXTS = 2;
    SavedText codes[];
    ArrayList<Boolean> group[];

    public Model()
    {
        assert NUMOFTEXTS > 1;

        codes = new SavedText[NUMOFTEXTS];
        group = new ArrayList[NUMOFTEXTS];
        for(int i=0;i<NUMOFTEXTS;i++)
        {
            group[i] = new ArrayList<Boolean>();
        }
    }

    public Model(SavedText[] s) throws IndexOutOfBoundsException
    {
        this();
        textLinking(s);
    }

    public void textLinking(SavedText[] s) throws IndexOutOfBoundsException
    {
        for(int i=0;i<NUMOFTEXTS;i++) codes[i]=s[i];
    }

    protected void grouping() throws NullPointerException
    {
        int x=codes[0].NumOfLine()+1,y=codes[1].NumOfLine()+1, comp;
        int LCS[][] = new int[codes[0].NumOfLine()][codes[1].NumOfLine()];
        boolean LCSBacktrack[][][] = new boolean[codes[0].NumOfLine()][codes[1].NumOfLine()][2];

        for(int i=0;i<x;i++) {
            LCS[i][y-1]=0; LCSBacktrack[i][y-1][0]=false; LCSBacktrack[i][y-1][1]=true;
        }
        for(int j=0;j<y;j++) {
            LCS[x-1][j]=0; LCSBacktrack[x-1][j][0]=false; LCSBacktrack[x-1][j][1]=false;
        }
        for(int i=x-2;i>=0;i--) {
            for(int j=y-2;j>=0;j--) {
                if(codes[0].Read(i).compareTo(codes[1].Read(j))==0) {
                    LCS[i][j] = LCS[i + 1][j + 1] + 1;
                    LCSBacktrack[i][j][0] = true;
                    LCSBacktrack[i][j][1] = true;
                }
                else {
                    LCS[i][j]=Math.max(LCS[i+1][j],LCS[i][j+1]);
                    LCSBacktrack[i][j][0]=false;
                    LCSBacktrack[i][j][1]=(LCS[i+1][j]>LCS[i][j+1]);
                }
            }
        }

        for(int i=0,j=0;!(i==x-1 && j==y-1);) {
            if(LCSBacktrack[i][j][0] || LCSBacktrack[i][j][1]) {
                group[0].add(true); i++;
            }
            if(LCSBacktrack[i][j][0] || !(LCSBacktrack[i][j][1])){
                group[1].add(false);j++;
            }
        }


    }






}


