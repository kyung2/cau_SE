package Model;
import java.io.IOException;
import java.lang.String;
import java.util.ArrayList;
import java.lang.Boolean;
/**
 * Created by User on 2016-05-14.
 */

abstract public class Model {

    static abstract class SavedText
    {
        protected ArrayList<String> lines;

        protected SavedText() {
            lines = new ArrayList<String>();
        }
        abstract void ReadFromOuter(String s) throws IOException;
        abstract void WriteFromOuter(String s) throws IOException;

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

        public ArrayList<Boolean>[] LCSMethod(SavedText another) throws NullPointerException
        {
            int x=this.NumOfLine()+1,y=another.NumOfLine()+1, comp;
            int LCS[][] = new int[x][y];
            boolean LCSBacktrack[][][] = new boolean[x][y][2];
            ArrayList<Boolean> group[] = new ArrayList[2];
            group[0] = new ArrayList<Boolean>();
            group[1] = new ArrayList<Boolean>();

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
            for(int i=0,j=0;!(i==x-1 && j==y-1);) {
                if(LCSBacktrack[i][j][0] || LCSBacktrack[i][j][1]) {
                    group[0].add(true); i++;
                }
                if(LCSBacktrack[i][j][0] || !(LCSBacktrack[i][j][1])){
                    group[1].add(false);j++;
                }
            }
            return group;
        }
    }

    public final int NUMOFTEXTS = 2;
    SavedText codes[];

    public Model()
    {
        assert NUMOFTEXTS > 1;
        codes = new SavedText[NUMOFTEXTS];
    }

    public void open(String s, int i) throws IOException, IndexOutOfBoundsException
    {
        codes[i].ReadFromOuter(s);
    }
    public void save(String s, int i) throws IOException, IndexOutOfBoundsException
    {
        codes[i].WriteFromOuter(s);
    }
    public void textLinking(SavedText[] s) throws IndexOutOfBoundsException
    {
        for(int i=0;i<NUMOFTEXTS;i++) codes[i]=s[i];
    }
    public ArrayList<Boolean>[] grouping()
    {
        return codes[0].LCSMethod(codes[1]);
    }
    public ArrayList<String> textSend(int i) throws IndexOutOfBoundsException
    {
        return codes[i].ReadAll();
    }
    public void textRecieve(int i, ArrayList<String> s) throws IndexOutOfBoundsException
    {
        codes[i].WriteAll(s);
    }


}


