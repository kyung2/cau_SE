package Model;

import Model.ModelException.MergeLineIllegalException;
import Model.ModelUnit.ModelUnit;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Model interface
 * This class has interface of Model.
 * This Class has functions about Binding model Unit for management.
 * Each Model unit unique Identifier.
 * @author 박찬우
 * */
/* 개개의 모델 유닛을 묶어 관리하는 클래스가 가져야 할 기능을 나열한 인터페이스이다.*/
/* 각각의 모델 유닛은 고유의 식별자를 가진다.*/
public interface ModelInterface {
    /**
     * Open new Tab.
     * @exception IllegalArgumentException
     * @param tabNum This is index about now working tab. if add tab tabNum++
     * */

    void newModel(int tabNum) throws IllegalArgumentException;
    /*
    tabNum을 식별자로 하는 모델 유닛을 하나 포함시킨다.
    이미 그러한 식별자를 가진 유닛이 있을 경우 IllegalArgumentException 발생.
    */
    int newModel();
    /*
    모델 유닛을 하나 포함시키고 그 유닛의 식별자를 반환한다.
    */
    /**
     * close the now working tab.
     * @param tabNum This is index about now working tab.
     * @throws IllegalArgumentException
     * */
    public void closeModel(int tabNum) throws IllegalArgumentException;
    /*
    tabNum을 식별자로 하는 모델 유닛을 닫는다.
    그러한 식별자를 가진 유닛이 없을 경우 IllegalArgumentException 발생.
    */
    /**
     * Close All Tab
     */
    public void closeModelAll();
    /*
    가진 모든 모델 유닛을 닫는다.
    그러한 식별자를 가진 유닛이 없을 경우 IllegalArgumentException 발생.
    */
    /**
     * @throws IllegalArgumentException
     * @param tabNum This is index about now working tab.
     *
     * */
    public ModelUnit getUnit(int tabNum) throws IllegalArgumentException;
    /*
    tabNum을 식별자로 하는 모델 유닛을 얻어온다.
    그러한 식별자를 가진 유닛이 없을 경우 IllegalArgumentException 발생.
    */
    /**
     * @return ArrayList<Integer> All index return list */
    public ArrayList<Integer> getTabNums();
    /*
    어떤어떤 식별자의 유닛이 저장되어 있는지에 대하여
    모든 식별자를 리스트로 반환한다.
    */




    /**
     * check tabNum -th i part file exist
     * @param i left : 0 right : 1
     * @param tabNum   This is index about specify tab.
     */
    public boolean isOpen(int tabNum, int i) throws IllegalArgumentException;
    /*
    tabNum을 식별자로 하는 모델 유닛이 파일로부터 열려져 있는지를 체크한다.
    */

    /**
     * this bring text from tabnum-th if i == 0 left part i == 1 right part. ,
     * @param i left : 0 right : 1
     * @param tabNum   This is index about specify tab.
     */
    public ArrayList<String> getText(int tabNum, int i) throws IndexOutOfBoundsException, IllegalArgumentException;
    /*
    tabNum을 식별자로 하는 모델 유닛의 i번째 텍스트를 가져온다.
    */
    /**
     * this bring text from tabnum-th if i == 0 left part i == 1 right part.
     * @throws IndexOutOfBoundsException
     * @param i left : 0 right : 1
     * @param tabNum   This is index about specify tab.
     */public ArrayList<String> getArrangedText(int tabNum, int i) throws IndexOutOfBoundsException, IllegalArgumentException;
    /*
    tabNum을 식별자로 하는 모델 유닛의 i번째 재배치된 텍스트를 가져온다.
    */
    /**tabnum을 식별자로 하는 모델 유닛의 재별열 된 텍스트의 줄 수 번째의 줄 수 번째의 줄이 어느 그룹에 속하는지에 대한 정보를 가져온다.
     * @throws IndexOutOfBoundsException,IllegalArgumentException
     * @param tabNum   This is index about specify tab.
     * @return ArrayList <Interger>
     *
     * /
    public ArrayList<Integer> getArrangedGroupNum(int tabNum) throws IndexOutOfBoundsException, IllegalArgumentException;
    /*
    tabNum을 식별자로 하는 모델 유닛의
    재배열된 텍스트의 줄 수 번째의 줄이 어느 그룹에 속하는지에 대한 정보를 가져온다.
    */
    /**  비교된 그룹의 앞에서 나오는 i번째 번의 그룹이 몇 개의 줄을 포함하는지에 대한 정보를 가져온다.
     * @param tabNum   This is index about specify tab.
     */
    public ArrayList<Integer> getArrangedGroupSpace(int tabNum) throws IllegalArgumentException;
    /*
    tabNum을 식별자로 하는 모델 유닛의
    비교된 그룹의 앞에서부터 나오는 번째 번의 그룹이 몇 개의 줄을 포함하는지에 대한 정보를 가져온다.
    */
    /**
     * i번째 텍스트를 text의 내용으로 바꾼다.
     * @param i i == 0 : left part i == 1 right part
     * @param tabNum ...
     * @param text  content of text
     * @throws IndexOutOfBoundsException,IllegalArgumentException
     *
     *  */
    public void setText(int tabNum, ArrayList<String> text, int i) throws IndexOutOfBoundsException, IllegalArgumentException;
    /*
    tabNum을 식별자로 하는 모델 유닛의
    i번째 텍스트를 매개변수 text의 내용으로 바꾼다.
    */

    /**
     * i번째 텍스트를 filepath에 있는 파일로 바꾼다.
     * @param tabNum
     * @param i i==0 left i==1 right
     * @param filepath filepath
     * @throws IndexOutOfBoundsException,IOException,IllegalArgumentException
     * */
    public void readTextOuter(int tabNum, String filepath, int i) throws IndexOutOfBoundsException, IOException, IllegalArgumentException;
    /*
     tabNum을 식별자로 하는 모델 유닛의
     i번째 텍스트를 filepath에 있는 파일로부터 읽어와 대체한다.
     */
    /**
     * i번째 텍스트를 filepath에 있는 파일로 바꾼다.
     * @param tabNum
     * @param i i==0 left i==1 right
     * @param filepath filepath
     * @throws IndexOutOfBoundsException,IOException,IllegalArgumentException
     * */
    public void writeTextOuter(int tabNum, String filepath, int i) throws IndexOutOfBoundsException, IOException, IllegalArgumentException;
    /*
     tabNum을 식별자로 하는 모델 유닛의
     i번째 텍스트를 filepath에 있는 파일에 쓴다.
     */
    /**
     * i번째 텍스트를 filepath에 있는 파일에 적는다.
     * @param tabNum
     * @param i i==0 left i==1 right
     * @throws IndexOutOfBoundsException,IOException,IllegalArgumentException
     * */
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
    /**
     * 선택된 부분을 merge 하는 부분.
     * @param tabNum
     * @param direction @@@ difrection이 몬지~~
     * @param groupNum 맞거나 틀리거나 하는 그룹들의 num
     * @throws IndexOutOfBoundsException,IllegalArgumentException*/
    public void mergeByGroup(int tabNum, int groupNum, boolean direction) throws IndexOutOfBoundsException, IllegalArgumentException, MergeLineIllegalException;
    /*
    tabNum을 식별자로 하는 모델 유닛의
    재배열된 텍스트의 groupNum번째 그룹을 병합한다.
    */




}

