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
        Image frontdoor;
        Image bedroom;
        Image livingroom;
        Image kitchen;
        Image resizeFD;

        ImageIcon scaledFD;
        ImageIcon scaledBR;
        ImageIcon scaledLV;
        ImageIcon scaledKT;

        public Picture(){
            setLayout(new BorderLayout());
            loadImage();

        }

        private void loadImage(){
            ImageIcon fd = new ImageIcon("src/assets/frontdoor.png");
            frontdoor = fd.getImage();
            resizeFD = frontdoor.getScaledInstance(700, 350, Image.SCALE_DEFAULT);
            scaledFD = new ImageIcon(resizeFD);

            ImageIcon br = new ImageIcon("src/assets/bedroom.png");
            bedroom = br.getImage();
            Image resizeBR = bedroom.getScaledInstance(700, 350, Image.SCALE_DEFAULT);
            scaledBR = new ImageIcon(resizeBR);

            ImageIcon lr = new ImageIcon("src/assets/livingroom.png");
            livingroom = lr.getImage();
            Image resizeLV = livingroom.getScaledInstance(700, 350, Image.SCALE_DEFAULT);
            scaledLV = new ImageIcon(resizeLV);

            ImageIcon kt = new ImageIcon("src/assets/kitchen.png");
            kitchen = kt.getImage();
            Image resizeKT = kitchen.getScaledInstance(700, 350, Image.SCALE_DEFAULT);
            scaledKT = new ImageIcon(resizeKT);
        }

        @Override
        public void paintComponent(Graphics g) {

            super.paintComponent(g);

            scaledFD.paintIcon(this, g, 0,0 );

            scaledBR.paintIcon(this, g, 705,0 );

            scaledLV.paintIcon(this, g, 705,355 );

            scaledKT.paintIcon(this, g, 0,355 );

        }


    }
}

