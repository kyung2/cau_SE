package Model.ModelUnit;

import Model.ModelException.MergeLineIllegalException;

import java.io.IOException;
import java.util.ArrayList;

/**
 *서로 비교하여야 할 대상이 되는 두 텍스트를 담은 하나의 탭 단위로서의 기능을 요구하는 인터페이스이다.
 *  텍스트를 비교하는 기능에서, 하나의 그룹은 매칭됨 여부가 동일한 연속된 더 큰 그룹에 속하지 않는 여러 줄을 의미하며,
 *  나오는 순서에 따라 번호매겨지고 매칭에 의하여 텍스트가 재배열될 수 있다.
 * @author Chanwoo Park
 */
public interface ModelUnit {

      /**
      * @param i 변환할 index, 0 : left 1 : right
      * @return arraylist result
      * @throws IndexOutOfBoundsException
      * */
    public ArrayList<String> textReceive(int i) throws IndexOutOfBoundsException;

    /**
    * i번째 텍스트를 매개변수 s의 내용으로 바꾼다. i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
    * @param i 0:left 1:right
    * @param s i번째 텍스트를 매개변수 s의 내용으로 바꾼다.
     *@throws IndexOutOfBoundsException
     */
    public void textSend(int i, ArrayList<String> s) throws IndexOutOfBoundsException;

    /**
     * @param i 재배열된 i번째 텍스트
     * @throws  IndexOutOfBoundsException
     * @return result of Typecasting
     */
    public ArrayList<String> getArrangedText(int i) throws IndexOutOfBoundsException;

    /**
     * @return arraylist 재배열된 텍스트의 줄 수 번쨰의 줄이 어느 그룹 소속인지 반환
     */
    public ArrayList<Integer> getArrangedGroup();

    /**
     * @return arraylist 재배열된 텍스트의 줄 수 몇개의 줄을 포함하는지 반환
     */
    public ArrayList<Integer> getArrangedGroupSpace();


    /**
     * 재배열된 텍스트의 lineNum번째의 줄이 포함된 그룹을 병합한다.
     * direction이 true면 0번째에서 1번째 텍스트로, false면 그 반대 방향으로 병합한다.
     * i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
     * 병합할 그룹이 이미 일치 상태일 경우 MergeLineIllegalException 발생.
     * @param direction true : left->right false right->left
     * @param lineNum index of line
     * @throws IndexOutOfBoundsException
     * @throws MergeLineIllegalException
     */
    public void mergeBylineNum(int lineNum, boolean direction) throws IndexOutOfBoundsException, MergeLineIllegalException;

    /**
     *재배열된 텍스트의 groupNum번째의 그룹을 병합한다.
     *direction이 true면 0번째에서 1번째 텍스트로, false면 그 반대 방향으로 병합한다.
     *i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
     *병합할 그룹이 이미 일치 상태일 경우 MergeLineIllegalException 발생.
     * @param direction true : left->right false right->left
     * @param groupNum index of line
     * @throws MergeLineIllegalException
     */
    public void mergeByGroupNum(int groupNum, boolean direction) throws MergeLineIllegalException;


    /**
     * i번째 텍스트를 filepath에 있는 파일의 내용을 가져와 대체한다.
     *filepath에 있는 파일을 열 수 없을 때 IOException 발생.
     *i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
     * @param i 0 left 1 right
     * @param filepath file location
     * @throws IOException
     * @throws IndexOutOfBoundsException
     */
    public void open(String filepath, int i) throws IOException, IndexOutOfBoundsException;

    /**
     * i번째 텍스트를 filepath에 있는 파일의 저장한다
     *filepath에 있는 파일을 저장 할 수 없을 때 IOException 발생.
     *i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
     * @param i 0 left 1 right
     * @param filepath file location
     * @throws IOException
     * @throws IndexOutOfBoundsException
     */
    public void save(String filepath, int i) throws IOException, IndexOutOfBoundsException;


    /**
     *open시 받은 filepath에 위치한 파일에 i 번째 텍스트의 내용을 쓴다. i번째 텍스트를 filepath에 있는 파일의 저장한다
     *filepath에 있는 파일을 저장 할 수 없을 때 IOException 발생.
     *i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
     * @param i 0 left 1 right
     * @throws IOException
     */
    public void save(int i) throws IOException;

    /**
     * @param i 0 left 1 right
     * @return string filepath */
    public String filepath(int i);


}



