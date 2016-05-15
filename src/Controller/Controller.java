package Controller;

import java.util.ArrayList;

import Model.*;

/**
 * Created by User on 2016-05-15.
 */
/*
public class Controller {

    ArrayList<work> queue;

    public Controller c;
    public Model m;
    public View v; //Todo make View class
    private static boolean isWork;
    private long lastDeal;
    private final long DEALTIMEGAP=50;
    public static Controller workdeals;

    Controller(Model m, View v)
    {
        this.m = m;
        this.v = v;
        this.c = this;
        queue = new  ArrayList<work>();
        isWork=false;
        lastDeal= System.currentTimeMillis();
    }
    ~Controller()
    {
        stop();
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
                    queue.get(0).whatWillDo();
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
                Controller.workdeals=c;
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

        // todo - m,v의 오브젝트 생성

        c=Controller(m,v);

        c.execute(args);
    }
}
*/


