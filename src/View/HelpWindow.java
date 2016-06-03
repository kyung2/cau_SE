package View;

import java.awt.*;
import java.io.*;

/**
 * Created by hyunkyung on 2016-06-01.
 */


public class HelpWindow {

    public void HelpWindow() throws Exception {
        File helperhtml =  new File("WebHelp/index.htm");
        Desktop.getDesktop().browse(helperhtml.toURI());

    }
}
/*최종버전이 완성되면 지워야하는 주석 */
/*
       Runtime run = Runtime.getRuntime();
        try {
            Process p = Runtime.getRuntime().exec("helper/simplemergehelper.chm");
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}*/
/*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Fxml/HelpWindow.fxml"));
        Parent root;
        try {

            root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Help");
            stage.getIcons().add(new Image("/View/Image/mainIcon.png"));
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
*/

