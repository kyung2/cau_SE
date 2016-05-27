package Model.ModelUnit;

import Model.LCSsupport.LCSClassEnum;
import Model.LCSsupport.LCSGrouping;
import Model.Model;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-19.
 */

public class SavedText
{
    protected ArrayList<String> lines;
    protected String filepath;
    protected SavedText() {
        lines = new ArrayList<String>();
        filepath = null;
    }
}

