package view;

import model.SystemController;
import view.screens.Screen;

import javax.swing.*;
import java.awt.*;

/**
 * The phone app used for SimpliSafe
 */
public class PhoneApp extends JFrame {
    /**
     * The main screen of the phone app
     */
    public static final Screen SCREEN = new Screen();

    /**
     * constructs the window for the phone app
     */
    public PhoneApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 650));
        add(SCREEN);
        pack();
        setVisible(true);
    }

}
