package view.screens;

import view.PhoneApp;

import javax.swing.*;
import java.awt.*;

/**
 * This class contains the home screen view
 */
public class HomeScreen extends JPanel {
    private JButton notice, arming, sensors, temp, camera, simpli;
    private JTextField status = new JTextField();

    /**
     * The create the home screen
     */
    public HomeScreen() {
        setLayout(new BorderLayout());
        status.setPreferredSize(new Dimension(100, 100));
        add(status, BorderLayout.NORTH);
        JPanel buttons = new JPanel(new GridLayout(3, 2));
        initializeButtons();
        buttons.add(notice);
        buttons.add(arming);
        buttons.add(sensors);
        buttons.add(temp);
        buttons.add(camera);
        buttons.add(simpli);
        add(buttons, BorderLayout.CENTER);
    }

    // initialize the buttons
    private void initializeButtons() {
        notice = new JButton("Notification");
        notice.addActionListener(e -> PhoneApp.SCREEN.updateScreen(Screens.NOTIFICATION));
        arming = new JButton("Arm/Disarm");
        arming.addActionListener(e -> PhoneApp.SCREEN.updateScreen(Screens.ARM_DISARM));
        sensors = new JButton("System Status/Sensors");
        sensors.addActionListener(e -> PhoneApp.SCREEN.updateScreen(Screens.SENSORS));
        temp = new JButton("Temperature");
        temp.addActionListener(e -> PhoneApp.SCREEN.updateScreen(Screens.TEMPERATURE));
        camera = new JButton("Camera");
        camera.addActionListener(e -> PhoneApp.SCREEN.updateScreen(Screens.CAMERA));
        simpli = new JButton("SimpliSafe");
        simpli.addActionListener(e -> PhoneApp.SCREEN.updateScreen(Screens.SIMPLISAFE));
    }
}
