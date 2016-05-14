package Message;

import java.util.ArrayList;

import Model.*;

/**
 * Created by User on 2016-05-15.
 */
public class WorkDealer {

    ArrayList<work> queue;

    Controller c;
    Model m;
    View v;
    private boolean isWork;
    private long lastDeal;
    private final long DEALTIMEGAP=50;

    WorkDealer(Model m, View v, Controller c)
    {
        this.m=m;
        this.v=v;
        this.c=c;
        queue = new  ArrayList<work>();
        isWork=true;
        lastDeal= System.currentTimeMillis();
    }

    void enqueue(work me)
    {
        queue.add(0,me);
        lastDeal += DEALTIMEGAP;
    }
    void deal()
    {
        if(System.currentTimeMillis()-lastDeal>DEALTIMEGAP) {
            if (queue.size() != 0) {
                queue.get(0).whatWillDo(this);
                queue.remove(0);
            }
        }
    }

    public static void main(String args)
    {

        Model m;
        View v;
        Controller c;
        WorkDealer d;

        // m,v,c의 오브젝트 생성

        d=WorkDealer(m,v,c);

        while(d.isWork)
        {
            d.deal();
        }
    }
}
