package view.screens;

import view.Main;

import javax.swing.*;
import java.awt.*;

public class CameraScreen extends JPanel {

    Picture frontDoor, bedroom, livingRoom, kitchen;

    /**
     * Creates the
     */
    public CameraScreen() {
        this.setLayout(new BorderLayout());
        // gets the files and make them pictures
        frontDoor = new Picture("src/assets/frontdoor.png");
        bedroom = new Picture("src/assets/bedroom.png");
        livingRoom = new Picture("src/assets/livingroom.png");
        kitchen = new Picture("src/assets/kitchen.png");
        // makes all of the pictures
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(frontDoor);
        panel.add(bedroom);
        panel.add(livingRoom);
        panel.add(kitchen);
        // put in scroll pane
        JScrollPane pane = new JScrollPane(panel);
        pane.getVerticalScrollBar().setUnitIncrement(10);

        // add scroll pane
        add(pane);
    }

    // private class to have resizable pictures
    private static class Picture extends JPanel{
        private final ImageIcon icon;

        // creates a picture with a default size based on the default screen size
        private Picture(String s) {
            icon = new ImageIcon(s);
            double ratio = (Main.INITIAL.getWidth() - 100.0) / icon.getIconWidth();
            setPreferredSize(new Dimension((int) (icon.getIconWidth() * ratio),
                    (int) (icon.getIconHeight() * ratio)));
        }

        // get ratio of panel based on screen width and image width
        private double panelRatio() {
            return (Main.APP.getWidth() - 100.0) / icon.getIconWidth();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            // sets the size of the image based on the ratio of the screen and image size
            double ratio = panelRatio();
            setPreferredSize(new Dimension((int) (icon.getIconWidth() * ratio),
                    (int) (icon.getIconHeight() * ratio)));
            setSize(getPreferredSize());
            // draw image
            g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), null);
        }

    }
}

