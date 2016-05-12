package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage MainFrame) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        MainFrame.setTitle("Simple Merge Program");
        MainFrame.setScene(new Scene(root, 600, 600));
        MainFrame.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
