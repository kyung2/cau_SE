package Model;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-19.
 */

abstract public class SavedText
{
    protected ArrayList<String> lines;
    protected SavedText(ModelUnit m) {

        lines = new ArrayList<String>();
    }
    abstract void ReadFromOuter(String s) throws IOException;
    abstract void WriteFromOuter(String s) throws IOException;

    void ReadFrom(String s) throws IOException
    {
        ReadFromOuter(s);
    }

    void WriteFrom(String s) throws IOException
    {
        ReadFromOuter(s);
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
        return new ArrayList<String>(lines);
    }
    public void WriteAll(ArrayList<String> s)
    {
        lines = new ArrayList<String>(s);
    }

    public ArrayList[][] LCSMethod(SavedText another) throws NullPointerException // todo
    {
        int x=this.NumOfLine()+1,y=another.NumOfLine()+1, comp;
        int LCS[][] = new int[x][y];
        boolean LCSBacktrack[][][] = new boolean[x][y][2];
        ArrayList[][] group = new ArrayList[6][2];

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
                }
            }
        }
        boolean c = true;
        /*

        ArrayList<String> st[] = new ArrayList[2];
        st[0] = new ArrayList<String>();
        st[1]= new ArrayList<String>();
        group.add(st);

        for(int i=0,j=0, k=0;i<x-1&&j<x-1;)
        {


            if(c!=LCSBacktrack[i][j][0])
            {
                st = new ArrayList[2];
                st[0] = new ArrayList<String>();
                st[1]= new ArrayList<String>();
                group.add(st);
                k++;
            }
            c=LCSBacktrack[i][j][0];
            if(LCSBacktrack[i][j][0])
            {
                group.get(k)[0].add(Read(i));
                group.get(k)[1].add(another.Read(j));
                i++; j++;
            }
            else if(LCSBacktrack[i][j][1])
            {
                group.get(k)[0].add(Read(i)); i++;
            }
            else
            {
                group.get(k)[1].add(another.Read(j));
                j++;
            }
        }
        return group;
        */ // todo
        return group;
    }

}

