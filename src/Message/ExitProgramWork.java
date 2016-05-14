package Message;

/**
 * Created by User on 2016-05-15.
 */
public class ExitProgramWork implements work{

    ExitProgramWork()
    {

    }
    public void whatWillDo() throws Exception
    {
        WorkDealer.workdeals.stop();
        // todo - if GUI of d.v(view) is still visualable, then close.
    }
}
