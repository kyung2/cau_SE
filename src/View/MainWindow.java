package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        //fxmlLoader.setController("Controller/Controller.java");

        Parent root = FXMLLoader.load(getClass().getResource("/View/Fxml/MainWindow.fxml"));
        primaryStage.setTitle("Simple Merge");
        primaryStage.setScene(new Scene(root));
        primaryStage.getScene().getStylesheets().add("/View/Css/mainWindow.css");
        //아이콘 이미지 바꾸는거 알아서 바꾸시길 ... 찾아서
        primaryStage.getIcons().add(new Image("/View/Image/sampleIcon.jpg"));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}