package Model.ModelUnit;

import java.util.ArrayList;

/**
 * Created by User on 2016-05-27.
 */
/*ModelUnit 클래스의 데이터 주고받기 메소드를 담은 클래스이다.*/
class ModelUnitGetSet {
    static public ArrayList<String> textReceive(int i, ModelUnitData m) throws IndexOutOfBoundsException
    {
        return new ArrayList(m.codes[i].lines);
    }
    /*
    i번째 텍스트를 ArrayList<String>형으로 반환한다.
     i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
    */

    static void textSend(int i, ArrayList<String> s, ModelUnitData m) throws IndexOutOfBoundsException
    {
        m.codes[i].lines=new ArrayList(s);
        m.groupNull();
    }
    /*
    i번째 텍스트를 매개변수 s의 내용으로 바꾼다.
     i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
    */

    static ArrayList<String> getArrangedText(int i, ModelUnitData m) throws IndexOutOfBoundsException
    {
        if(m.group==null) {
            ModelUnitCompareMerge.regrouping(m);
        }
        return (ArrayList<String>) m.arrangedString[i].clone();
    }
    /*
    재배열된 i번째 텍스트를 i번째 텍스트를 ArrayList<String>형으로 반환한다.
     i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
     만일 비교되지 않았다 한다면 그 즉시 regrouping을 사용하여 비교한다.
    */

    static ArrayList<Integer> getArrangedGroup(ModelUnitData m) throws IndexOutOfBoundsException
    {
        if(m.group==null) {
            ModelUnitCompareMerge.regrouping(m);
        }
        return (ArrayList<Integer>) m.group[ModelUnitCompareMerge.getArrangeLine_sGroupNumEnum()][0].clone();
    }
    /*
    재배열된 텍스트의 줄 수 번째의 줄이 어느 그룹에 속하는지를 ArrayList<Integer>형으로 반환한다.
    만일 비교되지 않았다 한다면 그 즉시 regrouping을 사용하여 비교한다.
    */
    static ArrayList<Integer> getArrangedGroupSpace(ModelUnitData m) throws IndexOutOfBoundsException
    {
        if(m.group==null) {
            ModelUnitCompareMerge.regrouping(m);
        }
        return (ArrayList<Integer>) m.group[ModelUnitCompareMerge.getGroup_sIncludingArrangedLineNumEnum()][0].clone();
    }
    /*
    비교된 그룹의 앞에서부터 나오는 번째 번의 그룹이 몇 개의 줄을 포함하는지를 ArrayList<Integer>형으로 반환한다.
    만일 비교되지 않았다 한다면 그 즉시 regrouping을 사용하여 비교한다.
    */

    static String filepath(int i, ModelUnitData m)
    {
        return m.codes[i].filepath;
    }
    /*
    open 시 받았던 filepath값을 반환한다.
    만일 비교되지 않았다 한다면 그 즉시 regrouping을 사용하여 비교한다.
    */
}
