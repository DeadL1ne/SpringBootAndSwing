package search.ui;


import org.springframework.lang.NonNull;

import javax.swing.*;
import java.awt.*;

abstract class AbstractForm {

    @NonNull
    JFrame frame;

    void hide() {
        frame.setVisible(false);
    }

    void show() {
        frame.setVisible(true);
    }

    void setFrameToCenterScreen() {
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenDimension.width - frame.getWidth()) / 2;
        int centerY = (screenDimension.height - frame.getHeight()) / 2;
        frame.setLocation(new Point(centerX, centerY));
    }
}
