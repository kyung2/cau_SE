package Model.LCSsupport;

/**
 * Created by User on 2016-05-20.
 */
public enum LCSClassEnum {
    LCSArrangeLine_sNonArrangeLineNum,
    LCSArrangeLine_sGroupNum,
    LCSGroup_sIncludingArrangedLineNum;

    public static int find(LCSClassEnum e)
    {
        LCSClassEnum[] ea = LCSClassEnum.values();
        for(int i=0;i<ea.length;i++) if(ea[i].compareTo(e)==0) return i;
        return -1;
    }
}
