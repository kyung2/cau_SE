package Model;

import Model.ModelException.MergeLineIllegalException;
import Model.ModelUnit.ModelUnit;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-19.
 */
/* 개개의 모델 유닛을 묶어 관리하는 클래스가 가져야 할 기능을 나열한 인터페이스이다.*/
/* 각각의 모델 유닛은 고유의 식별자를 가진다.*/
public interface ModelInterface {

    void newModel(int tabNum) throws IllegalArgumentException;
    /*
    tabNum을 식별자로 하는 모델 유닛을 하나 포함시킨다.
    이미 그러한 식별자를 가진 유닛이 있을 경우 IllegalArgumentException 발생.
    */
    int newModel();
    /*
    모델 유닛을 하나 포함시키고 그 유닛의 식별자를 반환한다.
    */
    public void closeModel(int tabNum) throws IllegalArgumentException;
    /*
    tabNum을 식별자로 하는 모델 유닛을 닫는다.
    그러한 식별자를 가진 유닛이 없을 경우 IllegalArgumentException 발생.
    */
    public void closeModelAll();
    /*
    가진 모든 모델 유닛을 닫는다.
    그러한 식별자를 가진 유닛이 없을 경우 IllegalArgumentException 발생.
    */
    public ModelUnit getUnit(int tabNum) throws IllegalArgumentException;
    /*
    tabNum을 식별자로 하는 모델 유닛을 얻어온다.
    그러한 식별자를 가진 유닛이 없을 경우 IllegalArgumentException 발생.
    */
    public ArrayList<Integer> getTabNums();
    /*
    어떤어떤 식별자의 유닛이 저장되어 있는지에 대하여
    모든 식별자를 리스트로 반환한다.
    */



    public boolean isOpen(int tabNum, int i) throws IllegalArgumentException;
    /*
    tabNum을 식별자로 하는 모델 유닛이 파일로부터 열려져 있는지를 체크한다.
    */

    public ArrayList<String> getText(int tabNum, int i) throws IndexOutOfBoundsException, IllegalArgumentException;
    /*
    tabNum을 식별자로 하는 모델 유닛의 i번째 텍스트를 가져온다.
    */

    public ArrayList<String> getArrangedText(int tabNum, int i) throws IndexOutOfBoundsException, IllegalArgumentException;
    /*
    tabNum을 식별자로 하는 모델 유닛의 i번째 재배치된 텍스트를 가져온다.
    */

    public ArrayList<Integer> getArrangedGroupNum(int tabNum) throws IndexOutOfBoundsException, IllegalArgumentException;
    /*
    tabNum을 식별자로 하는 모델 유닛의
    재배열된 텍스트의 줄 수 번째의 줄이 어느 그룹에 속하는지에 대한 정보를 가져온다.
    */

    public ArrayList<Integer> getArrangedGroupSpace(int tabNum) throws IllegalArgumentException;
    /*
    tabNum을 식별자로 하는 모델 유닛의
    비교된 그룹의 앞에서부터 나오는 번째 번의 그룹이 몇 개의 줄을 포함하는지에 대한 정보를 가져온다.
    */

    public void setText(int tabNum, ArrayList<String> text, int i) throws IndexOutOfBoundsException, IllegalArgumentException;
    /*
    tabNum을 식별자로 하는 모델 유닛의
    i번째 텍스트를 매개변수 text의 내용으로 바꾼다.
    */

    public void readTextOuter(int tabNum, String filepath, int i) throws IndexOutOfBoundsException, IOException, IllegalArgumentException;
    /*
     tabNum을 식별자로 하는 모델 유닛의
     i번째 텍스트를 filepath에 있는 파일로부터 읽어와 대체한다.
     */

    public void writeTextOuter(int tabNum, String filepath, int i) throws IndexOutOfBoundsException, IOException, IllegalArgumentException;
    /*
     tabNum을 식별자로 하는 모델 유닛의
     i번째 텍스트를 filepath에 있는 파일에 쓴다.
     */

    public void writeTextOuter(int tabNum, int i) throws IndexOutOfBoundsException, IOException, IllegalArgumentException;
    /*
     tabNum을 식별자로 하는 모델 유닛의
     i번째 텍스트를 그 텍스트가 파일로부터 내용을 읽어 올 때의 filepath에 있는 파일에 쓴다.
     */

    public void mergeByLine(int tabNum, int Index, boolean direction) throws IndexOutOfBoundsException, IllegalArgumentException, MergeLineIllegalException;
    /*
    tabNum을 식별자로 하는 모델 유닛의
    재배열된 텍스트의 lineNum번째의 줄이 포함된 그룹을 병합한다.
    */

    public void mergeByGroup(int tabNum, int groupNum, boolean direction) throws IndexOutOfBoundsException, IllegalArgumentException, MergeLineIllegalException;
    /*
    tabNum을 식별자로 하는 모델 유닛의
    재배열된 텍스트의 groupNum번째 그룹을 병합한다.
    */




}

