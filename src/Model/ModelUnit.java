package Model;
import java.io.*;
import java.lang.String;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-14.
 */


class ModelUnit {



    public static final int NUMOFTEXTS = 2;
    protected SavedText[] codes;
    protected ArrayList[][] group;
    public static final int groupXSize = 4;

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

    protected void groupNull()
    {
        group = null;
        groupChange();
    }








    public void open(String s, int i) throws IOException, IndexOutOfBoundsException //open from string
    {
        codes[i].ReadFromOuter(s);
        groupNull();
    }
    public void save(String s, int i) throws IOException, IndexOutOfBoundsException //close to string
    {
        codes[i].WriteFromOuter(s);
    }
    public ArrayList<String> textReceive(int i) throws IndexOutOfBoundsException
    {
        return codes[i].ReadAll();
    }
    public void textSend(int i, ArrayList<String> s) throws IndexOutOfBoundsException
    {
        codes[i].WriteAll(s);
        groupNull();
    }

    protected void textChange()
    {
        //todo - give notice that text change
    }
    protected void groupChange()
    {
        //todo - give notice that text change
    }
    public ArrayList[][] getGroup()
    {
        if(group==null) regrouping();
       return (ArrayList[][])group.clone();
    }

    public void mergeByGroupNum(int groupNum, boolean direction) throws IndexOutOfBoundsException
    {
        //todo
    }

    public void mergeBylineNum(int LineNum, boolean direction) throws IndexOutOfBoundsException
    {
        //todo
    }



}



