package Model.ModelWork;
import Controller.*;
import Controller.work.*;

import java.util.ArrayList;

/**
 * Created by User on 2016-05-15.
 */

public class DataRequireWork implements work {



    @Override
    public Object[] whatWillDo(WorkHave res) throws Exception {
        ArrayList<String> s1 = Controller.workdeals.m.textSend(0);
        ArrayList<String> s2 = Controller.workdeals.m.textSend(1);
        Object[] o = {s1,s2};
        return o;
    }
}

