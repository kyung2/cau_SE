package Model;

import Model.LCSsupport.LCSGrouping;

import java.util.ArrayList;

/**
 * Created by User on 2016-05-20.
 */
public class PseudoMain {
    public static void main(String args[])
    {
        SavedText s1 = new ModelUnitUsingFile.SavedTextCanFileRR(), s2 = new ModelUnitUsingFile.SavedTextCanFileRR();
        ArrayList<String> a1 = new  ArrayList<String>(), a2 = new  ArrayList<String>();
        ArrayList<Integer>[][] aaa;

        a1.add("a");
        a1.add("b");
        a1.add("c");
        a1.add("d");
        a1.add("e");
        a1.add("f");
        a1.add("g");

        a2.add("b");
        a2.add("c");
        a2.add("f");
        a2.add("f");
        a2.add("g");
        a2.add("d");

        s1.WriteAll(a1);
        s2.WriteAll(a2);

        aaa = s1.LCSMethod(s2);

        for(int i = 0; i< LCSGrouping.groupXSize; i++)
        {
            System.out.println(i + "th\n\n");
            for(int j=0;j<2;j++)
            {
                System.out.println(j + " text");
                for(int k=0;k<aaa[i][j].size();k++)
                {
                    System.out.println(aaa[i][j].get(k));
                }
                System.out.println("");
            }
            System.out.println("\n\n");
        }
    }
}
