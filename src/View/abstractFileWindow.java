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
 * Created by woojin on 2016-05-18.
 * Open window 와 Save window 를 위한 Abstract class
 * 원하는 title 과 controller file 을 받아서 stage 를 하나 만든다.
 * 구성요소로는
 * 생성자의 input 에 따라 결정되는 창의 Title,
 * 상단에 이 창이 어떤 일을 하는지 알려주는 label
 * 중간 상단에 'Left file' 이라고 적힌 label, 왼쪽 파일의 패스를 담을 text area, find 라 적힌 버튼
 * 중간 하단에 'Right file' 이라고 적힌 label, 오른쪽 파일의 패스를 담을 text area, find 라 적힌 버튼
 * 오른쪽 하단에 ok 버튼과 cancel 버튼
 * 가장 아래쪽에 각종 상태를 알려주는 text area 로 구성된다.
 */
abstract class AbstractFileWindow extends Stage {
    /* title 과 controller class 를 받아 stage 를 구성해 주는 생성자*/
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
    }

    /* 이 창이 어떤 일을 하는지 설명해 주는 부분을 반드시 만들어야 한다. */
    abstract protected void initLabel();

    /* 창을 구성하는 각종 구성요소들을 받아올 수 있는 method*/
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
}
