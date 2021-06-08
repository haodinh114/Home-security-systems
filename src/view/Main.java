package view;

import model.SystemController;
import model.TriggerSimulator;
import sensors.*;
import view.PhoneApp;

public class Main {

    public static final SystemController mainSystem = new SystemController();
    public static final TriggerSimulator simulator = new TriggerSimulator(mainSystem);

    public static void main(String[] args){
        //SystemController mainSystem = new SystemController();
        PhoneApp app = new PhoneApp();
        mainSystem.addSensor(new DoorSensor("1"));
        mainSystem.addSensor(new DoorSensor("2"));

        mainSystem.addSensor(new WindowSensor("1"));
        mainSystem.addSensor(new WindowSensor("2"));

        mainSystem.addSensor(new SmokeSensor("1"));
        mainSystem.addSensor(new SmokeSensor("2"));
        mainSystem.addSensor(new SmokeSensor("3"));
        mainSystem.addSensor(new SmokeSensor("4"));

        mainSystem.addSensor(new MotionSensor("1"));
        mainSystem.addSensor(new MotionSensor("2"));
        mainSystem.addSensor(new MotionSensor("3"));
        mainSystem.addSensor(new MotionSensor("4"));

        mainSystem.addSensor(new TemperatureSensor("1"));
        mainSystem.addSensor(new TemperatureSensor("2"));
        mainSystem.addSensor(new TemperatureSensor("3"));
        mainSystem.addSensor(new TemperatureSensor("4"));

        System.out.println(mainSystem.getListSensors());

        TriggerSimulator triggerSimulator = new TriggerSimulator(mainSystem);

        triggerSimulator.triggerSensor(mainSystem.getSensor(3));

        System.out.println(mainSystem.getSensor(3).getCurrentAlert());


    }
}
