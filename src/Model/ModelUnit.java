package Model;
import Model.LCSsupport.LCSClassEnum;
import Model.LCSsupport.LCSGrouping;

import java.io.*;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by User on 2016-05-14.
 */


class ModelUnit {



    public static final int NUMOFTEXTS = 2;
    protected SavedText[] codes;
    protected ArrayList[][] group;
    protected ArrayList[] arrangedString;


    public ModelUnit()
    {
        assert NUMOFTEXTS > 1;
        codes = null;
        group = null;
        arrangedString = null;
    }

    public ModelUnit(SavedText ST1, SavedText ST2)
    {
        assert NUMOFTEXTS > 1;
        codes = new SavedText[2];
        codes[0] = ST1;
        codes[1] = ST2;
        group = null;
        arrangedString = null;


    }

    protected void regrouping() //
    {
        group = codes[0].LCSMethod(codes[1]);
        changeArrangedString();
        groupChange();

    }

    protected void groupNull()
    {
        group = null;
        arrangedString = null;
        groupChange();
    }

    protected void changeArrangedString()
    {
        System.out.println("----");
        arrangedString = new ArrayList[2];
        arrangedString[0] = new ArrayList<String>();
        arrangedString[1] = new ArrayList<String>();
        int aToNonA;
        try {
            aToNonA = LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sNonArrangeLineNum);
        }
        catch(Exception e)
        {
            aToNonA = 0;
        }
        for(int i=0;i<group[aToNonA][0].size();i++)
        {
            if((Integer)group[aToNonA][0].get(i)==-1) {
                arrangedString[0].add("");
            }
            else {
                arrangedString[0].add(codes[0].Read((Integer)group[aToNonA][0].get(i)));
            }
            if((Integer)group[aToNonA][1].get(i)==-1) {
                arrangedString[1].add("");
            }
            else {
                arrangedString[1].add(codes[1].Read((Integer)group[aToNonA][1].get(i)));
            }
        }
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
    public ArrayList<String> getArrangedText(int i) throws IndexOutOfBoundsException
    {
        return new ArrayList<String>((Collection<String>) arrangedString[i].clone());
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


    public void mergeBylineNum(int lineNum, boolean direction) throws IndexOutOfBoundsException
    {
        LCSGrouping g = new LCSGrouping();
        group = g.merge(group, lineNum, direction);
        changeArrangedString();
        // todo -> SavedText
        groupChange();
        textChange();
    }

}



