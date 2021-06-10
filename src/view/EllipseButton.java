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

    private Color unpressed, pressed, disabled, current;
    private int offset, x, y, width, height;
    private String name;
    private final ActionListener listener;
    private final Font font;
    private double angle;
    private boolean enabled;

    /**
     * Creates an EllipseButton with a set name/text and action listener.
     * @param name The name as well as the text on the button
     * @param listener The ActionListener for when the button is clicked on
     */
    public EllipseButton(String name, ActionListener listener) {
        this.name = name;
        Mouse mouse = new Mouse();
        font = new Font("Serif", Font.BOLD, 16);
        makeButton(new Color(0x999999), new Color(0x777777), new Color(0xaaaaaa));
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        this.listener = listener;
        angle = 0;
        enabled = true;
    }

    /**
     * Creates an EllipseButton with a set name/text and action listener.
     * @param name The name as well as the text on the button
     * @param up the color when it is unpressed
     * @param p the color when it is pressed
     * @param listener The ActionListener for when the button is clicked on
     */
    public EllipseButton(String name, Color up, Color p, Color d, ActionListener listener) {
        this.name = name;
        Mouse mouse = new Mouse();
        font = new Font("Serif", Font.BOLD, 16);
        makeButton(up, p, d);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        this.listener = listener;
    }

    // makes the property of the button
    private void makeButton (Color up, Color p, Color d) {
        unpressed = up;
        pressed = p;
        disabled = d;
        current = unpressed;
        offset = 10;
        updateEllipse();
    }

    public void enableButton() {
        enabled = true;
        current = unpressed;
        repaint();
    }

    public void disableButton() {
        enabled = false;
        current = disabled;
        repaint();
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
        bbg.rotate(-angle, x, y);
        bbg.fillOval(offset, offset, width, height);
        bbg.rotate(angle, x, y);
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
            int x = EllipseButton.this.x - mouseX;
            int y = EllipseButton.this.y - mouseY;
            int xX = x * x;
            int yY = y * y;
            double a = width / 2.0;
            double b = height / 2.0;
            double aA = a * a;
            double bB = b * b;
            double coef1 = aA * yY + bB * xX;
            double coef2 = (aA - bB) * x * y;
            double coef3 = (aA * xX + bB * yY);
            double cosCos = Math.cos(angle) * Math.cos(angle);
            double sinSin = Math.sin(angle) * Math.sin(angle);
            double dist = coef1 * cosCos + coef2 * Math.sin(2*angle) + coef3 * sinSin;
            double dist2 = a * a * b * b;
            return dist <= dist2;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            if (!enabled) return;
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
            if (!enabled) return;
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
            if (!enabled) return;
            current = unpressed;
            pressInside = false;
            paintComponent(getGraphics());
            if (press && listener != null) {
                // activates the ActionListener
                ActionEvent g = new ActionEvent(e.getSource(), e.getID(), e.paramString());
                listener.actionPerformed(g);
            }
            press = false;
        }
    }

}
