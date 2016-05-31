package Controller;

/**
 * Created by woojin on 2016-05-31.
 */
interface FileWindowInterface {
    /*
    * left find 버튼 을 눌렀을 때
    * left file 을 load or save
    * */
    void leftFileFindButtonOnAction();
    /*
    * right find 버튼을 눌렀을 때
    * right file 을 load or save
    * */
    void rightFileFindButtonOnAction();
    /*
    * ok 버튼을 눌렀을 때
    * 지금까지 찾은 file 을 바탕으로 save or load
    * */
    void okButtonOnAction();
    /*
    * cancel 버튼을 눌렀을 때
    * 찾은 file 이 없으면 그대로 꺼지고
    * 있을 경우 안내창의 띄워 물어본다.
    * */
    void cancelButtonOnAction();
}
