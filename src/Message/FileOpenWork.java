package Message;

/**
 * Created by User on 2016-05-15.
 */
public class FileOpenWork implements work{

    String s;
    int i; // i = 0 : left panel/data, i = 1 : right panel/data
    FileOpenWork(String s, int i)
    {
        this.s=s;
        this.i=i;
    }
    public void whatWillDo(WorkDealer d) throws Exception {
        d.m.open(s, i);
    }
}
