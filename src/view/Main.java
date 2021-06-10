package view;

import model.SystemController;
import model.TriggerSimulator;
import sensors.*;
import view.PhoneApp;

public class Main {

    public static final SystemController mainSystem = new SystemController();
    public static final TriggerSimulator simulator = new TriggerSimulator(mainSystem);

    public static void main(String[] args){
        PhoneApp app = new PhoneApp("SimpliSafe");

        System.out.println(mainSystem.getListSensors());

        TriggerSimulator triggerSimulator = new TriggerSimulator(mainSystem);

    }
}
