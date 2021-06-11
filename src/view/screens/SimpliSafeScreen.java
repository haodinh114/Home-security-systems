package view.screens;

import model.TriggerSimulator;
import sensors.SensorController;
import view.Main;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class SimpliSafeScreen extends JPanel {

    JComboBox<Trigger> door, wind, mot, temp, smoke;
    Trigger currDoor, currWind, currMot, currTemp, currSmoke;
    JButton bDoor, bWind, bMot, bTemp, bSmoke;
    TriggerSimulator simulator;

    /**
     * Makes the SimpliSafe screen where it shows the trigger simulator
     */
    public SimpliSafeScreen() {
        setLayout(new GridLayout(5, 1));
        initComboBoxes();
        initButtons();
        String list = Main.MAIN_SYSTEM.getListSensors();
        Scanner line = new Scanner(list);
        simulator = new TriggerSimulator(Main.MAIN_SYSTEM);
        int i = 0;
        while (line.hasNextLine()) {
            SensorController sensor = Main.MAIN_SYSTEM.getSensor(i);
            Trigger trigger = new Trigger(sensor.getSensorName(), i);
            chooseCombo(sensor.getSensorName(), trigger);
            line.nextLine();
            i++;
        }
        add(makePanel("Door", door, bDoor));
        add(makePanel("Window", wind, bWind));
        add(makePanel("Motion", mot, bMot));
        add(makePanel("Temperature", temp, bTemp));
        add(makePanel("Smoke", smoke, bSmoke));
    }

    // initialize combo boxes
    private void initComboBoxes() {
        door = new JComboBox<>();
        door.addActionListener(e -> currDoor = (Trigger) door.getSelectedItem());
        wind = new JComboBox<>();
        wind.addActionListener(e -> currWind = (Trigger) wind.getSelectedItem());
        mot = new JComboBox<>();
        mot.addActionListener(e -> currMot = (Trigger) mot.getSelectedItem());
        temp = new JComboBox<>();
        temp.addActionListener(e -> currTemp = (Trigger) temp.getSelectedItem());
        smoke = new JComboBox<>();
        smoke.addActionListener(e -> currSmoke = (Trigger) smoke.getSelectedItem());
    }

    // initialize buttons
    private void initButtons() {
        bDoor = new JButton("Trigger");
        bDoor.addActionListener(e -> trigger(currDoor));
        bWind = new JButton("Trigger");
        bWind.addActionListener(e -> trigger(currWind));
        bMot = new JButton("Trigger");
        bMot.addActionListener(e -> trigger(currMot));
        bTemp = new JButton("Trigger");
        bTemp.addActionListener(e ->trigger(currTemp));
        bSmoke = new JButton("Trigger");
        bSmoke.addActionListener(e ->trigger(currSmoke));
    }

    // adds trigger to combobox and returns the curr, but change to trigger if curr is null
    private Trigger addToCombo(JComboBox<Trigger> box, Trigger t, Trigger curr) {
        box.addItem(t);
        if (curr == null) return t;
        return curr;
    }

    // choose the combo box for some trigger.
    private void chooseCombo(String s, Trigger t) {
        switch (s.substring(0, s.length()-1)) {
            case "Door" -> currDoor = addToCombo(door, t, currDoor);
            case "Window" -> currWind = addToCombo(wind, t, currWind);
            case "Motion" -> currMot = addToCombo(mot, t, currMot);
            case "Temperature" -> currTemp = addToCombo(this.temp, t, currTemp);
            case "Smoke" -> currSmoke = addToCombo(smoke, t, currSmoke);
        }
    }

    // make an individual panel for a label, combo box, and button
    private JPanel makePanel(String s, JComboBox<Trigger> c, JButton b) {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.add(new JLabel(s));
        panel.add(c);
        panel.add(b);
        return panel;
    }

    // triggers a sensor
    private void trigger(Trigger t) {
        simulator.triggerSensor(Main.MAIN_SYSTEM.getSensor(t.index));
    }

    // creates a trigger with the name and index of a sensor
    private record Trigger(String name, int index) {

        @Override
        public String toString() {
            return name;
        }
    }
}
