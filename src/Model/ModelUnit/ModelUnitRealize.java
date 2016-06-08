package Model.ModelUnit;
import Model.ModelException.MergeLineIllegalException;

import java.io.*;
import java.lang.String;
import java.util.ArrayList;

/**
 * 서로 비교하여야 할 대상이 되는 두 텍스트를 담은 하나의 탭 단위로서의 기능을 구사하는 오브젝트이다.
 * ModelUnit 클래스의 데이터 주고받기 메소드를 담은 클래스이다.
 * @author Chanwoo park
 */
public class ModelUnitRealize implements ModelUnit{

    /**
    *이 오브젝트가 가진 데이터는 ModelUnitData 오브젝트에 저장되며,
    *이 오브젝트의 기능은 데이터 주고받기, 파일 읽고쓰기, 비교와 병합 이 세 카테고리에 따라
    *ModelUnitGetSet, ModelUnitFileIO, ModelUnitCompareMerge 클래스에서 static 메소드를 가져온다.
    */
    ModelUnitData m;

    /**
    *생성자. 데이터가 들어갈 공간을 만든다.
    */
    public ModelUnitRealize()
    {
        m = new ModelUnitData();
    }


    /**
     * i번째 텍스트를 ArrayList<String>형으로 반환한다
     * i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
     * @param i 0 left 1 right
     * @throws IndexOutOfBoundsException 유효하지 않은 인자가 잘못 전달 시
     * @return Arraylist0string ModelUnitGet.set.textReceive(i,m)
    */
    public ArrayList<String> textReceive(int i) throws IndexOutOfBoundsException {
        return ModelUnitGetSet.textReceive(i,m);
    }

    /**
     * i번째 텍스트를 매개변수 s의 내용으로 바꾼다.
     * i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
     * @param i 0 left 1 right
     * @param s string
     * @throws IndexOutOfBoundsException 유효하지 않은 인자가 잘못 전달 시
    */
    public void textSend(int i, ArrayList<String> s) throws IndexOutOfBoundsException {
        ModelUnitGetSet.textSend(i,s,m);
    }

    /**
     * 재배열된 i번째 텍스트를 i번째 텍스트를 ArrayList<String>형으로 반환한다.
     * i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
     * @param i 0 left 1 right
     * @throws IndexOutOfBoundsException 유효하지 않은 인자가 잘못 전달 시
     * @return ArrayList ModelUnitGetSet
    */
    public ArrayList<String> getArrangedText(int i) throws IndexOutOfBoundsException {
        return ModelUnitGetSet.getArrangedText(i,m);
    }

    /**
     *  재배열된 텍스트의 줄 수 번째의 줄이 어느 그룹에 속하는지를 ArrayList<Integer>형으로 반환한다.
     *  @throws IndexOutOfBoundsException 유효하지 않은 인자가 잘못 전달 시
     *  @return arraylist integerType  ModelUnitGetSet.getArrangGroup(m)
     */
    public ArrayList<Integer> getArrangedGroup() throws IndexOutOfBoundsException {
        return ModelUnitGetSet.getArrangedGroup(m);
    }

    /**
     *  비교된 그룹의 앞에서부터 나오는 번째 번의 그룹이 몇 개의 줄을 포함하는지를 ArrayList<Integer>형으로 반환한다.
     *  @throws IndexOutOfBoundsException 유효하지 않은 인자가 잘못 전달 시
     *  @return ModelUnitGetSet.getArrangedGroupSpace(m)
    */
    public ArrayList<Integer> getArrangedGroupSpace() throws IndexOutOfBoundsException {
        return ModelUnitGetSet.getArrangedGroupSpace(m);
    }

    /**
     * 재배열된 텍스트의 lineNum번째의 줄이 포함된 그룹을 병합한다.
     * direction이 true면 0번째에서 1번째 텍스트로, false면 그 반대 방향으로 병합한다.
     * i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
     * 병합할 그룹이 이미 일치 상태일 경우 MergeLineIllegalException 발생.
     * @param direction true left false right
     * @param lineNum lineNum 줄이 포함된 그룹을 병합.
     * @throws IndexOutOfBoundsException 유효하지 않은 인자가 잘못 전달 시
     * @throws MergeLineIllegalException 이미 merge가 된 상태
    */
    public void mergeBylineNum(int lineNum, boolean direction) throws IndexOutOfBoundsException, MergeLineIllegalException {
        ModelUnitCompareMerge.mergeBylineNum(lineNum,direction,m);
    }

    /**
    *재배열된 텍스트의 groupNum번째의 그룹을 병합한다.
    *direction이 true면 0번째에서 1번째 텍스트로, false면 그 반대 방향으로 병합한다.
    *i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
    *병합할 그룹이 이미 일치 상태일 경우 MergeLineIllegalException 발생.
     * @param direction true left false right
     * @param groupNum group의 index
     * @throws MergeLineIllegalException 이미 merge가 된 상태
    */
    public void mergeByGroupNum(int groupNum, boolean direction) throws MergeLineIllegalException {
        ModelUnitCompareMerge.mergeByGroupNum(groupNum,direction,m);
    }

    /**
     *  i번째 텍스트를 filepath에 있는 파일의 내용을 가져와 대체한다.
     *  filepath에 있는 파일을 열 수 없을 때 IOException 발생.
     *  i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
     *  @param i 0 left 1 right
     *  @param filepath file경로
     *  @throws IOException file open fail
     *  @throws IndexOutOfBoundsException 유효하지 않은 인자가 잘못 전달 시
    */
    public void open(String filepath, int i) throws IOException, IndexOutOfBoundsException {
        ModelUnitFileIO.open(filepath,i,m);
    }

    /**
     *  매개변수로 받은 filepath에 위치한 파일에 i번째 텍스트의 내용을 덮어쓴다.
    *filepath에 있는 파일을 쓸 수 없을 때 IOException 발생.
    *i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
     * @param filepath filepath
     * @param i 0 left 1 right
     *  @throws IOException file open fail
     *  @throws IndexOutOfBoundsException 유효하지 않은 인자가 잘못 전달 시
     * */
    public void save(String filepath, int i) throws IOException, IndexOutOfBoundsException {
        ModelUnitFileIO.save(filepath,i,m);
    }

    /**
     *  open 시 받았던 filepath에 위치한 파일에 i번째 텍스트의 내용을 덮어쓴다.
     *  filepath에 있는 파일을 쓸 수 없을 때 IOException 발생.
     *  i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
     * @param i 0 left 1 right
     *  @throws IOException file open fail
     */
    public void save(int i) throws IOException {
        ModelUnitFileIO.save(i,m);
    }

    /**
     *  open 시 받았던 filepath값을 반환한다.
     *  @param i 0 left 1 right
     *  @return filepath (when we get opened the file)
    */
    public String filepath(int i) {
        return ModelUnitGetSet.filepath(i,m);
    }
}



