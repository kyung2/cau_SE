package Message;

/**
 * Created by User on 2016-05-15.
 */
public class ExceptionDealingWork implements work{

    Class m;
    Exception e;
    ExceptionDealingWork(Class m, Exception e)
    {
        this.m=m;
        this.e=e;
    }
    public void whatWillDo(WorkDealer d) throws Exception
    {
        //Todo
    }
}
