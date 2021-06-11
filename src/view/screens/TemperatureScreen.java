package view.screens;

import model.SystemController;
import view.Main;

import javax.swing.*;
import java.awt.*;

public class TemperatureScreen extends JPanel implements Refreshable{
    private JTextArea screen;
    public TemperatureScreen() {

        this.setLayout(new BorderLayout());

        screen = new JTextArea();
        screen.setEditable(false);
        add(new JScrollPane(screen), BorderLayout.CENTER);
        refresh();
    }

    @Override
    public void refresh() {
        SystemController controller = Main.MAIN_SYSTEM;
        //screen.setText(controller.getTemperature());
    }

}
