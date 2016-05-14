package Message;

import java.util.ArrayList;

/**
 * Created by User on 2016-05-15.
 */
public class DataRequireWork implements work{

    int n;
    DataRequireWork(int n)
    {
        this.n=n;
    }
    public void whatWillDo() throws Exception {
        ArrayList<String> s = WorkDealer.workdeals.m.textSend(n);
        // todo - make class DataSendToPanelWork implements work, and call d.enqueue(new DataSendToPanelWork(s,n)).
    }
}
