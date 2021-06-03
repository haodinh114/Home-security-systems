package view.screens;

import javax.swing.*;
import java.awt.*;

public class HomeScreen extends JPanel {
    private JButton notif = new JButton("Notification");
    private JButton arming = new JButton("Arm/Disarm");
    private JButton sensors = new JButton("System Status/Sensors");
    private JButton temptr = new JButton("Temperature");
    private JButton camera = new JButton("Camera");
    private JButton simpli = new JButton("SimpliSafe");
    private JTextField status = new JTextField();

    public HomeScreen() {
        setLayout(new BorderLayout());
        status.setPreferredSize(new Dimension(100, 100));
        add(status, BorderLayout.NORTH);
        JPanel buttons = new JPanel(new GridLayout(3, 2));
        buttons.add(notif);
        buttons.add(arming);
        buttons.add(sensors);
        buttons.add(temptr);
        buttons.add(camera);
        buttons.add(simpli);
        add(buttons, BorderLayout.CENTER);
    }
}
