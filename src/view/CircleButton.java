package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class CircleButton extends JPanel {

    private Color unpressed, pressed, current;
    private int offset, x, y, width, height;
    private String name;
    private final ActionListener listener;

    public CircleButton(String name, ActionListener listener) {
        this.name = name;
        Mouse mouse = new Mouse();
        makeButton();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        this.listener = listener;
    }

    private void makeButton () {
        unpressed = new Color(0xaaaaaa);
        pressed = new Color(0x777777);
        current = unpressed;
        offset = 10;
        updateCircle();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateCircle();
        g.setColor(current);
        g.fillOval(offset, offset, width, height);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Serif", Font.BOLD, 16));
        drawingString(g, name);
    }

    private void updateCircle() {
        x = getWidth() / 2;
        y = getHeight() / 2;
        width = this.getWidth() - 2 * offset;
        height = this.getHeight() - 2 * offset;
    }

    private void drawingString(Graphics g, String s) {
        Rectangle2D bounds = g.getFontMetrics().getStringBounds(s, g);
        int x = (this.getWidth() - (int) bounds.getWidth()) / 2;
        int y = this.getHeight() / 2 + 4;
        g.drawString(s, x ,  y);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    private class Mouse extends MouseAdapter {
        private int press = 0;

        private boolean isInside(int mouseX, int mouseY) {
            int distX = x - mouseX;
            int distY = y - mouseY;
            double angle;
            if (distX == 0 && distY == 0) angle = 0;
            else if (distX == 0) angle = Math.atan(1.0 * distX / distY);
            else angle = Math.atan(1.0 * distY / distX);
            double dist =  Math.sqrt(distX * distX + distY * distY);
            double elX = 1.0 * height/2 * Math.cos(angle);
            double elY = 1.0 * width/2 * Math.sin(angle);
            double dist2 = Math.sqrt(width*width*height*height/16.0/(elX * elX + elY * elY));
            return dist < dist2;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            if (isInside(e.getX(), e.getY())) {
                current = pressed;
                press = 1;
            }
            paintComponent(getGraphics());
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            if (!isInside(e.getX(), e.getY())) {
                current = unpressed;
                press = 0;
            }
            else {
                current = pressed;
                press = 1;
            }
            paintComponent(getGraphics());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            current = unpressed;
            paintComponent(getGraphics());
            if (press == 1 && listener != null) {
                ActionEvent g = new ActionEvent(e.getSource(), e.getID(), e.paramString());
                listener.actionPerformed(g);
            }
        }
    }

}
