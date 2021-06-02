package view.screens;

import view.CircleButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class KeypadUI extends JPanel {

    private CircleButton b0, b1, b2, b3, b4, b5, b6, b7 , b8, b9, menu, del, off, home, away;
    private JPasswordField passField;
    private String password;
    JLabel instruction;

    public KeypadUI(String password) {
        createKeypad();
        this.password = password;
    }

    private void createKeypad() {
        setLayout(new BorderLayout());
        JPanel screen = new JPanel(new BorderLayout());
        instruction = new JLabel();
        instruction.setText("Enter PIN:");
        instruction.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel passArea = new JPanel(new GridLayout(1,3));
        passField = new JPasswordField();
        passField.setPreferredSize(new Dimension(100, 100));
        passField.setFont(new Font("Serif", Font.BOLD, 68));
        passField.setEditable(false);
        passField.setColumns(0);
        passArea.add(new JPanel());
        passArea.add(passField);
        passArea.add(new JPanel());
        initButtons();
        JPanel buttons = new JPanel(new GridLayout(5, 3));
        buttons.add(off);
        buttons.add(home);
        buttons.add(away);
        buttons.add(b7);
        buttons.add(b8);
        buttons.add(b9);

        buttons.add(b4);
        buttons.add(b5);
        buttons.add(b6);

        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);

        buttons.add(menu);
        buttons.add(b0);
        buttons.add(del);

        screen.add(instruction, BorderLayout.NORTH);
        screen.add(passArea, BorderLayout.CENTER);
        add(screen, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);
    }

    // initialize all of the buttons
    private void initButtons() {
        b0 = new CircleButton("0", new NumPad("0"));
        b1 = new CircleButton("1", new NumPad("1"));
        b2 = new CircleButton("2", new NumPad("2"));
        b3 = new CircleButton("3", new NumPad("3"));
        b4 = new CircleButton("4", new NumPad("4"));
        b5 = new CircleButton("5", new NumPad("5"));
        b6 = new CircleButton("6", new NumPad("6"));
        b7 = new CircleButton("7", new NumPad("7"));
        b8 = new CircleButton("8", new NumPad("8"));
        b9 = new CircleButton("9", new NumPad("9"));
        menu = new CircleButton("menu", null);
        del = new CircleButton("Delete",
                e -> {
                    // delete the last character if password length > 0
                    if (passField.getPassword().length>0) {
                        String temp = String.valueOf(passField.getPassword());
                        passField.setText(temp.substring(0, temp.length() - 1));
                    }
                });
        off = new CircleButton("off", null);
        home = new CircleButton("home", null);
        away = new CircleButton("away", null);
    }

    // The abstract class that manages the input of the numpad
    private class NumPad extends AbstractAction {

        // keeps track of the name of the button
        // should only be 1 char long, which are digit 0-9
        private NumPad(String name) {
            putValue(Action.NAME, name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            char num = getValue(Action.NAME).toString().charAt(0);
            // append password field if field is less than 4
            if (passField.getPassword().length < 4)
                passField.setText(String.valueOf(passField.getPassword()) + num);
            if (passField.getPassword().length == 4) {
                if (password.equals(String.valueOf(passField.getPassword()))) {
                    instruction.setText("Success!");
                }
                else {
                    instruction.setText("Incorrect: Enter PIN:");
                    passField.setText("");
                }
            }
        }
    }
}
