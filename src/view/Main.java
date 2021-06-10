package view;

import model.SystemController;
import model.TriggerSimulator;

public class Main {

    public static final SystemController MAIN_SYSTEM = new SystemController();
    public static final TriggerSimulator SIMULATOR = new TriggerSimulator(MAIN_SYSTEM);

    public static void main(String[] args){
        PhoneApp app = new PhoneApp("SimpliSafe");
    }
}
