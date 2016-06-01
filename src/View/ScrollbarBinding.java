package View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;

/**
 * Created by JRD on 03/03/2015.
 */
public class ScrollbarBinding {

    public static final int BIND_BIDIRECTIONAL = 3;
    public static final int BIND_RIGHT_TO_LEFT = 1;
    public static final int BIND_LEFT_TO_RIGHT = 2;

    /**
     * Bidirectional binding of 2 ListView's scrollbars.
     */
    public static void bind(ListView lv1, ListView lv2) {
        bind(lv1, lv2, BIND_BIDIRECTIONAL);
    }

    /**
     * Allows binding of 2 ListView's scrollbars.
     * @param lv1 left list view
     * @param lv2 right list view
     * @param bindType 3 binding possibilities : BIND_BIDIRECTIONAL=bidirectional, BIND_RIGHT_TO_LEFT and BIND_LEFT_TO_RIGHT
     */
    public static void bind(ListView lv1, ListView lv2, int bindType) {
        ScrollBar bar1 = null;
        ScrollBar bar2 = null;

        for (Node node : lv1.lookupAll(".scroll-bar")) {
            if (node instanceof ScrollBar && ((ScrollBar)node).getOrientation().equals(Orientation.VERTICAL)) {
                bar1 = (ScrollBar)node;
            }
        }
        for (Node node : lv2.lookupAll(".scroll-bar")) {
            if (node instanceof ScrollBar && ((ScrollBar)node).getOrientation().equals(Orientation.VERTICAL)) {
                bar2 = (ScrollBar)node;
            }
        }
        if (bar1 == null || bar2 == null) return;

        final ScrollBar fbar1 = bar1;
        final ScrollBar fbar2 = bar2;
        if (fbar1 != null && (bindType & BIND_RIGHT_TO_LEFT) != 0) {
            fbar1.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    fbar2.setValue(newValue.doubleValue());
                }
            });
        }
        if (fbar2 != null && (bindType & BIND_LEFT_TO_RIGHT) != 0) {
            fbar2.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    fbar1.setValue(newValue.doubleValue());
                }
            });
        }
    }
}