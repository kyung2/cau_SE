package Controller;

/**
 * Interface for MainController
 * Created by woojin on 2016-05-31.
 * @author woojin Jang
 */
public interface MainInterface {
    /*
    * compare 버튼을 클릭했을 때
    * 현재 있는 text 들을 비교하여 비교한 결과를 list view 를 통하여 넣어준다.
    * 이때 서로 다른 부분에 따라서 toolbar 의 버튼들과 그에 해당하는 menu item 들의 비활성화를 결정한다.
    * */
    void compareOnAction();
    /*
    * 선택된 차이점을 오른쪽에서 왼쪽으로 merge
    * */
    void copyToLeftOnAction();
    /*
    * 선택된 차이점을 왼쪽에서 오른쪽으로 merge
    * */
    void copyToRightOnAction();
    /*
    * 모든 차이점을 오른쪽에서 왼쪽으로 merge
    * */
    void copyToLeftAllOnAction();
    /*
    * 모든 차이점을 왼쪽에서 오른쪽으로 merge
    * */
    void copyToRightAllOnAction();
    /*
    * 현재 선택된 차이점을 기준으로 다음 차이점으로 이동한다.
    * */
    void nextDifferenceOnAction();
    /*
    * 현재 선택된 차이점을 기준으로 이전 차이점으로 이동한다.
    * */
    void previousDifferenceOnAction();
    /*
    * 가장 처음의 차이점으로 이동한다.
    * */
    void firstDifferenceOnAction();
    /*
    * 현재 근처의 차이점으로 이동한다.
    * 현재 리스트 뷰에서 선택되어 있는 부분이 차이점이면 그곳으로 두고 시점을 옮기고
    * 차이점이 아니라면 바로 밑의 차이점을 선택하고 시점을 옮겨준다.
    * */
    void nowDifferenceOnAction();
    /*
    * 가장 마지막의 차이점으로 이동한다.
    * */
    void lastDifferenceOnAction();
    /*
    * 새로운 탭을 하나 만든다.
    * */
    void newTabMenuItemOnAction();
    /*
    * open window 를 열어서 파일을 load 한다.
    * */
    void openMenuItemOnAction();
    /*
    * save window 를 열어서 파일을 save 한다.
    * */
    void saveMenuItemOnAction();
    /*
    * right file 만 save 한다.
    * */
    void saveRightFileMenuItemOnAction();
    /*
    * left file 만 save 한다.
    * */
    void saveLeftFileMenuItemOnAction();
    /*
    * 현재 프로그램을 닫는다.
    * */
    void closeMenuItemOnAction();
    /*
    * help window 를 연다.
    * */
    void helpMenuItemOnAction();
    /*
    * program information window 를 연다.
    * */
    void programInformationMenuItemOnAction();
    /*
    * tab 이 종료될 때의 행동
    * */
    void tabCloseAction();
    /*
    * tab menu item 을 통하여 tab 을 종료시킨다.
    * */
    void closeTabMenuItemOnAction();
    /*
    * 모든 탭을 종료시킨다.
    * */
    void closeTabAllMenuItemOnAction();
}
