package Model;

/*
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JModelTest {



    Model m;
    @Before
    public void setUp() throws Exception {
        m = ModelRealize.getInstance();

    }

    @Test
    public void no001Add234() {
        m.newModel(2);
        m.newModel(3);
        m.newModel(4);
        ArrayList<Integer> a = m.getTabNums();
        assertEquals(a.size(),3);
        assertEquals((int)a.get(0),2);
        assertEquals((int)a.get(1),3);
        assertEquals((int)a.get(2),4);
    }

    @Test
    public void no002Close3() {
        m.closeModel(3);
        ArrayList<Integer> a = m.getTabNums();
        assertEquals(a.size(),2);
        assertEquals((int)a.get(0),2);
        assertEquals((int)a.get(1),4);
    }

    @Test
    public void no003GetSet() {
        ArrayList<String> s1 = new ArrayList<String>();
        ArrayList<String> s2 = new ArrayList<String>();

        s1.add("a");
        s1.add("b");
        s1.add("c");
        s1.add("d");
        s1.add("e");

        s2.add("a");
        s2.add("c");
        s2.add("f");
        s2.add("e");
        s2.add("g");
        s2.add("h");

        m.setText(2,s1,0);
        m.setText(2,s2,1);
        s2 = m.getText(2,0);
        s1 = m.getText(2,1);

        assertEquals(s1.size(),6);
        assertEquals(s1.get(0),"a");
        assertEquals(s1.get(1),"c");
        assertEquals(s1.get(2),"f");
        assertEquals(s1.get(3),"e");
        assertEquals(s1.get(4),"g");
        assertEquals(s1.get(5),"h");

        assertEquals(s2.size(),5);
        assertEquals(s2.get(0),"a");
        assertEquals(s2.get(1),"b");
        assertEquals(s2.get(2),"c");
        assertEquals(s2.get(3),"d");
        assertEquals(s2.get(4),"e");
    }

    @Test
    public void no004GetSetFile() {


    } //todo

    @Test(expected=IllegalArgumentException.class)
    public void no005WrongTabUse() {
        ArrayList<String> s1 = new ArrayList<String>();
        ArrayList<String> s2 = new ArrayList<String>();
        m.setText(3, s1, 0);
        m.setText(3, s2, 1);
    }

    @Test
    public void no006Compare()
    {
        ArrayList<String> s1 = m.getArrangedText(2,0);
        ArrayList<String> s2 = m.getArrangedText(2,1);
    }
}*/


