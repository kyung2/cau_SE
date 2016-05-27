package Model.ModelUnit;

import java.util.ArrayList;

/**
 * Created by User on 2016-05-27.
 */
class ModelUnitGetSet {
    static public ArrayList<String> textReceive(int i, ModelUnitData m) throws IndexOutOfBoundsException
    {
        return new ArrayList(m.codes[i].lines);
    }
    static void textSend(int i, ArrayList<String> s, ModelUnitData m) throws IndexOutOfBoundsException
    {
        m.codes[i].lines=new ArrayList(s);
        m.groupNull();
    }
    static ArrayList<String> getArrangedText(int i, ModelUnitData m) throws IndexOutOfBoundsException
    {
        if(m.group==null) {
            ModelUnitCompareMerge.regrouping(m);
            m.groupChange();
        }
        return (ArrayList<String>) m.arrangedString[i].clone();
    }
    static ArrayList<Integer> getArrangedGroup(ModelUnitData m) throws IndexOutOfBoundsException
    {
        if(m.group==null) {
            ModelUnitCompareMerge.regrouping(m);
            m.groupChange();
        }
        return (ArrayList<Integer>) m.group[ModelUnitCompareMerge.getArrangeLine_sGroupNum()][0].clone();
    }
    static ArrayList<Integer> getArrangedGroupSpace(ModelUnitData m) throws IndexOutOfBoundsException
    {
        if(m.group==null) {
            ModelUnitCompareMerge.regrouping(m);
            m.groupChange();
        }
        return (ArrayList<Integer>) m.group[ModelUnitCompareMerge.getGroup_sIncludingArrangedLineNum()][0].clone();
    }

    static String filepath(int i, ModelUnitData m)
    {
        return m.codes[i].filepath;
    }
}
