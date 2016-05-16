package Model.ModelWork;
import Controller.*;
import Controller.work.WorkHave;
import Controller.work.work;

/**
 * Created by User on 2016-05-15.
 */

public class FileOpenWork implements work {

    final private String s1 = "File1.txt";
    final private String s2 = "File2.txt";
    public Object[] whatWillDo(WorkHave res) throws Exception {
        Controller.workdeals.m.open((String)res.pushObj(s1,0),0);
        Controller.workdeals.m.open((String)res.pushObj(s2,1),1);
        return null;
    }
}

