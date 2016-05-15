package Message;

import java.util.ArrayList;

import Model.*;

/**
 * Created by User on 2016-05-15.
 */
/*
public class WorkDealer {

    ArrayList<work> queue;

    public Controller c; //Todo make Controller class
    public Model m;
    public View v; //Todo make View class
    private static boolean isWork;
    private long lastDeal;
    private final long DEALTIMEGAP=50;
    public static WorkDealer workdeals;
    private final WorkDealer thisis = this;

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

    class ExecuteRunnable implements Runnable {

        ExecuteRunnable(String args[])
        {
            this.args=args.clone();
        }
        String args[];
        public void run() {
            if(!isWork)
            {
                isWork=true;
                WorkDealer.workdeals=thisis;
                v.launch(args); //Todo make method what do launch
                while (workdeals.isWork) {
                    workdeals.deal();
                }
                whenExit();
            }
        }

    }

    private void whenExit()
    {
        //Todo make method when program exit
    }
}
    public void execute(String args[])
    {
        Thread t = new Thread(new ExecuteRunnable(args));
        t.start();
    }

    public void stop()
    {
        isWork=false;
    }


    public static void main(String args[])
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

*/
