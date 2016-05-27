package Model.ModelUnit;
import Model.LCSsupport.LCSClassEnum;
import Model.LCSsupport.LCSGrouping;
import Model.Model;
import Model.ModelException.MergeLineIllegalException;

import java.io.*;
import java.lang.String;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-14.
 */


public class ModelUnitRealize implements ModelUnit{



    ModelUnitData m;


    public ModelUnitRealize()
    {
        m = new ModelUnitData();
    }


    @Override
    public ArrayList<String> textReceive(int i) throws IndexOutOfBoundsException {
        return ModelUnitGetSet.textReceive(i,m);
    }

    @Override
    public void textSend(int i, ArrayList<String> s) throws IndexOutOfBoundsException {
        ModelUnitGetSet.textSend(i,s,m);
    }

    @Override
    public ArrayList<String> getArrangedText(int i) throws IndexOutOfBoundsException {
        return ModelUnitGetSet.getArrangedText(i,m);
    }

    @Override
    public ArrayList<Integer> getArrangedGroup() throws IndexOutOfBoundsException {
        return ModelUnitGetSet.getArrangedGroup(m);
    }

    @Override
    public ArrayList<Integer> getArrangedGroupSpace() throws IndexOutOfBoundsException {
        return ModelUnitGetSet.getArrangedGroupSpace(m);
    }

    @Override
    public void mergeBylineNum(int lineNum, boolean direction) throws IndexOutOfBoundsException, MergeLineIllegalException {
        ModelUnitCompareMerge.mergeBylineNum(lineNum,direction,m);
    }

    @Override
    public void mergeByGroupNum(int groupNum, boolean direction) throws MergeLineIllegalException {
        ModelUnitCompareMerge.mergeByGroupNum(groupNum,direction,m);
    }

    @Override
    public void open(String filepath, int i) throws IOException, IndexOutOfBoundsException {
        ModelUnitFileIO.open(filepath,i,m);
    }

    @Override
    public void save(String filepath, int i) throws IOException, IndexOutOfBoundsException {
        ModelUnitFileIO.save(filepath,i,m);
    }

    @Override
    public void save(int i) throws IOException {
        ModelUnitFileIO.save(i,m);
    }

    @Override
    public String filepath(int i) {
        return ModelUnitGetSet.filepath(i,m);
    }
}



