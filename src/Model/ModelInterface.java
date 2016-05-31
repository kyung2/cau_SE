package Model;

import Model.ModelException.MergeLineIllegalException;
import Model.ModelUnit.ModelUnit;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-19.
 */
public interface ModelInterface {

    void newModel(int tabNum) throws IllegalArgumentException;
    int newModel();
    public void closeModel(int tabNum) throws IllegalArgumentException;
    public void closeModelAll();
    public ModelUnit getUnit(int tabNum) throws IllegalArgumentException;



    public boolean isOpen(int tabNum, int i) throws IllegalArgumentException;
    public ArrayList<String> getText(int tabNum, int i) throws IndexOutOfBoundsException, IllegalArgumentException;
    public ArrayList<String> getArrangedText(int tabNum, int i) throws IndexOutOfBoundsException, IllegalArgumentException;
    public ArrayList<Integer> getArrangedGroupNum(int tabNum) throws IndexOutOfBoundsException, IllegalArgumentException;
    public ArrayList<Integer> getArrangedGroupSpace(int tabNum) throws IllegalArgumentException;
    public void setText(int tabNum, ArrayList<String> text, int i) throws IndexOutOfBoundsException, IllegalArgumentException;
    public void readTextOuter(int tabNum, String filepath, int i) throws IndexOutOfBoundsException, IOException, IllegalArgumentException;
    public void writeTextOuter(int tabNum, String filepath, int i) throws IndexOutOfBoundsException, IOException, IllegalArgumentException;
    public void writeTextOuter(int tabNum, int i) throws IndexOutOfBoundsException, IOException, IllegalArgumentException;
    public void mergeByLine(int tabNum, int Index, boolean direction) throws IndexOutOfBoundsException, IllegalArgumentException, MergeLineIllegalException;
    public void mergeByGroup(int tabNum, int groupNum, boolean direction) throws IndexOutOfBoundsException, IllegalArgumentException, MergeLineIllegalException;
    public ArrayList<Integer> getTabNums();


}

