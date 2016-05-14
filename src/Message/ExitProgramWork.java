package Message;

/**
 * Created by User on 2016-05-15.
 */
public class ExitProgramWork implements work{

    ExitProgramWork()
    {

    }
    public void whatWillDo(WorkDealer d) throws Exception
    {
        d.stop();
        // todo - if GUI of d.v(view) is still visualable, then close.
    }
}
