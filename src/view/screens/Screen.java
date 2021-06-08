package view.screens;

import view.Border;

import javax.swing.*;
import java.awt.*;

/**
 * This is the screen that is within the frame it manages the different screen interactions
 */
public class Screen extends JPanel {
    private Screens current;
    private JPanel back;
    private Border north = new Border();

    /**
     *  Creates the general layout of the screen and put the default HomeScreen
     */
    public Screen() {
        setLayout(new BorderLayout());
        current = Screens.HOME;
        add(north, BorderLayout.NORTH);
        add(new Border(), BorderLayout.SOUTH);
        add(new Border(), BorderLayout.EAST);
        add(new Border(), BorderLayout.WEST);
        createBackButton();
        add(current.getPanel(), BorderLayout.CENTER);
    }

    // creates a back button for when you wish to go back to the home screen
    private void createBackButton() {
        back = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JButton button = new JButton("Back");
        button.addActionListener(e -> updateScreen(current.getPrev()));
        back.setLayout(new FlowLayout(FlowLayout.LEADING));
        back.setBackground(new Color(0xcccccc));
        back.add(button);
    }

    /**
     * updates the content of the screen
     * @param screen the screen's content
     */
    public void updateScreen(Screens screen) {
        remove(current.getPanel());
        current = screen;
        if(current.getPrev() == null) {
            remove(back);
            add(north, BorderLayout.NORTH);
        }
        else {
            remove(north);
            add(back, BorderLayout.NORTH);
        }
        add(current.getPanel(), BorderLayout.CENTER);
        if (current.getPanel() instanceof Refreshable) {
            ((Refreshable) current.getPanel()).refresh();
        }
        revalidate();
        repaint();
    }

}
