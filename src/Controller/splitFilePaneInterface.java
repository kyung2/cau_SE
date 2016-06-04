package Controller;

/**
 * Created by woojin on 2016-06-01.
 */
interface SplitFilePaneInterface {
    /*
    * load 버튼을 눌렀을 때
    * 파일을 불러와서 text area 에 적고
    * edit 버튼을 활성화한다.
    * 두 text area 가 모두 파일이 있고 edit 상태가 아닐 때 compare 버튼의 활성화한다.
    * compare 하여 list view 가 있을 때 load 하면 text area 를 visible 시키고 list view 를 invisible 시킨다.
    * */
    void leftLoadButtonOnAction();
    void rightLoadButtonOnAction();
    /*
    * edit 버튼을 눌렀을 때
    * text area 가 edit 상태가 아니라면
    * text area 를 editable 하게 하고
    * load 버튼을 비활성화하고 save 버튼을 활성화한다.
    * compare 하여 list view 가 있을 때 load 하면 text area 를 visible 시키고 list view 를 invisible 시킨다.
    * ////////////////////////////////////
    * text area 가 edit 상태 라면
    * edit 버튼을 비활성화 하고
    * 두 text area 가 모두 파일이 있고 edit 상태가 아닐 때 compare 버튼의 활성화한다.
    * */

    void leftEditButtonOnAction();
    void rightEditButtonOnAction();
    /*
    * save 버튼을 눌렀을 때
    * text area 의 내용을 저장한다.
    * load 버튼과 edit 버튼을 활성화한다.
    * 두 text area 가 모두 파일이 있고 edit 상태가 아닐 때 compare 버튼의 활성화한다.
    * */
    void leftSaveButtonOnAction();
    void rightSaveButtonOnAction();
    /*
    * list view 를 클릭했을 때
    * 양쪽의 클릭을 동기화 시키고
    * 위치에 맞게 버튼과 menu item 을 활성화 한다.
    * */
    void onLeftListViewMouseClicked();
    void onRightListViewMouseClicked();

}
