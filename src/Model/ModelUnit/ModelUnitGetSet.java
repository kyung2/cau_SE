package Model.ModelUnit;

import java.util.ArrayList;

/**
 * ModelUnit 클래스의 데이터 주고받기 메소드를 담은 클래스이다.
 * @author Chanwoo park
 *
 */
class ModelUnitGetSet {

    /**
     * i번째 텍스트를 ArrayListString형으로 반환한다
     * i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
    * @param m ModelUnitData
    * @param i 0 left 1 right
     *  @throws IndexOutOfBoundsException 유효하지 않은 인자가 잘못 전달 시
    * @return Arraylist (left or right text Type Casting)
    */
    static public ArrayList<String> textReceive(int i, ModelUnitData m) throws IndexOutOfBoundsException
    {
        return new ArrayList(m.codes[i].lines);
    }

    /**
     * i번째 텍스트를 s의 내용으로 바꾼다
     * i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
     * @param m ModelUnitData
     * @param i 0 left 1 right
     * @param s 텍스트의 s의 내용
     * @throws IndexOutOfBoundsException 유효하지 않은 인자가 잘못 전달 시
     */
    static void textSend(int i, ArrayList<String> s, ModelUnitData m) throws IndexOutOfBoundsException
    {
        m.codes[i].lines=new ArrayList(s);
        m.groupNull();
    }


    /**
     * 재배열된 i번째 텍스트를 i번째 텍스트를 ArrayListString형으로 반환한다.
     * 만일 비교되지 않았다 한다면 그 즉시 regrouping을 사용하여 비교한다.
     * @param i 0 left 1 right
     * @param m ModelUnitData
     * @throws IndexOutOfBoundsException 유효하지 않은 인자가 잘못 전달 시
     * @return 재배열된 오른쪽또는왼쪽 텍스트의 type
     */
    static ArrayList<String> getArrangedText(int i, ModelUnitData m) throws IndexOutOfBoundsException
    {
        if(m.group==null) {
            ModelUnitCompareMerge.regrouping(m);
        }
        return (ArrayList<String>) m.arrangedString[i].clone();
    }

    /**
     * 재배열된 텍스트의 줄 수 번째의 줄이 어느 그룹에 속하는지를 ArrayList형으로 반환한다.
     * 만일 비교되지 않았다 한다면 그 즉시 regrouping을 사용하여 비교한다.
     * @param m ModelUnitData
     * @throws IndexOutOfBoundsException 유효하지 않은 인자가 잘못 전달 시
     * @return Arraylist 어디 줄에 속하는지
     */
    static ArrayList<Integer> getArrangedGroup(ModelUnitData m) throws IndexOutOfBoundsException
    {
        if(m.group==null) {
            ModelUnitCompareMerge.regrouping(m);
        }
        return (ArrayList<Integer>) m.group[ModelUnitCompareMerge.getArrangeLine_sGroupNumEnum()][0].clone();
    }

    /**
     * 비교된 그룹의 앞에서부터 나오는 오른쪽 또는 왼쪽의 그룹이 몇개의 줄을 포함하는지 arraylist(int)반환
     * 만일 비교되지 않았다 한다면 그 즉시 regrouping을 사용하여 비교한다.
     * @param m ModelUnitData
     * @throws IndexOutOfBoundsException 유효하지 않은 인자가 잘못 전달 시
     * @return Arraylist 포함하는 줄의 갯수
     */
    static ArrayList<Integer> getArrangedGroupSpace(ModelUnitData m) throws IndexOutOfBoundsException
    {
        if(m.group==null) {
            ModelUnitCompareMerge.regrouping(m);
        }
        return (ArrayList<Integer>) m.group[ModelUnitCompareMerge.getGroup_sIncludingArrangedLineNumEnum()][0].clone();
    }

    /**
     * @param m ModelUnitData
     * @param i 0 left 1 right
     * @return string filepath when we get open
     */
    static String filepath(int i, ModelUnitData m)
    {
        return m.codes[i].filepath;
    }

}
