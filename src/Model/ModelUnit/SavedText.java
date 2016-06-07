package Model.ModelUnit;

import java.util.ArrayList;

/**
 * Created by User on 2016-05-19.
 */

class SavedText
{
    protected ArrayList<String> lines;
    protected String filepath;
    protected SavedText() {
        lines = new ArrayList<String>();
        filepath = null;
    }
    void deleteblank()
    {
        String line;
        ArrayList<String> newLines = new ArrayList<String>();
        for(int i=0;i<lines.size();i++)
        {
            line = lines.get(i);
            while(line.length()>0?line.charAt(line.length()-1)==' ':false)
            {
                if(line.length()!=1)line = line.substring(0,line.length()-2);
                else line = "";
            }
            newLines.add(line);
        }
        lines = newLines;
    }
}

