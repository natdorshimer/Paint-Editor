import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Author: Natalie Dorshimer
 * Last Modified: April 8, 2020
 */


/*
 * Public Interface:
 * draw( Graphics ): called by paintComponent to draw the shape in its content pane
 */
public class PaintOval extends PaintingShape {
    public PaintOval(Point start, Point end, ControlsPanel data) {
        super(start, end, data);
    }

    public void draw(Graphics g) {
        super.draw(g);
        Graphics2D g2d = (Graphics2D) g;

        if(isFilled())
            g2d.fill(new Ellipse2D.Double( Math.min( getX1(), getX2()),
                    Math.min(getY1(), getY2() ), getWidth(), getHeight()));
        else
            g2d.draw( new Ellipse2D.Double ( 
                        Math.min( getX1(), getX2()),
                        Math.min(getY1(), getY2()), 
                        getWidth(), 
                        getHeight()
                        )
                    );
    }

} //PaintOval

