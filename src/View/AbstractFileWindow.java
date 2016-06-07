package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Abstract class for Open and Save file window
 * This class make stage with parameter ( title and controller file )
 * It is designed by FileWindow.fxml file in /View/Fxml package
 * It has two label, two text area, two button for open or save left and right file
 * Created by woojin on 2016-05-18.
 * @author woojin Jang
 */
abstract class AbstractFileWindow extends Stage {
    protected AbstractFileWindow(String title, Object controllerFile) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Fxml/FileWindow.fxml"));
        loader.setController(controllerFile);

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        this.setTitle(title);
        this.setScene(scene);
        this.getIcons().add(new Image("/View/Image/mainIcon.png"));
        this.show();
    }

    protected Label getWindowLabel(){
        return (Label)(((AnchorPane)this.getScene().getRoot()).getChildren().get(7));
    }
    protected Label getRightFileLabel(){
        return (Label)(((AnchorPane)this.getScene().getRoot()).getChildren().get(0));
    }
    protected Label getLeftFileLabel(){
        return (Label)(((AnchorPane)this.getScene().getRoot()).getChildren().get(1));
    }
    protected TextField getWarningInfoTextArea(){
        return (TextField) (((AnchorPane)this.getScene().getRoot()).getChildren().get(2));
    }
    protected TextField getRightFileTextArea(){
        return (TextField) (((AnchorPane)this.getScene().getRoot()).getChildren().get(3));
    }
    protected TextField getLeftFileTextArea(){
        return (TextField) (((AnchorPane)this.getScene().getRoot()).getChildren().get(4));
    }
    protected Button getRightFileFindButton(){
        return (Button)(((AnchorPane)this.getScene().getRoot()).getChildren().get(5));
    }
    protected Button getLeftFileFindButton(){
        return (Button)(((AnchorPane)this.getScene().getRoot()).getChildren().get(6));
    }
    protected Button getOkButton(){
        return (Button)(((AnchorPane)this.getScene().getRoot()).getChildren().get(8));
    }
    protected Button getCancelButton(){
        return (Button)(((AnchorPane)this.getScene().getRoot()).getChildren().get(9));
    }

    abstract protected void initLabel();
}
