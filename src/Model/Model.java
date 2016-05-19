package Model;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-19.
 */
public interface Model {
    void newModel(int tapNum);
    int newModel();
    void closeModel(int tapNum);
    ArrayList<String> getText(int tapNum, int I);
    void setText(int tapNum, ArrayList<String> text, int i);
    void getTextOuter(int tapNum, String filepath) throws IOException;
    void setTextOuter(int tapNum, String filepath) throws IOException;
    void merge(int tapNum, int Index, boolean direction) throws IndexOutOfBoundsException;
    ArrayList<ArrayList<String>[/*2*/]> getGroup(int tapNum);
    ArrayList<String> getGroupLine(int tapNum, int i);
    ArrayList<Integer> getGroupColor(int tapNum, int i);
}
