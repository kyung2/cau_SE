package Model;
import java.lang.String;
import java.util.ArrayList;
/**
 * Created by User on 2016-05-14.
 */

abstract public class Model {

    abstract class SavedText
    {
        ArrayList<String> texts;

        SavedText() {
            texts = new ArrayList<String>();
        }
        abstract void ReadFromOuter(String s);
        abstract void WriteFromOuter(String s);

        public String Read(int i) throws IndexOutOfBoundsException
        {
            return new String(texts.get(i));
        }
        public void Write(int i, String s) throws IndexOutOfBoundsException
        {
            texts.set(i,new String(s));
        }
    }

    final int NUMOFTEXTS = 2;
    SavedText codes[];
    ArrayList[] groupsStart;

    Model()
    {
        codes = new SavedText[NUMOFTEXTS];
        groupsStart = new ArrayList[NUMOFTEXTS];
        for(int i=0;i<NUMOFTEXTS;i++) groupsStart[i] = new ArrayList<String>();
    }

    public void textLinking(int i, SavedText s) throws IndexOutOfBoundsException
    {
        if(i<0 || i>=NUMOFTEXTS) throw new IndexOutOfBoundsException();
        codes[i]=s;
    }


}


