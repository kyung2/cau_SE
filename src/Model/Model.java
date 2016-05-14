package Model;
import java.lang.String;
import java.util.ArrayList;
/**
 * Created by User on 2016-05-14.
 */
abstract public class Model {

    static abstract class SavedText
    {
        ArrayList<String> texts;

        SavedText() {
            texts = new ArrayList<String>();
        }
        abstract void Read(String s);
        abstract void Write(String s);
    }

    SavedText t[];
    ArrayList[] groupsStart;

    Model()
    {
        t = new SavedText[2];
        groupsStart = new ArrayList[2];
        groupsStart[0] = new ArrayList<String>();
        groupsStart[1] = new ArrayList<String>();
    }

}


