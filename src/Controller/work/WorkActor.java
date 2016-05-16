package Controller.work;

import Controller.*;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-16.
 */
public class WorkActor {

    private work w;
    WorkHave res;
    private ArrayList<WorkActor> nextWorkActors;
    public WorkActor(work w)
    {
        this.w=w;
        res=null;
        nextWorkActors = new ArrayList<WorkActor>();
    }
    public WorkActor(work w, Object o[])
    {
        this.w=w;
        this.res=new WorkHave(o);
        nextWorkActors = new ArrayList<WorkActor>();
    }
    public WorkActor(String s) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        this.w = ChangeStringToWork(s);
        this.res=null;
        nextWorkActors = new ArrayList<WorkActor>();
    }
    public WorkActor(String s, Object o[]) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        this.w = ChangeStringToWork(s);
        this.res=new WorkHave(o);
        nextWorkActors = new ArrayList<WorkActor>();
    }
    public WorkActor next(String s) throws ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        WorkActor n = new WorkActor(ChangeStringToWork(s));
        nextWorkActors.add(n);
        return n;
    }
    private work ChangeStringToWork(String s)  throws ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        Object k = Class.forName(s).newInstance();
        if(!(k instanceof work)) throw new ClassNotFoundException(); //todo may define new Exception
        return (work)k;
    }
    private WorkActor giveRes(WorkHave res)
    {
        if(res==null) this.res=res;
        return this;
    }
    public void working() throws Exception {
        WorkHave newRes = new WorkHave(w.whatWillDo(res));
        for(int i = 0; i< nextWorkActors.size(); i++)
        {
            Controller.workdeals.enqueue(giveRes(newRes));
        }
    }
   public Class workClass()
    {
        return w.getClass();
    }

}
