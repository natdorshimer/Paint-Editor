import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Author: Natalie Dorshimer
 * Last Modified: April 8, 2020
 */

/* About
 * Line implementation of a shape
 *
 * Public Interface:
 * draw( Graphics ) : called by paintComponent to draw it to the content pane
 */
public class PaintLine extends PaintingShape
{
    public PaintLine(Point start, Point end, ControlsPanel data) {
        super(start, end, data);
    }

    public void draw(Graphics g) {
        super.draw(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.draw(new Line2D.Double(getX1(), getY1(), getX2(), getY2()));
    }

} //PaintLine

