package Model;

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



    public void closeModel(int tapNum) {
        u.remove(findTap(tapNum));

    }

    public ArrayList<String> getText(int tapNum, int i) {
        return u.get(findTap(tapNum)).m.textReceive(i);
    }

    public void setText(int tapNum, ArrayList<String> text, int i) {
        u.get(findTap(tapNum)).m.textSend(i, text);
    }

    public void getTextOuter(int tapNum, String filepath, int i) throws IOException {
        u.get(findTap(tapNum)).m.open(filepath, i);
    }

    public void setTextOuter(int tapNum, String filepath, int i) throws IOException {

    }

    @Override
    public void merge(int tapNum, int Index, boolean direction) throws IndexOutOfBoundsException {

    }

    @Override
    public ArrayList<ArrayList<String>[]> getGroup(int tapNum) {
        return null;
    }

    @Override
    public ArrayList<String> getGroupLine(int tapNum, int i) {
        return null;
    }

    @Override
    public ArrayList<Integer> getGroupColor(int tapNum, int i) {
        return null;
    }

    private Integer findTap(int tapNum)
    {
        for(int i=0;i<u.size();i++)
        {
            if(u.get(i).iterator==tapNum)
            {
                return i;
            }
        }
        return null;
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
