package view;

import javax.swing.*;
import java.awt.*;

public class PhoneApp extends JFrame {

    public PhoneApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 650));
        //add(new UserInterface());
        add(new KeypadUI());
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new PhoneApp();
    }

}
