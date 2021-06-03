package view;

import view.screens.HomeScreen;
import view.screens.KeypadUI;

import javax.swing.*;
import java.awt.*;

public class PhoneApp extends JFrame {

    private HomeScreen home = new HomeScreen();
    private KeypadUI keypadUI = new KeypadUI("3456");

    public PhoneApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 650));
        add(home);
        add(new Border(), BorderLayout.NORTH);
        add(new Border(), BorderLayout.SOUTH);
        add(new Border(), BorderLayout.EAST);
        add(new Border(), BorderLayout.WEST);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new PhoneApp();
    }

}
