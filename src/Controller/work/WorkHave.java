package Controller.work;

/**
 * Created by User on 2016-05-16.
 */
public final class WorkHave {
    private Object o[];
    public WorkHave()
    {
        this.o=null;
    }
    public WorkHave(Object o[])
    {
        this.o=o;
    }

    public Object pushObj(Object base, int index)
    {
        if(o==null) return base;
        Class clazz = base.getClass();
        for(int i=0, k=0;i<o.length;i++)
        {
            if(clazz.isInstance(o[i]))//todo - check
            {
                if(k==index) return o[i];
                k++;
            }
        }
        return base;
    }


}
