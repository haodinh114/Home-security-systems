package view.screens;

import model.SystemController;
import sensors.SensorController;
import view.Main;

import javax.swing.*;
import java.awt.*;

/**
 * This shows the current status of every sensors
 */
public class StatusScreen extends JPanel implements Refreshable {
    private JTextArea area;

    public StatusScreen() {
        this.setLayout(new BorderLayout());

        area = new JTextArea();
        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.CENTER);
        refresh();
    }

    public void refresh() {
        StringBuilder s = new StringBuilder();
        SystemController controller = Main.mainSystem;
        for (int i = 0; i < controller.getAmountofSensor(); i++) {
            SensorController sensor = controller.getSensor(i);
            s.append(sensor.getSensorName())
                    .append(" ")
                    .append(sensor.getSensor_type())
                    .append(" ")
                    .append(sensor.getStatus())
                    .append(" ")
                    .append(sensor.getCurrentAlert())
                    .append("\n");
        }
        area.setText(s.toString());
    }

}
