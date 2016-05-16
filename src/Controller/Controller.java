package Controller;

import java.util.ArrayList;

import Controller.ControllerWork.*;
import Controller.work.*;
import Model.*;

/**
 * Created by User on 2016-05-15.
 */

public class Controller {

    ArrayList<WorkActor> queue;

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
        queue = new  ArrayList<WorkActor>();
        isWork=false;
        lastDeal= System.currentTimeMillis();
    }

    public void enqueue(WorkActor me)
    {
        queue.add(0,me);
        lastDeal += DEALTIMEGAP;
    }
    private void deal()
    {
        if(System.currentTimeMillis()-lastDeal>DEALTIMEGAP) {
            if (queue.size() != 0) {
                try {
                    queue.get(0).working();
                }
                catch(Exception e)
                {
                    Object o[] = {e, queue.get(0).workClass()};
                    this.enqueue(new WorkActor(new ExceptionDealingWork(),o));
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
                v.launch(args);
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

        m = new ModelUsingFile();
        v = new View(); //todo - change your class extended View

        c= new Controller(m,v);

        c.execute(args);
    }
}



