package Model;

import Model.LCSsupport.*;

import java.util.ArrayList;

import static Model.ModelUnitUsingFile.SavedTextCanFileRR;

/**
 * Created by User on 2016-05-20.
 */
public class PseudoMain2 {
    public static void main(String args[]) {

        ArrayList<String> a1 = new  ArrayList<String>(), a2 = new  ArrayList<String>(), a3;
        ArrayList<Integer>[][] aaa;
        int c;

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

        Model m = new ModelRealize();
        m.newModel(2);
        c = m.newModel();
        try
        {
            m.setText(2,a1,0);
            m.setText(2,a2,1);
            m.setText(c,a2,0);
            m.setText(c,a1,1);
            System.out.println("A");
            aaa = m.getGroupInfo(2);
            System.out.println("B");


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
            }


            aaa = m.getGroupInfo(c);
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
            }

            a3 =  m.getArrangedText(2,1);
            System.out.println("-");
            for(int i=0;i<a3.size();i++) System.out.println(a3.get(i));
            System.out.println("-");


            m.closeModel(2);
            m.closeModel(c);

            System.out.println(LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sNonArrangeLineNum));





        }
        catch(Exception e)
        {
            System.out.println("!");

        }

    }
}
