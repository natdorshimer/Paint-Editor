import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

/**
 * Author: Natalie Dorshimer
 * Last Modified: April 8, 2020
 */

/**
 * PaintingPanel is a panel where the user can draw shapes with various properties
 * The properties of these shapes are determined by the data stored in DrawingData
 * The user can draw a shape by dragging their mouse across the panel
 *     The shape is finalized when the user releases the mouse
 * The shape the user is drawing will appear while he is drawing it
 * The panel is able to clear itself of all shapes drawn
 * The panel is also able to undo each previous shape drawn
 *
 * Public Interface:
 * PaintingPanel( DrawingData ) : constructs a painting panel with DrawingData used to create each shape
 * clear() : clears the panel of all shapes
 * undo() : clears the panel of the previous shape drawn
 * paintComponent( Graphics ): paints all previous shapes and the current shape. Called by repaint()
 */
public class PaintingPanel extends JPanel
{
    private LinkedList< PaintingShape > shapes;
    private PaintingShape               currObject;
    private Point                       start;
    private ControlsPanel               drawingData;

    public PaintingPanel( ControlsPanel drawingData )
    {
        shapes = new LinkedList<>();
        this.drawingData = drawingData;
        this.addMouseMotionListener( new MouseHandler() );
        this.addMouseListener( new MouseHandler() );
        this.setBackground( Color.LIGHT_GRAY );
    }

    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );

        if ( shapes != null )
            for ( PaintingShape shape : shapes )
                shape.draw( g );
        if ( currObject != null )
            currObject.draw( g );
    }

    public void clearPanel()
    {
        shapes.clear();
        repaint();
    }

    public void undo()
    {
        if( shapes != null )
        {
            shapes.removeLast();
            repaint();
        }
    }

    /**
     * Handler for the painting panel
     * We want to the current object to be the shape the user is currently creating by dragging the mouse
     * We want to store the object for later repainting once the user releases the mouse
     */
    private class MouseHandler extends MouseAdapter
    {
        public void mouseDragged( MouseEvent e )
        {
            Point end = e.getPoint();
            ControlsPanel data = PaintingPanel.this.drawingData;
            currObject = PaintingShape.getShape( start, end, data );
            repaint();
        }

        public void mousePressed( MouseEvent e )
        {
            start = e.getPoint();
        }

        public void mouseReleased( MouseEvent e )
        {
            if( currObject != null )      //we only want to add an object if it actually exits
                shapes.add( currObject ); //it may not exist if the user clicked but didn't drag
            currObject = null;            //current object now longer needed
        }
    } //MouseHandler

} //PaintingPanel


/**
 * Implementation
 *
 * //Basics:
 * When the user clicks down, the starting position is recorded
 * When the user drags the mouse, it records the current position and creates the object they are drawing
 * This then calls the repaint method to draw all previous objects and the one he is currently drawing
 * When the mouse is released, the current object is stored in the linked list
 *     -The current object is then set to null, as there is no longer a current object
 *
 * //Storage:
 * Each shape previously drawn is stored in a Linked List
 *     -This is so that they can be easily redrawn with each call of repaint()
 *     -LinkedList has easy access to the last object added
 *         -Good for removing last object made
 *     -Easy to clear by using LinkedList.clear() or setting the list equal to null
 * The shape that the user is currently drawing is stored in DrawObject
 *     -The shape isn't finalized, so it shouldn't be stored
 * Once the user is done drawing the object, it's added to the list and currObject is set to null
 *
 * //Painting:
 * Painting the components is done by iterating through the linked list and drawing each object
 * The current object is then drawn after each previous object has been drawn
 */
