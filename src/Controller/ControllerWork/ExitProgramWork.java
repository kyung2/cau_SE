package Controller.ControllerWork;
import Controller.*;
import Controller.work.*;
/**
 * Created by User on 2016-05-15.
 */

public class ExitProgramWork implements work{


    public Object[] whatWillDo(WorkHave res) throws Exception {
        Controller.workdeals.stop();
        return null;
    }
}

