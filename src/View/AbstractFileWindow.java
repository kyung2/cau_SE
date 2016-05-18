package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

/**
 * Created by woojin on 2016-05-18.
 */
public abstract class AbstractFileWindow extends Stage {
    public AbstractFileWindow(String s) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Fxml/FileWindow.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
            Scene scene = new Scene(root);
            this.setTitle(s);
            this.setScene(scene);
            this.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
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

    abstract protected void addButtonAction();
}
