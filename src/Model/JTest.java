package Model;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class JTest {

    Model m;
    @Before
    public void setUp() throws Exception {
        m = ModelRealize.getInstance();

    }

    @Test
    public void add234() {
        m.newModel(2);
        m.newModel(3);
        m.newModel(4);
        System.out.println("a");
    }

    @Test
    public void close3() {
        m.closeModel(3);
        System.out.println("b");
    }
    @Test
    public void getSet() {
        ArrayList<String> s1 = new ArrayList<String>();
        ArrayList<String> s2 = new ArrayList<String>();
        m.setText(2,s1,0);
        m.setText(2,s2,1);
        System.out.println("c");
    }

    @Test(expected=IllegalArgumentException.class)
    public void getSetEx() {
        ArrayList<Integer> a = m.getTabNums();
        for(int i=0;i<a.size();i++)
        {
            System.out.print(a.get(i)+" ");
        }
        m.closeModel(3);
        try {
            ArrayList<String> s1 = new ArrayList<String>();
            ArrayList<String> s2 = new ArrayList<String>();
            m.setText(3, s1, 0);
            m.setText(3, s2, 1);
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("d");
        }
        System.out.println("e");
    }
}


