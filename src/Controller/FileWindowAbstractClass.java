package Controller;

import View.AlarmWindow;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by woojin on 2016-05-31.
 */
abstract class FileWindowAbstractClass implements FileWindowInterface {
    @Override
    abstract public void leftFileFindButtonOnAction();
    @Override
    abstract public void rightFileFindButtonOnAction();
    @Override
    abstract public void okButtonOnAction ();
    @Override
    abstract public void cancelButtonOnAction();

    void initCancelButtonAction(String title, String content, Stage stage){
        AlarmWindow exitAlarmWindow = new AlarmWindow(title, content);
        exitAlarmWindow.showAndWait();
        if ((boolean) exitAlarmWindow.getUserData()) {
            stage.close();
        }
    }

    void changeTabName(Tab tab, String name, String position){
        String tab_name = tab.getText();
        if(position.equals("left")) {
            if (tab_name.equals("File")) {
                tab_name = name + " - ";
                tab.setText(tab_name);
            }
            else{
                tab_name = name + " -" + tab_name.split("-")[1];
                tab.setText(tab_name);
            }
        }
        else {
            if (tab_name.equals("File")) {
                tab_name = " - " + name;
                tab.setText(tab_name);
            }
            else{
                tab_name = tab_name.split("-")[0] + "- " + name;
                tab.setText(tab_name);
            }
        }
    }

    FileChooser loadFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("FileChooser");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files","*.txt", "*.java", "*.c", "*.cpp"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Java Files", "*.java"),
                new FileChooser.ExtensionFilter("C Files", "*.c","*.cpp")
        );

        File currentDirFile = new File(".");
        String init_path = currentDirFile.getAbsolutePath();
        init_path = init_path.substring(0, init_path.length() - 2);

        //String init_path = System.getProperty("user.home")+File.separator+"Documents";

        fileChooser.setInitialDirectory(new File(init_path));
        return fileChooser;
    }
}
