package Model.ModelUnit;

import java.util.ArrayList;

/**
 * Created by User on 2016-05-27.
 */
class ModelUnitData {

    public static final int NUMOFTEXTS = 2;
    protected SavedText[] codes;
    protected ArrayList[][] group;
    protected ArrayList[] arrangedString;

    ModelUnitData()
    {
        assert NUMOFTEXTS > 1;
        SavedText[] s = {new SavedText(), new SavedText()};
        codes = s;
        group = null;
        arrangedString = null;
    }

    protected void groupNull()
    {
        group = null;
        arrangedString = null;
        groupChange();
    }
    protected void textChange()
    {
        //todo - give notice that text change
    }
    protected void groupChange()
    {
        //todo - give notice that text change
    }
}
