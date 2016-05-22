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
        ArrayList<String> b1 = new  ArrayList<String>(), b2 = new  ArrayList<String>();
        ArrayList<Integer> aaa;
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

        b1.add("a");
        b1.add("b");
        b1.add("c");
        b1.add("f");
        b1.add("f");
        b1.add("g");

        b2.add("b");
        b2.add("c");
        b2.add("f");
        b2.add("f");
        b2.add("g");
        b2.add("d");

        Model m = new ModelRealize();
        m.newModel(2);
        m.newModel(4);
        c = m.newModel();
        try
        {
            m.setText(2,a1,0);
            m.setText(2,a2,1);
            m.setText(4,b1,0);
            m.setText(4,b2,1);
            m.setText(c,a2,0);
            m.setText(c,a1,1);

            aaa = m.getArrangedGroupNum(2);
            for(int k=0;k<aaa.size();k++)
            {
                System.out.println(aaa.get(k));
            }
            System.out.println("00000");


            aaa = m.getArrangedGroupNum(c);
            for(int k=0;k<aaa.size();k++)
            {
                System.out.println(aaa.get(k));
            }
            System.out.println("00000");

            aaa = m.getArrangedGroupNum(4);
            for(int k=0;k<aaa.size();k++)
            {
                System.out.println(aaa.get(k));
            }
            System.out.println("00000");


            a3 =  m.getArrangedText(2,1);
            System.out.println("-");
            for(int i=0;i<a3.size();i++) System.out.println(a3.get(i));
            System.out.println("-");

            System.out.println("0000");
            m.merge(2,3,false);
            System.out.println("0000");
            aaa = m.getArrangedGroupNum(2);
            for(int k=0;k<aaa.size();k++)
            {
                System.out.println(aaa.get(k));
            }
            System.out.println("00000");
            a3 =  m.getArrangedText(2,0);
            System.out.println("-");
            for(int i=0;i<a3.size();i++) System.out.println(a3.get(i));
            System.out.println("-");
            a3 =  m.getArrangedText(2,1);
            System.out.println("-");
            for(int i=0;i<a3.size();i++) System.out.println(a3.get(i));
            System.out.println("-");

            System.out.println("0000");
            m.merge(2,0,true);
            System.out.println("0000");
            a3 =  m.getArrangedText(2,0);
            System.out.println("-");
            for(int i=0;i<a3.size();i++) System.out.println(a3.get(i));
            System.out.println("-");
            a3 =  m.getArrangedText(2,1);
            System.out.println("-");
            for(int i=0;i<a3.size();i++) System.out.println(a3.get(i));
            System.out.println("-");
            aaa = m.getArrangedGroupNum(2);
            for(int k=0;k<aaa.size();k++)
            {
                System.out.println(aaa.get(k));
            }
            System.out.println("00000");


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
