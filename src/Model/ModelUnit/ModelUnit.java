package Model.ModelUnit;

import Model.ModelException.MergeLineIllegalException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-14.
 */


public interface ModelUnit {

    public ArrayList<String> textReceive(int i) throws IndexOutOfBoundsException;
    public void textSend(int i, ArrayList<String> s) throws IndexOutOfBoundsException;
    public ArrayList<String> getArrangedText(int i) throws IndexOutOfBoundsException;
    public ArrayList<Integer> getArrangedGroup();
    public ArrayList<Integer> getArrangedGroupSpace();

    public void mergeBylineNum(int lineNum, boolean direction) throws IndexOutOfBoundsException, MergeLineIllegalException;
    public void mergeByGroupNum(int groupNum, boolean direction) throws MergeLineIllegalException;

    public void open(String filepath, int i) throws IOException, IndexOutOfBoundsException;
    public void save(String filepath, int i) throws IOException, IndexOutOfBoundsException;
    public void save(int i) throws IOException;
    public String filepath(int i);

}



