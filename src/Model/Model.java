package Model;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-19.
 */
public interface Model {
    void newModel(int tapNum);
    // 식별자 tapNum을 가지는 창에 대응되는 모델 유닛을 만든다.
    int newModel();
    // 모델 유닛을 만들고 그 유닛의 식별자를 반환한다.
    void closeModel(int tapNum) throws IllegalAccessException;
    // 식별자 tapNum을 가지는 창을 닫는다.
    ArrayList<String> getText(int tapNum, int i) throws IndexOutOfBoundsException, IllegalAccessException;
    // 식별자 tapNum의 i번째 창(i=0 or 1)의 텍스트 전체를 가져온다.
    ArrayList<String> getArrangedText(int tapNum, int i) throws IndexOutOfBoundsException, IllegalAccessException;
    // 식별자 tapNum의 i번째 창(i=0 or 1)의 정리된 텍스트 전체를 가져온다.
    ArrayList<Integer> getArrangedGroupNum(int tapNum, int i) throws IndexOutOfBoundsException, IllegalAccessException;
    // 식별자 tapNum의 i번째 창(i=0 or 1)의 정리된 그룹을 가져온다.
    void setText(int tapNum, ArrayList<String> text, int i) throws IndexOutOfBoundsException, IllegalAccessException;
    // 식별자 tapNum의 i번째 창(i=0 or 1)의 텍스트 전체를 text로 설정한다.
    void readTextOuter(int tapNum, String filepath, int i) throws IndexOutOfBoundsException, IOException, IllegalAccessException;
    // 식별자 tapNum의 i번째 창(i=0 or 1)의 텍스트 전체를 filepath에서 가져온다.
    void writeTextOuter(int tapNum, String filepath, int i) throws IndexOutOfBoundsException, IOException, IllegalAccessException;
    // 식별자 tapNum의 i번째 창(i=0 or 1)의 텍스트 전체를 filepath에 쓴다.
    void merge(int tapNum, int Index, boolean direction) throws IndexOutOfBoundsException, IllegalAccessException;
    // 식별자 tapNum의 Index번째 그룹을 앞뒤 그룹과 결합한다. direction=true라면 0->1, false라면 0<-1.
    ArrayList<Integer>[][] getGroupInfo(int tapNum) throws IllegalAccessException;
    // 식별자 tapNum의 group에 관한 모든 정보를 가져온다.
}
