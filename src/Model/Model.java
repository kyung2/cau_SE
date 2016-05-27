package Model;

import Model.ModelException.MergeLineIllegalException;
import Model.ModelUnit.ModelUnit;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-19.
 */
public interface Model {
    void newModel(int tabNum) throws IllegalArgumentException;
    // 식별자 tabNum을 가지는 창에 대응되는 모델 유닛을 하나 만든다.
    //각 모델 유닛은 i=0 또는 i=1, 이렇게 두 개의 텍스트를 저장하는 구조를 가진다.
    int newModel();
    // 모델 유닛을 하나 만들고 그 유닛의 식별자를 반환한다.
    public void closeModel(int tabNum) throws IllegalArgumentException;
    public void closeModelAll();
    // 식별자 tabNum을 가지는 모델 유닛을 닫는다.
    public ModelUnit getUnit(int tabNum) throws IllegalArgumentException;



    public boolean isOpen(int tabNum, int i) throws IllegalArgumentException;
    public ArrayList<String> getText(int tabNum, int i) throws IndexOutOfBoundsException, IllegalArgumentException;
    // 식별자 tabNum의 i번째 텍스트(i=0 or 1)전체를 가져온다.
    // tabNum이 적합하지 않을 시 IllegalArgumentException이, i가 적합하지 않을 시 IndexOutOfBoundsException이 발생된다.
    public ArrayList<String> getArrangedText(int tabNum, int i) throws IndexOutOfBoundsException, IllegalArgumentException;
    // 식별자 tabNum의 i번째 창(i=0 or 1)의 정리된 텍스트 전체를 가져온다.
    public ArrayList<Integer> getArrangedGroupNum(int tabNum) throws IndexOutOfBoundsException, IllegalArgumentException;
    // 식별자 tabNum의 정리된 텍스트 각 줄의 그룹을 가져온다.
    public ArrayList<Integer> getArrangedGroupSpace(int tabNum) throws IllegalArgumentException;
    // 식별자 tabNum의 정리된 텍스트 각 줄의 그룹이 차지하는 줄 수를 가져온다.
    public void setText(int tabNum, ArrayList<String> text, int i) throws IndexOutOfBoundsException, IllegalArgumentException;
    // 식별자 tabNum의 i번째 창(i=0 or 1)의 텍스트 전체를 text로 설정한다.
    public void readTextOuter(int tabNum, String filepath, int i) throws IndexOutOfBoundsException, IOException, IllegalArgumentException;
    // 식별자 tabNum의 i번째 창(i=0 or 1)의 텍스트 전체를 filepath에서 가져온다.
    public void writeTextOuter(int tabNum, String filepath, int i) throws IndexOutOfBoundsException, IOException, IllegalArgumentException;
    // 식별자 tabNum의 i번째 창(i=0 or 1)의 텍스트 전체를 filepath에 쓴다.
    public void writeTextOuter(int tabNum, int i) throws IndexOutOfBoundsException, IOException, IllegalArgumentException;
    // 식별자 tabNum의 i번째 창(i=0 or 1)의 텍스트 전체를 그 창이 연 파일에 쓴다.
    public void mergeByLine(int tabNum, int Index, boolean direction) throws IndexOutOfBoundsException, IllegalArgumentException, MergeLineIllegalException;
    // 식별자 tabNum의, 정리된 텍스트의 Index번째 줄이 포함된 그룹을 앞뒤 그룹과 결합한다. direction=true라면 0->1, false라면 0<-1.
    public void mergeByGroup(int tabNum, int groupNum, boolean direction) throws IndexOutOfBoundsException, IllegalArgumentException, MergeLineIllegalException;
    // 식별자 tabNum의, 정리된 텍스트의 Index번째 줄이 포함된 그룹을 앞뒤 그룹과 결합한다. direction=true라면 0->1, false라면 0<-1.
    public ArrayList<Integer> getTabNums();


}

