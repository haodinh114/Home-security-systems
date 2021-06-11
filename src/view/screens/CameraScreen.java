package view.screens;

import javax.swing.*;
import java.awt.*;

public class CameraScreen extends JPanel {

    public CameraScreen() {
        this.setLayout(new BorderLayout());
        Picture pic = new Picture();
        add(pic);

    }

    public class Picture extends JPanel{
        ImageIcon frontdoor;
        ImageIcon bedroom;
        ImageIcon livingroom;
        ImageIcon kitchen;

        public Picture(){
            frontdoor = new ImageIcon("/Users/apple/Documents/GitHub/PRJ-3-group-3/src/assets/frontdoor.png");
            bedroom = new ImageIcon("/Users/apple/Documents/GitHub/PRJ-3-group-3/src/assets/bedroom.png");
            livingroom = new ImageIcon("/Users/apple/Documents/GitHub/PRJ-3-group-3/src/assets/livingroom.png");
            kitchen = new ImageIcon("/Users/apple/Documents/GitHub/PRJ-3-group-3/src/assets/kitchen.png");

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            frontdoor.paintIcon(this, g, 70,70 );

            bedroom.paintIcon(this, g, 140,70 );

            livingroom.paintIcon(this, g, 70,140 );

            kitchen.paintIcon(this, g, 140,140 );

        }

    }
}

