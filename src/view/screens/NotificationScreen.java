package view.screens;

import view.Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * This screen allows you to see past Sensor notifications
 */
public class NotificationScreen extends JPanel implements Refreshable {

    private JTextArea area;

    public NotificationScreen() {
        setLayout(new BorderLayout());
        area = new JTextArea();
        area.setLineWrap(true);
        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.CENTER);
        refresh();
    }

    @Override
    public void refresh() {
        StringBuilder s = new StringBuilder(Main.MAIN_SYSTEM.selectAllRecords());
        Scanner line = new Scanner(s.toString());
        List<String> lines = new ArrayList<>();
        while (line.hasNextLine())
            lines.add(line.nextLine());
        s = new StringBuilder();
        for (String x: lines) {
            Scanner scan = new Scanner(x);
            scan.next();
            s.append(scan.next());
            s.append(" ").append(scan.next()).append(" ").append(scan.next());
            scan.next();
            s.append("\n    ").append(scan.nextLine()).append("\n\n");
        }
        area.setText(s.toString());
    }
}
