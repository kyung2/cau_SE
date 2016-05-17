package Model;
import java.io.*;
import java.lang.String;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-14.
 */

public class Model {

    static abstract class SavedText
            // the texts what saved in model
            // please close this method who only using this
    {
        protected ArrayList<String> lines;
        Model m;
        protected SavedText(Model m) {

            lines = new ArrayList<String>();
            textChange();
        }
        abstract protected void ReadFromOuter(String s) throws IOException;
        abstract protected void WriteFromOuter(String s) throws IOException;

        void ReadFrom(String s) throws IOException
        {
            ReadFromOuter(s);
            textChange();
        }

        void WriteFrom(String s) throws IOException
        {
            ReadFromOuter(s);
            textChange();
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
            textChange();
        }
        public ArrayList<String> ReadAll()
        {
            return new ArrayList<String>(lines);
        }
        public void WriteAll(ArrayList<String> s)
        {
            lines = new ArrayList<String>(s);
            textChange();
        }

        public ArrayList<ArrayList<String>[]> LCSMethod(SavedText another) throws NullPointerException // please close this method who only using this
        {
            int x=this.NumOfLine()+1,y=another.NumOfLine()+1, comp;
            int LCS[][] = new int[x][y];
            boolean LCSBacktrack[][][] = new boolean[x][y][2];
            ArrayList<ArrayList<String>[]> group = new ArrayList<ArrayList<String>[]>();

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
        }



        private void textChange()
        {
            m.textChange();
        }
    }

    public final int NUMOFTEXTS = 2;
    SavedText codes[];
    ArrayList<ArrayList<String>[]> group;
    ArrayList<String>[] fixedCodes;
    ArrayList<Boolean>[] fixedCodesColor;


    public Model()
    {
        assert NUMOFTEXTS > 1;
        codes = null;
        group = null;
        SavedTextCanFileRR[] s = {new SavedTextCanFileRR(this), new SavedTextCanFileRR(this)};
        codes = s;
        textLinking(s);

    }


    protected void textLinking(SavedText[] s) throws IndexOutOfBoundsException
    {
        for(int i=0;i<NUMOFTEXTS;i++) codes[i]=s[i];
    }
    protected void regrouping() //
    {
        group = codes[0].LCSMethod(codes[1]);
        groupChange();
    }







    public void open(String s, int i) throws IOException, IndexOutOfBoundsException //open from string
    {
        codes[i].ReadFromOuter(s);
    }
    public void save(String s, int i) throws IOException, IndexOutOfBoundsException //close to string
    {
        codes[i].WriteFromOuter(s);
    }
    public ArrayList<String> textSend(int i) throws IndexOutOfBoundsException
    {
        return codes[i].ReadAll();
    }
    public void textRecieve(int i, ArrayList<String> s) throws IndexOutOfBoundsException
    {
        codes[i].WriteAll(s);
    }
    public void groupCopy(int index, int dir) throws NullPointerException, IndexOutOfBoundsException
    {
        if(dir!=0&&dir!=1) throw new IndexOutOfBoundsException();
        if(group == null) throw new NullPointerException();
        group.get(index)[1-dir]=(ArrayList<String>)(group.get(index)[dir].clone());
        if(index>0)
        {
            group.get(index-1)[1-dir].addAll(group.get(index)[1-dir]);
            group.get(index-1)[dir].addAll(group.get(index)[dir]);
            if(index+1<=group.size())
            {
                group.get(index-1)[1-dir].addAll(group.get(index+1)[1-dir]);
                group.get(index-1)[dir].addAll(group.get(index+1)[dir]);
                group.remove(index+1);
            }
            group.remove(index);
        }
        groupChange();
    }
    protected void textChange()
    {
        //todo - give notice that text change
    }
    protected void groupChange()
    {
        //todo - give notice that text change
    }



}


class SavedTextCanFileRR extends Model.SavedText
{
    public void ReadFromOuter(String s) throws IOException {
        File aFile = new File(s);
        FileReader fileReader = new FileReader(aFile);
        BufferedReader reader = new BufferedReader(fileReader);
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
    }
    public void WriteFromOuter(String s) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(s));
        String line;
        for(int i=0;i<lines.size();i++) {
            writer.write(lines.get(i));
        }
        writer.close();
    }

    SavedTextCanFileRR(Model m)
    {
        super(m);
    }
}
