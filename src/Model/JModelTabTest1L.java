package Model;


import Model.ModelException.MergeLineIllegalException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class JModelTabTest1L {



    ModelInterface m;
    @Before
    public void setUp() throws Exception {

        m = ModelRealize.getInstance();
        m.newModel(0);


    }

    @After
    public void setDown() throws Exception {
        m.closeModelAll();

    }
    void testData()
    {
        ArrayList<String> a1 = new  ArrayList<String>(), a2 = new  ArrayList<String>();

        String[] sa1 = {"a", "b", "c", "d","g","h","j","k","l","o","q","t"};
        String[] sa2 = {"a","d", "e","f","g","i","j","n","o","r","s","t"};

        for(int i=0;i<sa1.length;i++)
            a1.add(sa1[i]);
        for(int i=0;i<sa2.length;i++)
            a2.add(sa2[i]);
        m.getUnit(0).textSend(0,a1);
        m.getUnit(0).textSend(1,a2);
    }

    void check(String[] sra1, String[] sra2, String[] sa1, String[] sa2, Integer[] ia1, Integer[] ia2)
    {
        ArrayList<String> s1, s2, sr1, sr2;
        ArrayList<Integer> i1, i2;

        s1 = m.getUnit(0).getArrangedText(0);
        s2 = m.getUnit(0).getArrangedText(1);
        sr1 = m.getUnit(0).textReceive(0);
        sr2 = m.getUnit(0).textReceive(1);
        i1 = m.getUnit(0).getArrangedGroup();
        i2 = m.getUnit(0).getArrangedGroupSpace();



        assertEquals(s1.size(),sa1.length);
        for(int i=0;i<sa1.length;i++)
            assertEquals(s1.get(i),sa1[i]);

        assertEquals(s2.size(),sa2.length);
        for(int i=0;i<sa2.length;i++)
            assertEquals(s2.get(i),sa2[i]);

        assertEquals(sr1.size(),sra1.length);
        for(int i=0;i<sra1.length;i++)
            assertEquals(sr1.get(i),sra1[i]);

        assertEquals(sr2.size(),sra2.length);
        for(int i=0;i<sra2.length;i++)
            assertEquals(sr2.get(i),sra2[i]);

        assertEquals(i1.size(),ia1.length);
        for(int i=0;i<ia1.length;i++)
            assertEquals(i1.get(i),ia1[i]);

        assertEquals(i2.size(),ia2.length);
        for(int i=0;i<ia2.length;i++)
            assertEquals(i2.get(i),ia2[i]);
    }

    @Test
    public void compareAndCheck() {
        testData();

        String[] sra1 = {"a", "b", "c", "d", "g","h","j","k","l","o","q","t"};
        String[] sra2 = {"a", "d", "e","f","g","i","j","n","o","r","s","t"};
        String[] sa1 = {"a", "b", "c", "d", "","","g","h","j","k","l","o","q","","t"};
        String[] sa2 = {"a", "", "", "d", "e","f","g","i","j","n","","o","r","s","t"};
        Integer[] ia1 = {0,1,1,2,3,3,4,5,6,7,7,8,9,9,10};
        Integer[] ia2 = {1,2,1,2,1,1,1,2,1,2,1};


        check(sra1, sra2, sa1, sa2, ia1, ia2);
    }

    @Test(expected= MergeLineIllegalException.class)
    public void MergeAndCheck_G0R() {
        testData();
        m.getUnit(0).mergeBylineNum(0, true);
    }

    @Test(expected= MergeLineIllegalException.class)
    public void MergeAndCheck_G0L() {
        testData();
        m.getUnit(0).mergeBylineNum(0, false);
    }

    @Test
    public void MergeAndCheck_G1R() {
        testData();
        m.getUnit(0).mergeByGroupNum(1, true);


        String[] sra1 = {"a", "b", "c", "d","g","h","j","k","l","o","q","t"};
        String[] sra2 = {"a", "b", "c", "d", "e","f","g","i","j","n","o","r","s","t"};
        String[] sa1 = {"a", "b", "c", "d", "","","g","h","j","k","l","o","q","","t"};
        String[] sa2 = {"a", "b", "c", "d", "e","f","g","i","j","n","","o","r","s","t"};
        Integer[] ia1 = {0,0,0,0,1,1,2,3,4,5,5,6,7,7,8};
        Integer[] ia2 = {4,2,1,1,1,2,1,2,1};

        check(sra1, sra2, sa1, sa2, ia1, ia2);
    }

    @Test
    public void MergeAndCheck_G1L() {
        testData();
        m.getUnit(0).mergeByGroupNum(1, false);

        String[] sra1 = {"a", "d","g","h","j","k","l","o","q","t"};
        String[] sra2 = {"a", "d", "e","f","g","i","j","n","o","r","s","t"};
        String[] sa1 = {"a", "d", "","","g","h","j","k","l","o","q","","t"};
        String[] sa2 = {"a", "d", "e","f","g","i","j","n","","o","r","s","t"};
        Integer[] ia1 = {0,0,1,1,2,3,4,5,5,6,7,7,8};
        Integer[] ia2 = {2,2,1,1,1,2,1,2,1};

        check(sra1, sra2, sa1, sa2, ia1, ia2);
    }

    @Test
    public void MergeAndCheck_G2R() {
        testData();
        m.getUnit(0).mergeByGroupNum(2, true);


        String[] sra1 = {"a", "b", "c", "d","g","h","j","k","l","o","q","t"};
        String[] sra2 = {"a", "b", "c", "d", "e","f","g","i","j","n","o","r","s","t"};
        String[] sa1 = {"a", "b", "c", "d", "","","g","h","j","k","l","o","q","","t"};
        String[] sa2 = {"a", "b", "c", "d", "e","f","g","i","j","n","","o","r","s","t"};
        Integer[] ia1 = {0,0,0,0,1,1,2,3,4,5,5,6,7,7,8};
        Integer[] ia2 = {4,2,1,1,1,2,1,2,1};

        check(sra1, sra2, sa1, sa2, ia1, ia2);
    }

    @Test
    public void MergeAndCheck_G2L() {
        testData();
        m.getUnit(0).mergeByGroupNum(2, false);

        String[] sra1 = {"a", "d","g","h","j","k","l","o","q","t"};
        String[] sra2 = {"a", "d", "e","f","g","i","j","n","o","r","s","t"};
        String[] sa1 = {"a", "d", "","","g","h","j","k","l","o","q","","t"};
        String[] sa2 = {"a", "d", "e","f","g","i","j","n","","o","r","s","t"};
        Integer[] ia1 = {0,0,1,1,2,3,4,5,5,6,7,7,8};
        Integer[] ia2 = {2,2,1,1,1,2,1,2,1};

        check(sra1, sra2, sa1, sa2, ia1, ia2);
    }

    @Test(expected= MergeLineIllegalException.class)
    public void MergeAndCheck_G3R() {
        testData();
        m.getUnit(0).mergeByGroupNum(3, true);
    }

    @Test(expected= MergeLineIllegalException.class)
    public void MergeAndCheck_G3L() {
        testData();
        m.getUnit(0).mergeByGroupNum(3, false);
    }

    @Test
    public void MergeAndCheck_G4R() {
        testData();
        m.getUnit(0).mergeByGroupNum(4, true);

        String[] sra1 = {"a", "b", "c", "d","g","h","j","k","l","o","q","t"};
        String[] sra2 = {"a", "d", "g","i","j","n","o","r","s","t"};
        String[] sa1 = {"a", "b", "c", "d","g","h","j","k","l","o","q","","t"};
        String[] sa2 = {"a", "", "", "d", "g","i","j","n","","o","r","s","t"};
        Integer[] ia1 = {0,1,1,2,2,3,4,5,5,6,7,7,8};
        Integer[] ia2 = {1,2,2,1,1,2,1,2,1};

        check(sra1, sra2, sa1, sa2, ia1, ia2);
    }

    @Test
    public void MergeAndCheck_G4L() {
        testData();
        m.getUnit(0).mergeByGroupNum(4, false);

        String[] sra1 = {"a", "b","c","d","e","f","g","h","j","k","l","o","q","t"};
        String[] sra2 = {"a","d","e","f","g","i","j","n", "o","r","s","t"};
        String[] sa1 = {"a", "b","c","d","e","f","g","h","j","k","l","o","q","", "t"};
        String[] sa2 = {"a", "", "", "d","e","f","g","i","j","n","", "o","r","s","t"};
        Integer[] ia1 = {0,1,1,2,2,2,2,3,4,5,5,6,7,7,8};
        Integer[] ia2 = {1,2,4,1,1,2,1,2,1};

        check(sra1, sra2, sa1, sa2, ia1, ia2);
    }

    @Test
    public void MergeAndCheck_G5R() {
        testData();
        m.getUnit(0).mergeByGroupNum(5, true);

        String[] sra1 = {"a", "b", "c", "d","g","h","j","k","l","o","q","t"};
        String[] sra2 = {"a", "d", "g","i","j","n","o","r","s","t"};
        String[] sa1 = {"a", "b", "c", "d","g","h","j","k","l","o","q","","t"};
        String[] sa2 = {"a", "", "", "d", "g","i","j","n","","o","r","s","t"};
        Integer[] ia1 = {0,1,1,2,2,3,4,5,5,6,7,7,8};
        Integer[] ia2 = {1,2,2,1,1,2,1,2,1};

        check(sra1, sra2, sa1, sa2, ia1, ia2);
    }

    @Test
    public void MergeAndCheck_G5L() {
        testData();
        m.getUnit(0).mergeByGroupNum(5, false);

        String[] sra1 = {"a", "b","c","d","e","f","g","h","j","k","l","o","q","t"};
        String[] sra2 = {"a","d","e","f","g","i","j","n", "o","r","s","t"};
        String[] sa1 = {"a", "b","c","d","e","f","g","h","j","k","l","o","q","", "t"};
        String[] sa2 = {"a", "", "", "d","e","f","g","i","j","n","", "o","r","s","t"};
        Integer[] ia1 = {0,1,1,2,2,2,2,3,4,5,5,6,7,7,8};
        Integer[] ia2 = {1,2,4,1,1,2,1,2,1};

        check(sra1, sra2, sa1, sa2, ia1, ia2);
    }

    @Test(expected= MergeLineIllegalException.class)
    public void MergeAndCheck_G6R() {
        testData();
        m.getUnit(0).mergeByGroupNum(6, true);
    }

    @Test(expected= MergeLineIllegalException.class)
    public void MergeAndCheck_G6L() {
        testData();
        m.getUnit(0).mergeByGroupNum(6, false);
    }

    @Test
    public void MergeAndCheck_G7R() {
        testData();
        m.getUnit(0).mergeByGroupNum(7, true);

        String[] sra1 = {"a", "b","c","d","g","h","j","k","l","o","q","t"};
        String[] sra2 = {"a","d","e","f","g","h","j","n", "o","r","s","t"};
        String[] sa1 = {"a", "b","c","d","","","g","h","j","k","l","o","q","", "t"};
        String[] sa2 = {"a", "", "", "d","e","f","g","h","j","n","", "o","r","s","t"};
        Integer[] ia1 = {0,1,1,2,3,3,4,4,4,5,5,6,7,7,8};
        Integer[] ia2 = {1,2,1,2,3,2,1,2,1};

        check(sra1, sra2, sa1, sa2, ia1, ia2);
    }

    @Test
    public void MergeAndCheck_G7L() {
        testData();
        m.getUnit(0).mergeByGroupNum(7, false);

        String[] sra1 = {"a", "b","c","d","g","i","j","k","l","o","q","t"};
        String[] sra2 = {"a","d","e","f","g","i","j","n", "o","r","s","t"};
        String[] sa1 = {"a", "b","c","d","","","g","i","j","k","l","o","q","", "t"};
        String[] sa2 = {"a", "", "", "d","e","f","g","i","j","n","", "o","r","s","t"};
        Integer[] ia1 = {0,1,1,2,3,3,4,4,4,5,5,6,7,7,8};
        Integer[] ia2 = {1,2,1,2,3,2,1,2,1};

        check(sra1, sra2, sa1, sa2, ia1, ia2);
    }

    @Test(expected= MergeLineIllegalException.class)
    public void MergeAndCheck_G8R() {
        testData();
        m.getUnit(0).mergeByGroupNum(8, true);
    }

    @Test(expected= MergeLineIllegalException.class)
    public void MergeAndCheck_G8L() {
        testData();
        m.getUnit(0).mergeByGroupNum(8, false);
    }

    @Test
    public void MergeAndCheck_G9R() {
        testData();
        m.getUnit(0).mergeByGroupNum(9, true);

        String[] sra1 = {"a", "b", "c", "d", "g","h","j","k","l","o","q","t"};
        String[] sra2 = {"a", "d", "e","f","g","i","j","k","l","o","r","s","t"};
        String[] sa1 = {"a", "b", "c", "d", "","","g","h","j","k","l","o","q","","t"};
        String[] sa2 = {"a", "", "", "d", "e","f","g","i","j","k","l","o","r","s","t"};
        Integer[] ia1 = {0,1,1,2,3,3,4,5,6,6,6,6,7,7,8};
        Integer[] ia2 = {1,2,1,2,1,1,4,2,1};

        check(sra1, sra2, sa1, sa2, ia1, ia2);
    }

    @Test
    public void MergeAndCheck_G9L() {
        testData();
        m.getUnit(0).mergeByGroupNum(9, false);

        String[] sra1 = {"a", "b", "c", "d", "g","h","j","n","o","q","t"};
        String[] sra2 = {"a", "d", "e","f","g","i","j","n","o","r","s","t"};
        String[] sa1 = {"a", "b", "c", "d", "","","g","h","j","n","o","q","","t"};
        String[] sa2 = {"a", "", "", "d", "e","f","g","i","j","n","o","r","s","t"};
        Integer[] ia1 = {0,1,1,2,3,3,4,5,6,6,6,7,7,8};
        Integer[] ia2 = {1,2,1,2,1,1,3,2,1};

        check(sra1, sra2, sa1, sa2, ia1, ia2);
    }

    @Test
    public void MergeAndCheck_GAR() {
        testData();
        m.getUnit(0).mergeByGroupNum(10, true);

        String[] sra1 = {"a", "b", "c", "d", "g","h","j","k","l","o","q","t"};
        String[] sra2 = {"a", "d", "e","f","g","i","j","k","l","o","r","s","t"};
        String[] sa1 = {"a", "b", "c", "d", "","","g","h","j","k","l","o","q","","t"};
        String[] sa2 = {"a", "", "", "d", "e","f","g","i","j","k","l","o","r","s","t"};
        Integer[] ia1 = {0,1,1,2,3,3,4,5,6,6,6,6,7,7,8};
        Integer[] ia2 = {1,2,1,2,1,1,4,2,1};

        check(sra1, sra2, sa1, sa2, ia1, ia2);
    }

    @Test
    public void MergeAndCheck_GAL() {
        testData();
        m.getUnit(0).mergeByGroupNum(10, false);

        String[] sra1 = {"a", "b", "c", "d", "g","h","j","n","o","q","t"};
        String[] sra2 = {"a", "d", "e","f","g","i","j","n","o","r","s","t"};
        String[] sa1 = {"a", "b", "c", "d", "","","g","h","j","n","o","q","","t"};
        String[] sa2 = {"a", "", "", "d", "e","f","g","i","j","n","o","r","s","t"};
        Integer[] ia1 = {0,1,1,2,3,3,4,5,6,6,6,7,7,8};
        Integer[] ia2 = {1,2,1,2,1,1,3,2,1};

        check(sra1, sra2, sa1, sa2, ia1, ia2);
    }

    @Test(expected= MergeLineIllegalException.class)
    public void MergeAndCheck_GBR() {
        testData();
        m.getUnit(0).mergeByGroupNum(11, true);
    }

    @Test(expected= MergeLineIllegalException.class)
    public void MergeAndCheck_GBL() {
        testData();
        m.getUnit(0).mergeByGroupNum(11, false);
    }

    @Test
    public void MergeAndCheck_GCR() {
        testData();
        m.getUnit(0).mergeByGroupNum(12, true);

        String[] sra1 = {"a", "b", "c", "d", "g","h","j","k","l","o","q","t"};
        String[] sra2 = {"a", "d", "e","f","g","i","j","n","o","q","t"};
        String[] sa1 = {"a", "b", "c", "d", "","","g","h","j","k","l","o","q","t"};
        String[] sa2 = {"a", "", "", "d", "e","f","g","i","j","n","","o","q","t"};
        Integer[] ia1 = {0,1,1,2,3,3,4,5,6,7,7,8,8,8};
        Integer[] ia2 = {1,2,1,2,1,1,1,2,3};

        check(sra1, sra2, sa1, sa2, ia1, ia2);
    }

    @Test
    public void MergeAndCheck_GCL() {
        testData();
        m.getUnit(0).mergeByGroupNum(12, false);

        String[] sra1 = {"a", "b", "c", "d", "g","h","j","k","l","o","r","s","t"};
        String[] sra2 = {"a", "d", "e","f","g","i","j","n","o","r","s","t"};
        String[] sa1 = {"a", "b", "c", "d", "","","g","h","j","k","l","o","r","s","t"};
        String[] sa2 = {"a", "", "", "d", "e","f","g","i","j","n","","o","r","s","t"};
        Integer[] ia1 = {0,1,1,2,3,3,4,5,6,7,7,8,8,8,8};
        Integer[] ia2 = {1,2,1,2,1,1,1,2,4};

        check(sra1, sra2, sa1, sa2, ia1, ia2);
    }
    @Test
    public void MergeAndCheck_GDR() {
        testData();
        m.getUnit(0).mergeByGroupNum(13, true);

        String[] sra1 = {"a", "b", "c", "d", "g","h","j","k","l","o","q","t"};
        String[] sra2 = {"a", "d", "e","f","g","i","j","n","o","q","t"};
        String[] sa1 = {"a", "b", "c", "d", "","","g","h","j","k","l","o","q","t"};
        String[] sa2 = {"a", "", "", "d", "e","f","g","i","j","n","","o","q","t"};
        Integer[] ia1 = {0,1,1,2,3,3,4,5,6,7,7,8,8,8};
        Integer[] ia2 = {1,2,1,2,1,1,1,2,3};

        check(sra1, sra2, sa1, sa2, ia1, ia2);
    }

    @Test
    public void MergeAndCheck_GDL() {
        testData();
        m.getUnit(0).mergeByGroupNum(13, false);

        String[] sra1 = {"a", "b", "c", "d", "g","h","j","k","l","o","r","s","t"};
        String[] sra2 = {"a", "d", "e","f","g","i","j","n","o","r","s","t"};
        String[] sa1 = {"a", "b", "c", "d", "","","g","h","j","k","l","o","r","s","t"};
        String[] sa2 = {"a", "", "", "d", "e","f","g","i","j","n","","o","r","s","t"};
        Integer[] ia1 = {0,1,1,2,3,3,4,5,6,7,7,8,8,8,8};
        Integer[] ia2 = {1,2,1,2,1,1,1,2,4};

        check(sra1, sra2, sa1, sa2, ia1, ia2);
    }

    @Test(expected= MergeLineIllegalException.class)
    public void MergeAndCheck_GER() {
        testData();
        m.getUnit(0).mergeByGroupNum(14, true);
    }

    @Test(expected= MergeLineIllegalException.class)
    public void MergeAndCheck_GEL() {
        testData();
        m.getUnit(0).mergeByGroupNum(14, false);
    }

    @Test(expected=MergeLineIllegalException.class)
    public void MergeAndCheck_GmR() {
        testData();
        m.getUnit(0).mergeByGroupNum(-1, true);
    }

    @Test(expected= MergeLineIllegalException.class)
    public void MergeAndCheck_GmL() {
        testData();
        m.getUnit(0).mergeByGroupNum(-1, false);
    }

    @Test(expected=MergeLineIllegalException.class)
    public void MergeAndCheck_GoR() {
        testData();
        m.getUnit(0).mergeByGroupNum(15, true);
    }

    @Test(expected= MergeLineIllegalException.class)
    public void MergeAndCheck_GoL() {
        testData();
        m.getUnit(0).mergeByGroupNum(15, false);
    }

}


