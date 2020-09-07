import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Author: Natalie Dorshimer
 * Last Modified: April 8, 2020
 */

/**
 * This frame provides the user interface for interacting with the paint program
 * It consists of a control panel, a painting panel, and a mouse location label
 * The mouse label is for determining position in the painting panel
 */
public class PaintingFrame extends JFrame {

    private ControlsPanel controls;   //Provides the controls for specifying properties of shapes
    private PaintingPanel colorPanel; //The painting panel the user can draw on
    private JLabel mouseLocation;     //Information labels

    public PaintingFrame() {
        //Constructing the components
        controls = new ControlsPanel(e -> colorPanel.clearPanel(), e -> colorPanel.undo());
        colorPanel = new PaintingPanel(controls);
        mouseLocation = new JLabel();

        //Handles for components
        colorPanel.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                mouseLocation.setText(String.format("(%d,%d)", e.getX(), e.getY()));
            }
        });

        //Building the frame. GridBagLayout is the best option for this.
        this.setLayout(new GridBagLayout());
        int BOTH = GridBagConstraints.BOTH;
        int CENTER = GridBagConstraints.CENTER;
        int LEFT = GridBagConstraints.FIRST_LINE_START;
        gridBagAdd(controls, 0, 0, 0, 2, BOTH, CENTER);
        gridBagAdd(colorPanel, 0, 1, 20, 20, BOTH, CENTER);
        gridBagAdd(mouseLocation, 0, 2, 0, 0, 0, LEFT);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 400);


    }
    //For simplifying adding to a GridBagLayout
    private void gridBagAdd(Component component, int x, int y, int weightx, int weighty, int fill, int anchor) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.weightx = weightx;
        c.weighty = weighty;
        c.fill = fill;
        c.anchor = anchor;
        this.add( component, c );
    }

} 