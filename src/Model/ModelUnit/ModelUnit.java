package Model.ModelUnit;

import Model.ModelException.MergeLineIllegalException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-14.
 */

/*서로 비교하여야 할 대상이 되는 두 텍스트를 담은 하나의 탭 단위로서의 기능을 요구하는 인터페이스이다.*/
public interface ModelUnit {

    /*
    텍스트를 비교하는 기능에서, 하나의 그룹은 매칭됨 여부가 동일한 연속된 더 큰 그룹에 속하지 않는 여러 줄을 의미하며,
    나오는 순서에 따라 번호매겨지고 매칭에 의하여 텍스트가 재배열될 수 있다.
    */

    public ArrayList<String> textReceive(int i) throws IndexOutOfBoundsException;
    /*
    i번째 텍스트를 ArrayList<String>형으로 반환한다.
     i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
    */
    public void textSend(int i, ArrayList<String> s) throws IndexOutOfBoundsException;
    /*
    i번째 텍스트를 매개변수 s의 내용으로 바꾼다.
     i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
    */
    public ArrayList<String> getArrangedText(int i) throws IndexOutOfBoundsException;
    /*
    재배열된 i번째 텍스트를 i번째 텍스트를 ArrayList<String>형으로 반환한다.
     i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
    */
    public ArrayList<Integer> getArrangedGroup();
    /*
    재배열된 텍스트의 줄 수 번째의 줄이 어느 그룹에 속하는지를 ArrayList<Integer>형으로 반환한다.
    */
    public ArrayList<Integer> getArrangedGroupSpace();
    /*
    비교된 그룹의 앞에서부터 나오는 번째 번의 그룹이 몇 개의 줄을 포함하는지를 ArrayList<Integer>형으로 반환한다.
    */

    public void mergeBylineNum(int lineNum, boolean direction) throws IndexOutOfBoundsException, MergeLineIllegalException;
    /*
    재배열된 텍스트의 lineNum번째의 줄이 포함된 그룹을 병합한다.
    direction이 true면 0번째에서 1번째 텍스트로, false면 그 반대 방향으로 병합한다.
    i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
    병합할 그룹이 이미 일치 상태일 경우 MergeLineIllegalException 발생.
    */
    public void mergeByGroupNum(int groupNum, boolean direction) throws MergeLineIllegalException;
    /*
    재배열된 텍스트의 groupNum번째의 그룹을 병합한다.
    direction이 true면 0번째에서 1번째 텍스트로, false면 그 반대 방향으로 병합한다.
    i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
    병합할 그룹이 이미 일치 상태일 경우 MergeLineIllegalException 발생.
    */

    public void open(String filepath, int i) throws IOException, IndexOutOfBoundsException;
    /*
    i번째 텍스트를 filepath에 있는 파일의 내용을 가져와 대체한다.
    filepath에 있는 파일을 열 수 없을 때 IOException 발생.
    i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
    */
    public void save(String filepath, int i) throws IOException, IndexOutOfBoundsException;
    /*
    매개변수로 받은 filepath에 위치한 파일에 i번째 텍스트의 내용을 덮어쓴다.
    filepath에 있는 파일을 쓸 수 없을 때 IOException 발생.
    i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
    */

    public void save(int i) throws IOException;
    /*
    open 시 받았던 filepath에 위치한 파일에 i번째 텍스트의 내용을 덮어쓴다.
    filepath에 있는 파일을 쓸 수 없을 때 IOException 발생.
    i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
    */
    public String filepath(int i);
    /*
    open 시 받았던 filepath값을 반환한다.
    */

}



