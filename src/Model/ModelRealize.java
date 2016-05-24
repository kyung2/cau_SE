package Model;

import Model.ModelException.MergeLineIllegalException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by User on 2016-05-19.
 */
public class ModelRealize implements Model {

    private static ModelRealize instance = null;
    private ArrayList<ModelUnitGroup> u;

    private ModelRealize()
    {
        u = new ArrayList<ModelUnitGroup>();
    }

    public static ModelRealize getInstance()
    {
        if(instance == null) instance = new ModelRealize();
        return instance;
    }

    public void newModel(int tabNum) throws IllegalArgumentException{
        try {
            findTap(tabNum);
        }
        catch(IllegalArgumentException e)
        {
            u.add(new ModelUnitGroup(tabNum));
            return;
        }
        throw new IllegalArgumentException();
    }

    public int newModel() {
        if(u.size()!=0) {
            u.sort(u.get(0));
            int it = u.get(u.size()-1).iterator+1;
            u.add(new ModelUnitGroup(it));
            return it;
        }
        else
        {
            u.add(new ModelUnitGroup(1));
            return 1;
        }
    }



    public void closeModel(int tabNum) throws IllegalArgumentException{
        u.remove((int)findTap(tabNum));

    }

    @Override
    public boolean isOpen(int tabNum, int i) throws IllegalArgumentException {
       return (u.get(findTap(tabNum)).m.filepath(i))!=(null);
    }

    public ArrayList<String> getText(int tabNum, int i) throws IllegalArgumentException {
        return u.get(findTap(tabNum)).m.textReceive(i);
    }

    @Override
    public ArrayList<String> getArrangedText(int tabNum, int i) throws IndexOutOfBoundsException, IllegalArgumentException {
        //todo
        return u.get(findTap(tabNum)).m.getArrangedText(i);
    }

    @Override
    public ArrayList<Integer> getArrangedGroupNum(int tabNum) throws IndexOutOfBoundsException, IllegalArgumentException {

        return u.get(findTap(tabNum)).m.getArrangedGroup();

    }

    @Override
    public ArrayList<Integer> getArrangedGroupSpace(int tabNum) throws IllegalArgumentException {
        return u.get(findTap(tabNum)).m.getArrangedGroupSpace();
    }

    public void setText(int tabNum, ArrayList<String> text, int i) throws IndexOutOfBoundsException, IllegalArgumentException {
        u.get(findTap(tabNum)).m.textSend(i, text);
    }

    public void readTextOuter(int tabNum, String filepath, int i) throws IndexOutOfBoundsException, IOException,IllegalArgumentException {
        u.get(findTap(tabNum)).m.open(filepath, i);
    }

    public void writeTextOuter(int tabNum, String filepath, int i) throws IndexOutOfBoundsException, IOException,IllegalArgumentException {
        u.get(findTap(tabNum)).m.save(filepath, i);
    }

    @Override
    public void writeTextOuter(int tabNum, int i) throws IndexOutOfBoundsException, IOException, IllegalArgumentException {
        u.get(findTap(tabNum)).m.save(i);
    }

    public void mergeByLine(int tabNum, int Index, boolean direction) throws IndexOutOfBoundsException, IllegalArgumentException, MergeLineIllegalException {
        u.get(findTap(tabNum)).m.mergeBylineNum(Index, direction);
    }

    public void mergeByGroup(int tabNum, int groupNum, boolean direction) throws IndexOutOfBoundsException, IllegalArgumentException, MergeLineIllegalException {
        u.get(findTap(tabNum)).m.mergeByGroupNum(groupNum, direction);
    }



    private Integer findTap(int tabNum) throws IllegalArgumentException
    {
        for(int i=0;i<u.size();i++)
        {
            if(u.get(i).iterator==tabNum)
            {
                return i;
            }
        }
        throw new IllegalArgumentException();
    }
    public ArrayList<Integer> getTabNums()
    {
        ArrayList<Integer> c = new ArrayList<Integer>();
        for(int i=0;i<u.size();i++) c.add(u.get(i).iterator);
            return c;
    }
}

class ModelUnitGroup implements Comparator<ModelUnitGroup>
{
    ModelUnit m;
    int iterator;
    ModelUnitGroup(int iterator)
    {
        m=new ModelUnitRealizeUsingFile();
        this.iterator=iterator;
    }

    public int compare(ModelUnitGroup o1, ModelUnitGroup o2) {
        return o1.iterator-o2.iterator;
    }


}
