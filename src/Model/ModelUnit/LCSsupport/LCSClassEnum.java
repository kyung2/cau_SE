package Model.ModelUnit.LCSsupport;

/**
 * @see Model.ModelUnit.LCSsupport.LCSSupportUnit
 * LCS Algorithm
 * @author Chanwoo Park
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
