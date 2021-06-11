package view;

import model.SystemController;
import model.TriggerSimulator;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static final SystemController MAIN_SYSTEM = new SystemController();
    public static final Dimension INITIAL = new Dimension(new Dimension(400, 650));
    public static final PhoneApp APP = new PhoneApp("SimpliSafe",INITIAL);

    public static void main(String[] args) {
    }
}
