package Model;

import Model.ModelUnit.LCSsupport.*;

import java.util.ArrayList;

/**
 * Created by User on 2016-05-20.
 */
public class PseudoMain2 {
    static final int changeNum = 3;
    public static void main(String args[]) {

        System.out.println("0000");
        ArrayList<String> a1 = new  ArrayList<String>(), a2 = new  ArrayList<String>(), a3;
        ArrayList<String> b1 = new  ArrayList<String>(), b2 = new  ArrayList<String>();
        ArrayList<Integer> aaa;
        String fp1 = new String("C:\\Users\\User\\Desktop\\a.txt"), fp2 = new String("C:\\Users\\User\\Desktop\\b.txt");
        int c;

        System.out.println("0000");
        a1.add("a");
        a1.add("b");
        a1.add("c");
        a1.add("d");
        a1.add("g");
        a1.add("h");
        a1.add("j");
        a1.add("k");
        a1.add("l");
        a1.add("o");
        a1.add("q");
        a1.add("t");



        a2.add("a");
        a2.add("d");
        a2.add("e");
        a2.add("f");
        a2.add("g");
        a2.add("i");
        a2.add("j");
        a2.add("n");
        a2.add("o");
        a2.add("r");
        a2.add("s");
        a2.add("t");

        System.out.println("0000");
        ModelInterface m = ModelRealize.getInstance();
        System.out.println("0000");
        m.newModel(2);
        c = m.newModel();
        System.out.println("0000");
        try
        {

            m.setText(2,a1,0);
            m.setText(2,a2,1);
            System.out.println("0000");

            a3 = m.getText(2,0);
            System.out.println("2,0 texts");
            for(int i=0;i<a3.size();i++) System.out.println(a3.get(i));
            System.out.println("-\n\n");

            a3 = m.getText(2,1);
            System.out.println("2,1 texts");
            for(int i=0;i<a3.size();i++) System.out.println(a3.get(i));
            System.out.println("-\n\n");

            a3 = m.getArrangedText(2,0);
            System.out.println("2,0 Atexts");
            for(int i=0;i<a3.size();i++) System.out.println(a3.get(i));
            System.out.println("-\n\n");

            a3 = m.getArrangedText(2,1);
            System.out.println("2,1 Atexts");
            for(int i=0;i<a3.size();i++) System.out.println(a3.get(i));
            System.out.println("-\n\n");

            aaa = m.getArrangedGroupSpace(2);
            System.out.println("2 gnum");
            for(int i=0;i<aaa.size();i++) System.out.println(aaa.get(i));
            System.out.println("-\n\n");

            m.mergeByGroup(2,changeNum,true);
            a3 = m.getText(2,0);
            System.out.println("2,0 texts");
            for(int i=0;i<a3.size();i++) System.out.println(a3.get(i));
            System.out.println("-\n\n");

            a3 = m.getText(2,1);
            System.out.println("2,1 texts");
            for(int i=0;i<a3.size();i++) System.out.println(a3.get(i));
            System.out.println("-\n\n");

            a3 = m.getArrangedText(2,0);
            System.out.println("2,0 Atexts");
            for(int i=0;i<a3.size();i++) System.out.println(a3.get(i));
            System.out.println("-\n\n");

            a3 = m.getArrangedText(2,1);
            System.out.println("2,1 Atexts");
            for(int i=0;i<a3.size();i++) System.out.println(a3.get(i));
            System.out.println("-\n\n");

            aaa = m.getArrangedGroupSpace(2);
            System.out.println("2 gnum");
            for(int i=0;i<aaa.size();i++) System.out.println(aaa.get(i));
            System.out.println("-\n\n");

            System.out.println(LCSClassEnum.find(LCSClassEnum.LCSArrangeLine_sNonArrangeLineNum));





        }
        catch(Exception e)
        {
            System.out.println(e.getLocalizedMessage());

        }

    }
}
