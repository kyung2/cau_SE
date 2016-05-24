package Model;

import Model.LCSsupport.LCSGrouping;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-19.
 */

abstract public class SavedText
{
    private ArrayList<String> lines;
    private String filepath;
    protected SavedText() {

        lines = new ArrayList<String>();
    filepath = null;
}
    abstract protected void ReadFromOuter(String filepath) throws IOException;
    abstract protected void WriteFromOuter(String filepath) throws IOException;

    void ReadFrom(String filepath) throws IOException
    {
        this.filepath = filepath;
        ReadFromOuter(filepath);
    }

    void WriteFrom(String filepath) throws IOException
    {
        WriteFromOuter(filepath);
        this.filepath = filepath;
    }

    void WriteFrom() throws IOException
    {
        WriteFrom(filepath);
    }

    String filepath()
    {
        return filepath;
    }

    int NumOfLine()
    {
        return lines.size();
    }
    String Read(int i) throws IndexOutOfBoundsException {
        return lines.get(i);
    }
    public void Write(int i, String s) throws IndexOutOfBoundsException {
        lines.set(i,s);
    }
    ArrayList<String> ReadAll()
    {
        return (ArrayList<String>)lines.clone();
    }
    void WriteAll(ArrayList<String> s)
    {
        lines = new ArrayList<String>(s);
    }

    ArrayList<Integer>[][] LCSMethod(SavedText another) throws NullPointerException // todo
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

