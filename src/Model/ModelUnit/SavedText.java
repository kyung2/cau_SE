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
}

