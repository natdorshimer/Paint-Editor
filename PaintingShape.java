import java.awt.*;

/**
 * Author: Natalie Dorshimer
 * Last Modified: April 8, 2020
 */

/**
 * PaintingShape is an abstraction of a shape that is to have the following properties:
 * Has a solid color
 * Specified line width and dash length
 * May or may not use dashes
 * May or may not be filled with a color / gradient
 * May or may not have a gradient specified by two colors
 * Starting point and an end point from where the mouse began and where it ended
 *
 * The shape must also be able to be painted, but implementation is varied depending on type of shape
 * But honestly, the code is so similar between all of them that they might not even deserve their own class
 *
 * Public Interface:
 * PaintingShape( Point,Point,DrawingData ) : Data listed in About is filled with the given information
 * getShape( Point,Point,DrawingData ) : static method that returns a specific type of Shape
 *      -Eliminates necessity to work with the individual shapes in the grand scheme of things
 * draw( Graphics ) : abstract method that will paint the shape onto its content pane
 */
public class PaintingShape {
    private int startX, startY, endX, endY;
    private int lineWidth, dashLength;
    private boolean filled, dashed, gradient;
    private Color firstColor, secondColor;

    public PaintingShape( Point start, Point end, ControlsPanel data ) {
        this.startX = (int)start.getX();
        this.startY = (int)start.getY();
        this.endX = (int)end.getX();
        this.endY = (int)end.getY();
        this.lineWidth = data.getLineWidth();
        this.dashLength = data.getDashLength();
        this.filled = data.isFilled();
        this.dashed = data.isDashed();
        this.firstColor = data.firstColor();
        this.secondColor = data.secondColor();
        this.gradient = data.useGradient();
    }

    public static PaintingShape getShape(Point start, Point end, ControlsPanel params) {
        PaintingShape returnObj = null;
        switch(params.getShape()) {
            case "Rectangle":
                returnObj = new PaintRect( start, end, params );
                break;
            case "Oval":
                returnObj = new PaintOval( start, end, params );
                break;
            case "Line":
                returnObj = new PaintLine( start, end, params );
                break;
        }

        return returnObj;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if(gradient)
            g2d.setPaint(new GradientPaint(getX1(), getY1(), firstColor, getX2(), getY2(), secondColor));
        else
            g2d.setColor(firstColor);

        if(dashed)
            g2d.setStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10.0f,
                    new float[]{dashLength, dashLength}, 0.0f));
        else
            g2d.setStroke(new BasicStroke(lineWidth));
    }


    //Getters
    public int getX1() { return startX; }

    public int getX2() { return endX; }

    public int getY1() { return startY; }

    public int getY2() { return endY; }

    public int getWidth() { return Math.abs( getX1() - getX2() ); }

    public int getHeight() { return Math.abs( getY1() - getY2() ); }

    public boolean isFilled() { return filled; }

} //PaintingShape