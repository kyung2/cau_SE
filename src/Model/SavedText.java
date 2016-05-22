package Model;

import Model.LCSsupport.LCSGrouping;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-19.
 */

abstract public class SavedText
{
    protected ArrayList<String> lines;
    protected String filepath;
    protected SavedText() {

        lines = new ArrayList<String>();
    filepath = null;
}
    abstract void ReadFromOuter(String s) throws IOException;
    abstract void WriteFromOuter(String s) throws IOException;

    void ReadFrom(String s) throws IOException
    {
        filepath = s;
        ReadFromOuter(s);
    }

    void WriteFrom(String s) throws IOException
    {
        WriteFromOuter(s);
    }

    void Writefrom() throws IOException
    {
        WriteFromOuter(filepath);
    }

    String filepath()
    {
        return filepath;
    }

    public int NumOfLine()
    {
        return lines.size();
    }
    public String Read(int i) throws IndexOutOfBoundsException {
        return lines.get(i);
    }
    public void Write(int i, String s) throws IndexOutOfBoundsException {
        lines.set(i,s);
    }
    public ArrayList<String> ReadAll()
    {
        return (ArrayList<String>)lines.clone();
    }
    public void WriteAll(ArrayList<String> s)
    {
        lines = new ArrayList<String>(s);
    }

    public ArrayList<Integer>[][] LCSMethod(SavedText another) throws NullPointerException // todo
    {
        int x=this.NumOfLine()+1,y=another.NumOfLine()+1;
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
                if(this.Read(i).compareTo(another.Read(j))==0) {
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

        return p.start(LCSBacktrack,x,y);
    }

}

