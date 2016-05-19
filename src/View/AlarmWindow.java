package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by woojin on 2016-05-19.
 * 알람 창을 만들어 준다
 * 알람 창의 타이틀과 라벨을 받아서 해당하는 알람창을 만듬
 * 버튼은 Yes 와 No를 가지고 있다.
 * 어떤 버튼이 눌렸는지 boolean 형으로 isYes 함수를 통해 알려준다.
 */
public class AlarmWindow extends Stage {

    public AlarmWindow(String title, String label) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Fxml/AlarmWindow.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);

        this.setTitle(title);
        this.setScene(scene);
        getLabel().setText(label);
    }
    private Label getLabel(){
        return (Label)((AnchorPane)this.getScene().getRoot()).getChildren().get(0);
    }

}