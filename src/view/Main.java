package view;

import model.SystemController;
import model.TriggerSimulator;

public class Main {

    public static final SystemController MAIN_SYSTEM = new SystemController();

    public static void main(String[] args){
        new PhoneApp("SimpliSafe");
    }
}
