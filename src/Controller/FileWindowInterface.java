package Controller;

/**
 * Interface for FileWindowAbstractClass
 * Created by woojin on 2016-05-31.
 */
interface FileWindowInterface {

    /*
    * click left find button
    * get file path and name for load or save left file
    * */
    void leftFileFindButtonOnAction();

    /*
    * click right find button
    * get file path and name for load or save right file
    * */
    void rightFileFindButtonOnAction();

    /*
    * click ok button
    * save or load file with find value
    * */
    void okButtonOnAction();

    /*
    * click cancel button
    * If it find no file just close the window
    * If it find file make alarm window for ask one more
    * */
    void cancelButtonOnAction();
}
