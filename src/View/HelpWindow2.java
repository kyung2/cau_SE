package View;

        import javafx.application.Application;
        import java.io.*;
import java.io.IOException;
/**
 * Created by hyunkyung on 2016-06-01.
 */

/*이론상은 되어야하는데 안됨 추후 확인 */
public class HelpWindow2 {
    public void HelpWindow() throws IOException {
        Runtime run = Runtime.getRuntime();
        try {
            Process p = Runtime.getRuntime().exec("helper/simplemergehelper.chm");
            p.waitFor();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}