package Model;
import java.io.*;
import java.lang.String;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-14.
 */


public class ModelUnit {



    public final int NUMOFTEXTS = 2;
    SavedText[] codes;
    SavedText codeA;
    ArrayList<ArrayList<String>[]> group;
    ArrayList<String>[] fixedCodes;
    ArrayList<Boolean>[] fixedCodesColor;



    public ModelUnit()
    {
        assert NUMOFTEXTS > 1;
        codes = null;
        group = null;


    }

    public ModelUnit(SavedText ST1, SavedText ST2)
    {
        assert NUMOFTEXTS > 1;
        codes = new SavedText[2];
        codes[0] = ST1;
        codes[1] = ST2;
        group = null;


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
    public void textReceive(int i, ArrayList<String> s) throws IndexOutOfBoundsException
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



