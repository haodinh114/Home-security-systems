package view;

import javax.swing.*;
import java.awt.*;

public class KeypadUI extends JPanel {

    private JButton b0, b1, b2, b3, b4, b5, b6, b7 , b8, b9;
    private JTextArea area;

    public KeypadUI() {
        initButtons();
        this.setLayout(new GridLayout(4, 3));
        add(b7);
        add(b8);
        add(b9);

        add(b4);
        add(b5);
        add(b6);

        add(b1);
        add(b2);
        add(b3);

        add(new JPanel());
        add(b0);
        add(new JPanel());
    }

    private void initButtons() {
        b0 = new JButton("0");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");
    }
}
