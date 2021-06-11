package view.screens;

import view.Border;
import view.Main;

import javax.swing.*;
import java.awt.*;

public class CameraScreen extends JPanel {

    Picture frontdoor, bedroom, livingroom, kitchen;

    /**
     * Creates the
     */
    public CameraScreen() {
        this.setLayout(new BorderLayout());
        // gets the files and make them pictures
        frontdoor = new Picture("src/assets/frontdoor.png");
        bedroom = new Picture("src/assets/bedroom.png");
        livingroom = new Picture("src/assets/livingroom.png");
        kitchen = new Picture("src/assets/kitchen.png");
        // makes all of the pictures
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(frontdoor);
        panel.add(bedroom);
        panel.add(livingroom);
        panel.add(kitchen);
        // show picture
        add(new JScrollPane(panel));
    }

    // private class to have resizable pictures
    private class Picture extends JPanel{
        private ImageIcon icon;

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

