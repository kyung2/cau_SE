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
 * @author Chanwoo Park
 * */
/* 개개의 모델 유닛을 묶어 관리하는 클래스가 가져야 할 기능을 나열한 인터페이스이다.*/
/* 각각의 모델 유닛은 고유의 식별자를 가진다.*/
public interface ModelInterface {

    /**
     * Open new Tab.
     * @exception IllegalArgumentException 유효하지 않은 인자가 잘못 전달 시
     * @param tabNum This is index about now working tab. if add tab tabNum++
     * */
    void newModel(int tabNum) throws IllegalArgumentException;
    int newModel();

    /**
     * close the now working tab.
     * @param tabNum This is index about we want close tab of index.
     * @throws IllegalArgumentException 유효하지 않은 인자가 잘못 전달  시
     * */
    public void closeModel(int tabNum) throws IllegalArgumentException;

    /**
     * Close All Tab
     */
    public void closeModelAll();

    /**
     * get now working tab info
     * @throws IllegalArgumentException 유효하지 않은 인자가 잘못 전달  시
     * @param tabNum This is index about now working tab.
     * @return Modelunit
     * */
    public ModelUnit getUnit(int tabNum) throws IllegalArgumentException;

    /**
     * bring tabnum
     * @return ArrayList innteger  All index return list */
    public ArrayList<Integer> getTabNums();

    /**
     * check tabNum -th i part file open
     * @throws  IllegalArgumentException 유효하지 않은 인자가 잘못 전달  시
     * @param i left : 0 right : 1
     * @param tabNum   This is index about now working tab
     * @return true if is open
     */
    public boolean isOpen(int tabNum, int i) throws IllegalArgumentException;

    /**
     * this bring text from tabnum-th if i == 0 left part i == 1 right part.
     * @throws IndexOutOfBoundsException 유효하지 않은 인자가 잘못 전달  시
     * @throws IllegalArgumentException 유효하지 않은 인자가 잘못 전달  시
     * @param i left : 0 right : 1
     * @param tabNum   This is index about now working tab
     * @return ArrayList_text
     */
    public ArrayList<String> getText(int tabNum, int i) throws IndexOutOfBoundsException, IllegalArgumentException;

    /**
     * this bring text from tabnum-th if i == 0 left part i == 1 right part.
     * @throws IndexOutOfBoundsException 유효하지 않은 인자가 잘못 전달  시
     * @throws IllegalArgumentException 유효하지 않은 인자가 잘못 전달  시
     * @param i left : 0 right : 1
     * @param tabNum   This is index about now working tab
     * @return ArrayList txt
     */
    public ArrayList<String> getArrangedText(int tabNum, int i) throws IndexOutOfBoundsException, IllegalArgumentException;

    /**
     * tabnum을 식별자로 하는 모델 유닛의 재별열 된 텍스트의 줄 수 번째의 줄 수 번째의 줄이 어느 그룹에 속하는지에 대한 정보를 가져온다.
     * @throws IndexOutOfBoundsException,IllegalArgumentException 유효하지 않은 인자가 잘못 전달 시
     * @param tabNum   This is index about now working tab
     * @return ArrayList_Interger
     *
     */
    public ArrayList<Integer> getArrangedGroupNum(int tabNum) throws IndexOutOfBoundsException, IllegalArgumentException;

    /**
     * 비교된 그룹의 앞에서 나오는 i번째 번의 그룹이 몇 개의 줄을 포함하는지에 대한 정보를 가져온다.
     * @throws IllegalArgumentException 유효하지 않은 인자가 잘못 전달 시
     * @param tabNum  This is index about now working tab
     * @return ArrayList_Integer num of line
     */
    public ArrayList<Integer> getArrangedGroupSpace(int tabNum) throws IllegalArgumentException;

    /**
     * i번째 텍스트를 text의 내용으로 바꾼다.
     * @param i 0 left 1 right
     * @param tabNum This is index about now working tab
     * @param text  content of text
     * @throws IndexOutOfBoundsException 유효하지 않은 인자가 잘못 전달 시
     * @throws IllegalArgumentException 유효하지 않은 인자가 잘못 전달 시
     **/
    public void setText(int tabNum, ArrayList<String> text, int i) throws IndexOutOfBoundsException, IllegalArgumentException;

    /**
     * i번째 텍스트를 filepath에 있는 파일로 바꾼다.
     * @param tabNum This is index about now working tab
     * @param i 0: left 1 :right
     * @param filepath location of file
     * @throws IndexOutOfBoundsException 유효하지 않은 인자가 잘못 전달 시
     * @throws IOException 파일을 읽어오지 못한다.
     * @throws IllegalArgumentException 유효하지 않은 인자가 잘못 전달 시
     * */
    public void readTextOuter(int tabNum, String filepath, int i) throws IndexOutOfBoundsException, IOException, IllegalArgumentException;

    /**
     * i번째 텍스트를 filepath에 있는 파일로 바꾼다.
     * @param tabNum This is index about now working tab
     * @param i 0 left 1 right
     * @param filepath filepath
     * @throws IndexOutOfBoundsException,IOException,IllegalArgumentException 유효하지 않은 인자가 잘못 전달 시
     * */
    public void writeTextOuter(int tabNum, String filepath, int i) throws IndexOutOfBoundsException, IOException, IllegalArgumentException;

    /**
     * i번째 텍스트를 filepath에 있는 파일에 적는다.
     * @param tabNum This is index about now working tab
     * @param i i==0 left i==1 right
     * @throws IndexOutOfBoundsException 유효하지 않은 인자가 잘못 전달 시
     * @throws IOException file이 존재하지 않을 시
     * @throws IllegalArgumentException 유효하지 않은 인자가 잘못 전달 시
     */
    public void writeTextOuter(int tabNum, int i) throws IndexOutOfBoundsException, IOException, IllegalArgumentException;

    /**
     *tabNum을 식별자로 하는 모델 유닛의 재배열된 텍스트의 lineNum번째의 줄이 포함된 그룹을 병합한다.
     * @param direction true left false right
     * @param Index index line
     * @param tabNum now working tab
     * @throws IndexOutOfBoundsException,IllegalArgumentException 유효하지 않은 인자가 잘못 전달 시
     */
     public void mergeByLine(int tabNum, int Index, boolean direction) throws IndexOutOfBoundsException, IllegalArgumentException, MergeLineIllegalException;

     /**
     * 선택된 부분을 merge 하는 부분.
     * @param tabNum This is index about now working tab
     * @param direction true : left false : right
     * @param groupNum 맞거나 틀리거나 하는 그룹들의 index num
     * @throws IndexOutOfBoundsException 유효하지 않은 인자가 잘못 전달 시
     * @throws IllegalArgumentException 유효하지 않은 인자가 잘못 전달 시
      */
    public void mergeByGroup(int tabNum, int groupNum, boolean direction) throws IndexOutOfBoundsException, IllegalArgumentException, MergeLineIllegalException;

}

