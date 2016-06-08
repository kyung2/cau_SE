package View;

import java.awt.*;
import java.io.*;

/**
 * HelpWindow
 * Created by hyunkyung on 2016-06-01.
 *
 * @author Hyunkyung Choi
 */
public class HelpWindow {

    /**
     * Help window.
     *
     * @throws Exception the exception
     */
    public void HelpWindow() throws Exception {
        File helperhtml =  new File("WebHelp/index.htm");
        Desktop.getDesktop().browse(helperhtml.toURI());

    }
}