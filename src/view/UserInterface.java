package view;

import javax.swing.*;
import java.awt.*;

public class UserInterface extends JPanel {
    private JPanel main;
    public UserInterface() {
        setLayout(new BorderLayout());
        setMain();
        add(main);
    }

    private void setMain() {
        main = new JPanel(new BorderLayout());
        main.add(new JPanel());
        JPanel content = new JPanel(new GridLayout(2, 1));
        JPanel arming = new JPanel(new GridLayout(1, 2));
        JPanel arm = new JPanel(new BorderLayout());
        arm.add(new JButton("arm"));
        JPanel disarm = new JPanel(new BorderLayout());
        disarm.add(new JButton("disarm"));
        arming.add(arm);
        arming.add(disarm);
        JPanel info = new JPanel(new BorderLayout());
        info.add(new JLabel("information", JLabel.CENTER));
        info.setBackground(new Color(0xaaaaaa));
        content.add(arming);
        content.add(info);
        main.add(content);
    }
}
