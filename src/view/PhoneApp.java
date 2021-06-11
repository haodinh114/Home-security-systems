package view;

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
    public PhoneApp(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1600, 1000));
        add(SCREEN);
        pack();
        setVisible(true);
    }

}
