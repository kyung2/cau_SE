package Message;

import java.util.ArrayList;

import Model.*;

/**
 * Created by User on 2016-05-15.
 */
/*
public class WorkDealer {

    ArrayList<work> queue;

    public Controller c;
    public Model m;
    public View v;
    private static boolean isWork;
    private long lastDeal;
    private final long DEALTIMEGAP=50;
    public static WorkDealer workdeals;

    WorkDealer(Model m, View v, Controller c)
    {
        this.m=m;
        this.v=v;
        this.c=c;
        queue = new  ArrayList<work>();
        isWork=false;
        lastDeal= System.currentTimeMillis();
    }

    public void enqueue(work me)
    {
        queue.add(0,me);
        lastDeal += DEALTIMEGAP;
    }
    private void deal()
    {
        if(System.currentTimeMillis()-lastDeal>DEALTIMEGAP) {
            if (queue.size() != 0) {
                try {
                    queue.get(0).whatWillDo(this);
                }
                catch(Exception e)
                {
                    this.enqueue(new ExceptionDealingWork(queue.get(0).getClass(),e));
                }
                finally {
                    queue.remove(0);
                }
            }
        }
    }

    public void stop()
    {
        isWork=false;
    }




    public static void main(String args)
    {

        Model m;
        View v;
        Controller c;
        WorkDealer d;

        // m,v,c의 오브젝트 생성

        d=WorkDealer(m,v,c); // include c.setWorkDealer(d);

        d.isWork=true;
        WorkDealer.workdeals=d;
        while (d.isWork) {
            d.deal();
        }
    }
}


{
public void execute(string args[])
{
if(d.isWork==false)
    {
        d.isWork=true;
        WorkDealer.workdeals=d;
        c.launch(args);
        while (d.isWork) {
            d.deal();
        }
    }
}
*/
