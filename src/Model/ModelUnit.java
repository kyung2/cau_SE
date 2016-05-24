package Model;

import Model.LCSsupport.LCSClassEnum;
import Model.LCSsupport.LCSGrouping;
import Model.ModelException.MergeLineIllegalException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-14.
 */


interface ModelUnit {

    public ArrayList<String> textReceive(int i) throws IndexOutOfBoundsException;
    public void textSend(int i, ArrayList<String> s) throws IndexOutOfBoundsException;
    public ArrayList<String> getArrangedText(int i) throws IndexOutOfBoundsException;
    public ArrayList<Integer> getArrangedGroup() throws IndexOutOfBoundsException ;
    public ArrayList<Integer> getArrangedGroupSpace() throws IndexOutOfBoundsException;
    public void mergeBylineNum(int lineNum, boolean direction) throws IndexOutOfBoundsException, MergeLineIllegalException;
    public void mergeByGroupNum(int groupNum, boolean direction) throws MergeLineIllegalException;

    public void open(String s, int i) throws IOException, IndexOutOfBoundsException;
    public void save(String s, int i) throws IOException, IndexOutOfBoundsException;
    public void save(int i) throws IOException;
    public String filepath(int i);

}



