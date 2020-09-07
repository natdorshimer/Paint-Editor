import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Natalie Dorshimer
 * Last Modified: April 8, 2020
 */

/**
 * ControlsPanel is a class that handles all of the controls for the program
 * It's interface includes the following:
 * clear and undo buttons to clear the painting panel
 * list to select from 3 shapes: line, oval, and rectangle
 * Check boxes to select for filled shapes, dashed shapes, and gradient coloring
 * Color buttons to choose the color of the shapes
 *      The first color selects solid shape, second determines second gradient color
 * Text fields for user to determine line width and dash length
 *
 * Its constructor needs ActionListeners to handle the implementation of clear and undo
 */
public class ControlsPanel extends JPanel {
    private JButton clear, undo; //for clearing the panel and removing previous shape
    private JComboBox<String> shapeList; //list for selecting which shape to draw
    private JCheckBox filled, dashed, useGradient; //check boxes for line style
    private JColorButton firstGradColor, secondGradColor; //first color is solid color, second color is for gradients
    private JTextField lineWidthField, dashLengthField;    //for line style
    private JLabel shape, lineWidthLabel, dashLengthLabel; //Information labels

    private static final String[] shapeNames = { "Line", "Oval", "Rectangle" };

    //Constructor takes a handle for clear and undo
    //Those buttons have an implementation dependent on how the painting panel works
    public ControlsPanel( ActionListener clearHandle, ActionListener undoHandle ) {
        //Declarations of control parts
        clear = new JButton("Clear");
        undo = new JButton("Undo");
        shape = new JLabel("Shape:");
        shapeList = new JComboBox<>(shapeNames);
        firstGradColor = new JColorButton("1st Color...");
        secondGradColor = new JColorButton("2nd Color...");
        lineWidthLabel = new JLabel("Line Width:");
        lineWidthField = new JTextField("3");
        lineWidthField.setColumns(2);
        dashLengthLabel = new JLabel("Dash Length:");
        dashLengthField = new JTextField("6");
        dashLengthField.setColumns(2);
        filled = new JCheckBox("Filled");
        dashed = new JCheckBox("Dashed");
        useGradient = new JCheckBox("Use Gradient");

        clear.addActionListener(clearHandle);
        undo.addActionListener(undoHandle);

        this.setLayout(new FlowLayout());
        this.add(clear);
        this.add(undo);
        this.add(shape);
        this.add(shapeList);
        this.add(filled);
        this.add(useGradient);
        this.add(firstGradColor);
        this.add(secondGradColor);
        this.add(lineWidthLabel);
        this.add(lineWidthField);
        this.add(dashLengthLabel);
        this.add(dashLengthField);
        this.add(dashed);

    }
    
    boolean isFilled() { return filled.isSelected(); }

    boolean isDashed() { return dashed.isSelected(); }

    boolean useGradient() { return useGradient.isSelected(); }

    Color firstColor() { return firstGradColor.getColor(); }

    Color secondColor() { return secondGradColor.getColor(); }

    String getShape() { return shapeList.getSelectedItem().toString(); }

    int getLineWidth() { return Integer.parseInt( lineWidthField.getText() ); }

    int getDashLength() { return Integer.parseInt( dashLengthField.getText() );}


    /**
     * This is just a JButton with a color attached
     * When clicked on, a dialog box shows up and the user can select a color
     * This color is stored in chosenColor to be easily retrieved in the future
     * If the user exits out without clicking on anything, then the color must not change
     * So only if the color returns from the dialog isn't null is it stored in chosenColor
     */
    private class JColorButton extends JButton {

        private Color chosenColor;

        public JColorButton( String name ) {
            super( name );
            chosenColor = Color.BLACK;
            addActionListener( new ActionListener() {
                public void actionPerformed( ActionEvent e )
                {
                    Color color = JColorChooser.showDialog(null,"Choose Color", chosenColor );
                    if(color != null)
                        chosenColor = color;
                }
            } );
        }

        public Color getColor() { return chosenColor; }

    } //JColorButton

} //ControlsPanel
