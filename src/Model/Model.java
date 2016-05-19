package Model;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-19.
 */
public interface Model {
    void newModel(int tapNum);
    int newModel();
    void closeModel(int tapNum) throws IllegalAccessException;
    ArrayList<String> getText(int tapNum, int i) throws IllegalAccessException;
    void setText(int tapNum, ArrayList<String> text, int i) throws IllegalAccessException;
    void getTextOuter(int tapNum, String filepath, int i) throws IOException, IllegalAccessException;
    void setTextOuter(int tapNum, String filepath, int i) throws IOException, IllegalAccessException;
    void merge(int tapNum, int Index, boolean direction) throws IndexOutOfBoundsException, IllegalAccessException;
    ArrayList<ArrayList<String>>[] getGroup(int tapNum) throws IllegalAccessException;
    ArrayList<String>[] getGroupLine(int tapNum, int i) throws IllegalAccessException;
    ArrayList<Integer>[] getGroupColor(int tapNum, int i) throws IllegalAccessException;
}
