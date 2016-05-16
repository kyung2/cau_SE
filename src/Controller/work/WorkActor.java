package Controller.work;

import Controller.*;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-16.
 */
public class WorkActor {

    private work w;
    WorkHave res;
    private ArrayList<WorkActor> nextWorkActor;
    public WorkActor(work w)
    {
        this.w=w;
        res=null;
        nextWorkActor = new ArrayList<WorkActor>();
    }
    public WorkActor(work w, Object o[])
    {
        this.w=w;
        this.res=new WorkHave(o);
        nextWorkActor = new ArrayList<WorkActor>();
    }
    private WorkActor giveRes(WorkHave res)
    {
        if(res==null) this.res=res;
        return this;
    }
    public void working() throws Exception {
        WorkHave newRes = new WorkHave(w.whatWillDo(res));
        for(int i = 0; i< nextWorkActor.size(); i++)
        {
            Controller.workdeals.enqueue(giveRes(newRes));
        }
    }
   public Class workClass()
    {
        return w.getClass();
    }

}
