package Model;
import Model.LCSsupport.LCSClassEnum;
import Model.LCSsupport.LCSGrouping;
import Model.ModelException.MergeLineIllegalException;

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
        if(group==null) {
            regrouping();
            groupChange();
        }
        return (ArrayList<String>) arrangedString[i].clone();
    }
    public ArrayList<Integer> getArrangedGroup() throws IndexOutOfBoundsException //
    {
        if(group==null) {
            regrouping();
            groupChange();
        }
        return (ArrayList<Integer>) group[LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sGroupNum)][0].clone();
    }
    public void mergeBylineNum(int lineNum, boolean direction) throws IndexOutOfBoundsException, MergeLineIllegalException //
    {
        LCSGrouping g = new LCSGrouping();
        if(group==null) regrouping();
        mergeByGroupNum((Integer)(group[LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sGroupNum)][0].get(lineNum)), direction);


    }
    protected void mergeTextFirst(int groupNum, boolean direction) //
    {
        int aToNonA = LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sNonArrangeLineNum);
        int aToGNum = LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sGroupNum);
        int thisNum = LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sGroupNum);

        int in = direction?0:1;
        ArrayList<String>[] oldS, newS;
        oldS = new ArrayList[2];
        newS = new ArrayList[2];
        oldS[0] = codes[0].ReadAll();
        oldS[1] = codes[1].ReadAll();
        newS[0] = new ArrayList<String>();
        newS[1] = new ArrayList<String>();



        for(int i=0;i<group[aToGNum][0].size();i++) {
            if ((Integer) group[aToGNum][0].get(i) != groupNum) {
                if((Integer)group[aToNonA][0].get(i) != -1) {
                    newS[0].add(oldS[0].get((Integer)group[aToNonA][0].get(i)));
                }
                if((Integer)group[aToNonA][1].get(i) != -1) {
                    newS[1].add(oldS[1].get((Integer)group[aToNonA][1].get(i)));
                }
            }
            else if ((Integer) group[aToGNum][0].get(i) == groupNum) {
                if((Integer)group[aToNonA][in].get(i) != -1) {
                    newS[0].add(oldS[in].get((Integer)group[aToNonA][in].get(i)));
                    newS[1].add(oldS[in].get((Integer)group[aToNonA][in].get(i)));

                }
            }
        }
        for(int i=0;i<newS[0].size();i++)
        {
        }
        for(int i=0;i<newS[1].size();i++)
        {
        }
        codes[0].WriteAll(newS[0]);
        codes[1].WriteAll(newS[1]);

    }

    protected void regrouping()
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
    protected void changeArrangedString() //
    {
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
    protected void textChange()
    {
        //todo - give notice that text change
    }
    protected void groupChange()
    {
        //todo - give notice that text change
    }

    public void mergeByGroupNum(int groupNum, boolean direction) throws MergeLineIllegalException{
        LCSGrouping g = new LCSGrouping();
        if (group == null) regrouping();
        if (groupNum % 2 == 1) {
            mergeTextFirst(groupNum, direction);


            group = g.merge(group, groupNum, direction);
            changeArrangedString();
            groupChange();
            textChange();
        }
        else
        {
            throw new MergeLineIllegalException();
        }
    }
}



