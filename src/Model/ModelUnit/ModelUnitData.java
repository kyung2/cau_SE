package Model.ModelUnit;

import java.util.ArrayList;

/**
 *
 * ModelUnit 클래스에서 사용할 데이터들을 담을 클래스
 * @author Chanwoo Park
 */
class ModelUnitData {

    public static final int NUMOFTEXTS = 2;
    /*이 클래스가 몇 개의 데이터를 담고 있는지에 대한 상수이다.*/
    protected SavedText[] codes;
    /*이 클래스가 담고 있는 텍스트이다.*/
    protected ArrayList[][] group;
    /*이 클래스가 담고 있는 그룹 관련 정보이다.*/
    protected ArrayList[] arrangedString;
    /*이 클래스가 담고 있는 재배열된 텍스트이다.*/

    ModelUnitData()
    {
        assert NUMOFTEXTS > 1;
        SavedText[] s = {new SavedText(), new SavedText()};
        codes = s;
        group = null;
        arrangedString = null;
    }

    /**
     * 만일 비교가 되지 않은 상태이면, group을 null로 하여 그 사실을 알아볼 수 있다.
     */
    protected void groupNull()
    {
        group = null;
        arrangedString = null;
    }


}
