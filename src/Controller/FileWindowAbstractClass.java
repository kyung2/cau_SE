package Controller;

import Model.ModelInterface;
import Model.ModelRealize;
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
        String left_file_name = null;
        String right_file_name = null;

        ModelInterface model = ModelRealize.getInstance();

        if(position.equals("left")) {
            try{
                right_file_name = new File(model.getUnit((int)tab.getUserData()).filepath(1)).getName();
            }catch (Exception e){}

            if(right_file_name != null){
                tab_name = name + " - " + right_file_name;
            }
            else tab_name = name + " - ";

            tab.setText(tab_name);
        }
        else {
            try {
                left_file_name = new File(model.getUnit((int)tab.getUserData()).filepath(0)).getName();

            }catch (Exception e){}

            if(left_file_name != null){
                tab_name = left_file_name + " - " + name;
            }
            else tab_name = " - " + name;

            tab.setText(tab_name);
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
