package view.screens;

import view.EllipseButton;
import view.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * This class is the User Interface for the SimpliSafe keypad.
 * This class is able to verify a user via a password system.
 */
public class KeypadUI extends JPanel implements Refreshable {

    private EllipseButton b0, b1, b2, b3, b4, b5, b6, b7 , b8, b9, menu, del, off, home, away;
    private JPasswordField passField;
    JLabel instruction;
    private Arming curr;
    private final String enterPin = "Enter PIN:", option = "Select an Option:",
            success = "Success!", invalid = "Invalid PIN!";

    /**
     * Initialize the keypad with a password
     */
    public KeypadUI() {
        createKeypad();
    }

    // create the whole keypad
    private void createKeypad() {
        setLayout(new BorderLayout());
        JPanel screen = getScreen();
        JPanel buttons = getButtons();
        JPanel panic = new JPanel();
        EllipseButton panicButton = new EllipseButton("panic",
                new Color(0xff0000),
                new Color(0xcc0000),
                new Color(0xcc5555),
                null);
        panicButton.setPreferredSize(new Dimension(60, 60));
        panic.add(panicButton);
        add(screen, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);
        add(panic, BorderLayout.SOUTH);
        curr = null;
    }

    // creates the screen of the keypad
    private JPanel getScreen() {
        JPanel screen = new JPanel(new BorderLayout());
        instruction = new JLabel();
        instruction.setText(option);
        instruction.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel passArea = new JPanel(new BorderLayout());
        passField = new JPasswordField();
        passField.setPreferredSize(new Dimension(100, 100));
        passField.setFont(new Font("Serif", Font.BOLD, 68));
        passField.setEditable(false);
        passField.setColumns(0);
        passArea.add(new JPanel(), BorderLayout.EAST);
        passArea.add(passField);
        passArea.add(new JPanel(), BorderLayout.WEST);
        screen.add(instruction, BorderLayout.NORTH);
        screen.add(passArea, BorderLayout.CENTER);
        return screen;
    }

    // adds the button onto a keypad
    private JPanel getButtons() {
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
        disableNumpad();
        return buttons;
    }

    // initialize all of the buttons
    private void initButtons() {
        b0 = new EllipseButton("0", new NumPad("0"));
        b1 = new EllipseButton("1", new NumPad("1"));
        b2 = new EllipseButton("2", new NumPad("2"));
        b3 = new EllipseButton("3", new NumPad("3"));
        b4 = new EllipseButton("4", new NumPad("4"));
        b5 = new EllipseButton("5", new NumPad("5"));
        b6 = new EllipseButton("6", new NumPad("6"));
        b7 = new EllipseButton("7", new NumPad("7"));
        b8 = new EllipseButton("8", new NumPad("8"));
        b9 = new EllipseButton("9", new NumPad("9"));
        menu = new EllipseButton("menu", null);
        del = new EllipseButton("Delete",
                e -> {
                    // delete the last character if password length > 0
                    if (passField.getPassword().length>0) {
                        String temp = String.valueOf(passField.getPassword());
                        passField.setText(temp.substring(0, temp.length() - 1));
                    }
                });
        off = new EllipseButton("off", new Arming("off"));
        home = new EllipseButton("home", new Arming("home"));
        away = new EllipseButton("away", new Arming("away"));
    }

    // disable the numpad buttons
    private void disableNumpad() {
        b0.disableButton();
        b1.disableButton();
        b2.disableButton();
        b3.disableButton();
        b4.disableButton();
        b5.disableButton();
        b6.disableButton();
        b7.disableButton();
        b8.disableButton();
        b9.disableButton();
        del.disableButton();
    }

    // enable the numpad buttons
    private void enableNumpad() {
        b0.enableButton();
        b1.enableButton();
        b2.enableButton();
        b3.enableButton();
        b4.enableButton();
        b5.enableButton();
        b6.enableButton();
        b7.enableButton();
        b8.enableButton();
        b9.enableButton();
        del.enableButton();
    }

    @Override
    public void refresh() {

    }

    private class Arming extends AbstractAction {
        private Arming(String name) {
            putValue(Action.NAME, name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            curr = this;
            enableNumpad();
            instruction.setText(enterPin);
        }
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
                String pin = Main.mainSystem.getPinCode();
                if (pin == null) pin = "1234";
                if (pin.equals(String.valueOf(passField.getPassword()))) {
                    if (curr != null) {
                        if ("off".equals(curr.getValue(Action.NAME)))
                            Main.mainSystem.disArmSystem();
                        else
                            Main.mainSystem.armSystem();
                        instruction.setText(success);
                        passField.setText("");
                        disableNumpad();
                    }
                }
                else {
                    instruction.setText(invalid);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    passField.setText("");
                    instruction.setText(enterPin);
                }
            }
        }
    }
}
