package Model.ModelUnit.LCSsupport;

import java.util.ArrayList;

/**
 * 그룹의 정보 단위를 나타나는 객체가 구현하여야 할 기능이다.
 * LCSSupportUnit을 구현한 객체는 LCSClassEnum 열거형에 이름이 나옴으로서
 * 그룹의 정보 단위로서 사용될 수 있는 ArrayList<Integer>[2] 형의 정보를 생성한다.
 * 이는 백트래킹 행렬의 탐색 시 시작/위치 이동/그룹 변화/종료 이 네 가지 상황에서 일어날 변화를 기술하는 것으로 생성된다.
 * @author Chanwoo Park
 */
public interface LCSSupportUnit {

    /**
     * LCSGrouping의 그룹 관련 정보 생성 과정에서 백트래킹 행렬 추적을 시작할 때, aa를 그에 알맞는 정보로 갱신한다.
     * @param aa 이 기능을 구현한 클래스가 완성하여야 할 ArrayList<Integer>
     * */
    public void groupStartingAction(ArrayList<Integer>[] aa);

    /**
     * LCSGrouping의 그룹 관련 정보 생성 과정에서 백트래킹 행렬의 초점이 한 원소에서 다른 원소로 넘어갈 때, aa에 그에 알맞는 정보를 추가한다.
     * @param aa 이 기능을 구현한 클래스가 완성하여야 할 ArrayList<Integer>
     * @param p 백트래킹 행렬의 로그 정보
     * @param dir1 백트래킹 행렬의 초점 이동 방향 정보 1
     * @param dir2 백트래킹 행렬의 초점 이동 방향 정보 2, True/True면 Correct, True/False면 Mismatch, False/*이면 2번째 정보에 따른 Gap을 의미한다.
     * */
    public void groupTrackingAction(ArrayList<Integer>[] aa, LCSGrouping p, boolean dir1, boolean dir2);

    /**
     * LCSGrouping의 그룹 관련 정보 생성 과정에서 백트래킹 행렬의 초점이 한 그룹에서 다른 그룹으로 전환될 때, aa에 그에 알맞는 정보를 추가한다.
     * @param aa 이 기능을 구현한 클래스가 완성하여야 할 ArrayList<Integer>
     * @param p 백트래킹 행렬의 로그 정보
     * */
    public void groupChangingAction(ArrayList<Integer>[] aa, LCSGrouping p);

    /**
     * LCSGrouping의 그룹 관련 정보 생성 과정에서 백트래킹 행렬 추적을 끝낼 때, aa를 그에 알맞는 정보로 갱신한다.
     * @param aa 이 기능을 구현한 클래스가 완성하여야 할 ArrayList<Integer>
     * @param p 백트래킹 행렬의 로그 정보
     * */
    public void groupEndingAction(ArrayList<Integer>[] aa, LCSGrouping p);

    /**
     * 기존 그룹 정보 aaa와 병합될 줄의 번호 index, 병합 방향 dir이 주어질 때, 병합된 텍스트에 맞는 그룹 정보단위를 반환한다.
     * @param aaa 기존의 그룹 관련 정보
     * @param index 병합되는 줄
     * @param dir 병합 방향, true : left->right false right->left
     * */
    public ArrayList<Integer>[] whenMerge(ArrayList<Integer>[][] aaa, int index, boolean dir);
}
