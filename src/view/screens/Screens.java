package view.screens;

import javax.swing.*;

/**
 * The enum manages all of the content in the different screen,
 * as well as where the screen need to move back to
 */
public enum Screens {
    HOME(new HomeScreen(), null),
    NOTIFICATION(new NotificationScreen(), HOME),
    ARM_DISARM(new KeypadUI(), HOME),
    STATUS(new StatusScreen(), HOME),
    TEMPERATURE(new TemperatureScreen(), HOME),
    CAMERA(new CameraScreen(), HOME),
    SIMPLISAFE(new SimpliSafeScreen(), HOME);

    private final Screens prev;
    private final JPanel panel;

    /**
     * assign a panel and a previous screen per screen
     * @param panel the panel
     * @param prev the previous screen
     */
    Screens(JPanel panel, Screens prev) {
        this.panel = panel;
        this.prev = prev;
    }

    /**
     * get the current panel
     * @return the current panel
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * gets the previous screen
     * @return the previous screen
     */
    public Screens getPrev() {
        return prev;
    }
}
