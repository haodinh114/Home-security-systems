package view.screens;

import model.SystemController;
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

    @Override
    public void refresh() {
        SystemController controller = Main.MAIN_SYSTEM;
        area.setText(controller.getListSensors());
    }

}
