package Model;

import Model.LCSsupport.LCSClassEnum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by User on 2016-05-19.
 */
public class ModelRealize implements Model {


    ArrayList<ModelUnitGroup> u;

    ModelRealize()
    {
        u = new ArrayList<ModelUnitGroup>();
    }
    public void newModel(int tapNum) {
        u.add(new ModelUnitGroup(tapNum));
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



    public void closeModel(int tapNum) throws IllegalAccessException{
        u.remove(findTap(tapNum));

    }

    public ArrayList<String> getText(int tapNum, int i) throws IllegalAccessException {
        return u.get(findTap(tapNum)).m.textReceive(i);
    }

    @Override
    public ArrayList<String> getArrangedText(int tapNum, int i) throws IndexOutOfBoundsException, IllegalAccessException {
        //todo
        return u.get(findTap(tapNum)).m.getArrangedText(i);
    }

    @Override
    public ArrayList<Integer> getArrangedGroupNum(int tapNum) throws IndexOutOfBoundsException, IllegalAccessException {

        return u.get(findTap(tapNum)).m.getArrangedGroup();

    }

    public void setText(int tapNum, ArrayList<String> text, int i) throws IndexOutOfBoundsException, IllegalAccessException {
        u.get(findTap(tapNum)).m.textSend(i, text);
    }

    public void readTextOuter(int tapNum, String filepath, int i) throws IndexOutOfBoundsException, IOException,IllegalAccessException {
        u.get(findTap(tapNum)).m.open(filepath, i);
    }

    public void writeTextOuter(int tapNum, String filepath, int i) throws IndexOutOfBoundsException, IOException,IllegalAccessException {
        u.get(findTap(tapNum)).m.save(filepath, i);
    }

    public void merge(int tapNum, int Index, boolean direction) throws IndexOutOfBoundsException, IllegalAccessException {
        u.get(findTap(tapNum)).m.mergeBylineNum(Index, direction);
    }



    private Integer findTap(int tapNum) throws IllegalAccessException
    {
        for(int i=0;i<u.size();i++)
        {
            if(u.get(i).iterator==tapNum)
            {
                return i;
            }
        }
        throw new IllegalAccessException();
    }
}

class ModelUnitGroup implements Comparator<ModelUnitGroup>
{
    ModelUnitUsingFile m;
    int iterator;
    ModelUnitGroup(int iterator)
    {
        m=new ModelUnitUsingFile();
        this.iterator=iterator;
    }

    public int compare(ModelUnitGroup o1, ModelUnitGroup o2) {
        return o1.iterator-o2.iterator;
    }
}
