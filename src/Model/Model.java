package Model;
import java.lang.String;
import java.util.ArrayList;
/**
 * Created by User on 2016-05-14.
 */

abstract public class Model {

    abstract class SavedText
    {
        ArrayList<String> lines;

        SavedText() {
            lines = new ArrayList<String>();
        }
        abstract void ReadFromOuter(String s);
        abstract void WriteFromOuter(String s);

        public String Read(int i) throws IndexOutOfBoundsException
        {
            return new String(lines.get(i));
        }
        public void Write(int i, String s) throws IndexOutOfBoundsException
        {
            lines.set(i,new String(s));
        }
        public ArrayList<String> ReadAll()
        {
            return new ArrayList<String>(lines);
        }
        public void WriteAll(ArrayList<String> s)
        {
            lines = new ArrayList<String>(s);
        }
    }

    public final int NUMOFTEXTS = 2;
    SavedText codes[];
    ArrayList[] groupsStart;

    public Model()
    {
        codes = new SavedText[NUMOFTEXTS];
        groupsStart = new ArrayList[NUMOFTEXTS];
        for(int i=0;i<NUMOFTEXTS;i++)
        {
            groupsStart[i] = new ArrayList<String>();
        }
    }

    public Model(SavedText[] s) throws IndexOutOfBoundsException
    {
        this();
        for(int i=0;i<NUMOFTEXTS;i++) codes[i]=s[i];
    }

    public void textLinking(int i, SavedText s) throws IndexOutOfBoundsException
    {
        codes[i]=s;
    }






}


