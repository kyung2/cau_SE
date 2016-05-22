package Model;

import Model.ModelException.MergeLineIllegalException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-19.
 */
public interface Model {
    void newModel(int tabNum);
    // 식별자 tabNum을 가지는 창에 대응되는 모델 유닛을 하나 만든다.
    //각 모델 유닛은 i=0 또는 i=1, 이렇게 두 개의 텍스트를 저장하는 구조를 가진다.
    int newModel();
    // 모델 유닛을 하나 만들고 그 유닛의 식별자를 반환한다.
    void closeModel(int tabNum) throws IllegalAccessException;
    // 식별자 tabNum을 가지는 모델 유닛을 닫는다.

    boolean isOpen(int tabNum, int i) throws IllegalAccessException;
    ArrayList<String> getText(int tabNum, int i) throws IndexOutOfBoundsException, IllegalAccessException;
    // 식별자 tabNum의 i번째 텍스트(i=0 or 1)전체를 가져온다.
    // tabNum이 적합하지 않을 시 IllegalAccessException이, i가 적합하지 않을 시 IndexOutOfBoundsException이 발생된다.
    ArrayList<String> getArrangedText(int tabNum, int i) throws IndexOutOfBoundsException, IllegalAccessException;
    // 식별자 tabNum의 i번째 창(i=0 or 1)의 정리된 텍스트 전체를 가져온다.
    ArrayList<Integer> getArrangedGroupNum(int tabNum) throws IndexOutOfBoundsException, IllegalAccessException;
    // 식별자 tabNum의 정리된 텍스트 각 줄의 그룹을 가져온다.
    ArrayList<Integer> getArrangedGroupSpace(int tabNum) throws IllegalAccessException;
    // 식별자 tabNum의 정리된 텍스트 각 줄의 그룹이 차지하는 줄 수를 가져온다.
    void setText(int tabNum, ArrayList<String> text, int i) throws IndexOutOfBoundsException, IllegalAccessException;
    // 식별자 tabNum의 i번째 창(i=0 or 1)의 텍스트 전체를 text로 설정한다.
    void readTextOuter(int tabNum, String filepath, int i) throws IndexOutOfBoundsException, IOException, IllegalAccessException;
    // 식별자 tabNum의 i번째 창(i=0 or 1)의 텍스트 전체를 filepath에서 가져온다.
    void writeTextOuter(int tabNum, String filepath, int i) throws IndexOutOfBoundsException, IOException, IllegalAccessException;
    // 식별자 tabNum의 i번째 창(i=0 or 1)의 텍스트 전체를 filepath에 쓴다.
    void writeTextOuter(int tabNum, int i) throws IndexOutOfBoundsException, IOException, IllegalAccessException;
    // 식별자 tabNum의 i번째 창(i=0 or 1)의 텍스트 전체를 그 창이 연 파일에 쓴다.
    void mergeByLine(int tabNum, int Index, boolean direction) throws IndexOutOfBoundsException, IllegalAccessException, MergeLineIllegalException;
    // 식별자 tabNum의, 정리된 텍스트의 Index번째 줄이 포함된 그룹을 앞뒤 그룹과 결합한다. direction=true라면 0->1, false라면 0<-1.
    void mergeByGroup(int tabNum, int groupNum, boolean direction) throws IndexOutOfBoundsException, IllegalAccessException, MergeLineIllegalException;
    // 식별자 tabNum의, 정리된 텍스트의 Index번째 줄이 포함된 그룹을 앞뒤 그룹과 결합한다. direction=true라면 0->1, false라면 0<-1.
}

