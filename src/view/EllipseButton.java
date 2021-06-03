package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * This class manages the shape and interaction of a button shaped like an ellipse.
 */
public class EllipseButton extends JPanel {

    private Color unpressed, pressed, current;
    private int offset, x, y, width, height;
    private String name;
    private final ActionListener listener;
    private final Font font;

    /**
     * Creates an EllipseButton with a set name/text and action listener.
     * @param name The name as well as the text on the button
     * @param listener The ActionListener for when the button is clicked on
     */
    public EllipseButton(String name, ActionListener listener) {
        this.name = name;
        Mouse mouse = new Mouse();
        font = new Font("Serif", Font.BOLD, 16);
        makeButton(new Color(0x999999), new Color(0x777777));
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        this.listener = listener;
    }

    /**
     * Creates an EllipseButton with a set name/text and action listener.
     * @param name The name as well as the text on the button
     * @param up the color when it is unpressed
     * @param p the color when it is pressed
     * @param listener The ActionListener for when the button is clicked on
     */
    public EllipseButton(String name, Color up, Color p, ActionListener listener) {
        this.name = name;
        Mouse mouse = new Mouse();
        font = new Font("Serif", Font.BOLD, 16);
        makeButton(up, p);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        this.listener = listener;
    }

    // makes the property of the button
    private void makeButton (Color up, Color p) {
        unpressed = up;
        pressed = p;
        current = unpressed;
        offset = 10;
        updateEllipse();
    }

    // updates the ellipse based on panel size
    private void updateEllipse() {
        x = getWidth() / 2;
        y = getHeight() / 2;
        width = this.getWidth() - 2 * offset;
        height = this.getHeight() - 2 * offset;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateEllipse();
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D bbg = (Graphics2D) image.getGraphics();
        bbg.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        bbg.setColor(this.getBackground());
        bbg.fillRect(0,0, getWidth(), getHeight());
        bbg.setColor(current);
        bbg.fillOval(offset, offset, width, height);
        bbg.setColor(Color.WHITE);
        bbg.setFont(font);
        drawingString(bbg, name);
        g.drawImage(image, 0, 0, this);
    }

    // draws the name of the button on the button in the center of the button.
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

    // creates a MouseAdapter class that detect mouse activity
    private class Mouse extends MouseAdapter {
        private boolean press = false;
        private boolean pressInside = false;

        // checks if the mouse is inside of the ellipse
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
                if (!press) {
                    current = pressed;
                    press = true;
                    pressInside = true;
                    paintComponent(getGraphics());
                }
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            if (!isInside(e.getX(), e.getY())) {
                if (press && pressInside) {
                    current = unpressed;
                    press = false;
                    paintComponent(getGraphics());
                }
            }
            else {
                if (!press && pressInside) {
                    current = pressed;
                    press = true;
                    paintComponent(getGraphics());
                }
            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            current = unpressed;
            pressInside = false;
            paintComponent(getGraphics());
            if (press && listener != null) {
                // activates the ActionListener
                ActionEvent g = new ActionEvent(e.getSource(), e.getID(), e.paramString());
                listener.actionPerformed(g);
                press = false;
            }
        }
    }

}
