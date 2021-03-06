
//********************************************************************
//  KochPanel.java       Author: Lewis/Loftus/Cocking
//
//  Represents a drawing surface on which to paint a Koch Snowflake.
//********************************************************************

import java.awt.*;
import javax.swing.JPanel;

public class FractalTreePanel extends JPanel
{
    private final int PANEL_WIDTH = 400;
    private final int PANEL_HEIGHT = 400;

    private int startAngle = 90;
    private int current; //current order

    //-----------------------------------------------------------------
    //  Sets the initial fractal order to the value specified.
    //-----------------------------------------------------------------
    public FractalTreePanel (int currentOrder)
    {
        current = currentOrder;
        setBackground (Color.black);
        setPreferredSize (new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    }

    //-----------------------------------------------------------------
    //  Draws the fractal recursively. Base case is an order of 1 for
    //  which a simple straight line is drawn. Otherwise three
    //  intermediate points are computed, and each line segment is
    //  drawn as a fractal.
    //-----------------------------------------------------------------
    public void drawFractalTree (int order, int x1, int y1, int x5, int y5,
    Graphics page, double angle)
    {
        int deltaX, deltaY, x2, y2, x3, y3, x4, y4;

        page.drawLine (x1, y1, x5, y5);
      
        double prevLength =  Math.sqrt(Math.pow((x1-x5),2) + Math.pow((y1-y5),2));
        double newLength = prevLength - 7;
        if (newLength < 1)
        {
            return;
        }
        double leftAngle = angle - 10;
        double rightAngle = angle + 20;
        int xRight = x5 + (int)(newLength* Math.cos(Math.toRadians(rightAngle)));
        int yRight =y5 - (int)(newLength* Math.sin(Math.toRadians(rightAngle)));
        int xLeft = x5 +(int)(newLength* Math.cos(Math.toRadians(leftAngle)));
        int yLeft = y5 -(int)(newLength* Math.sin(Math.toRadians(leftAngle)));
        drawFractalTree (order-1, x5, y5, xLeft, yLeft, page, leftAngle);
        drawFractalTree (order-1, x5, y5, xRight, yRight, page, rightAngle);

    }

    //-----------------------------------------------------------------
    //  Performs the initial calls to the drawFractal method.
    //-----------------------------------------------------------------
    public void paintComponent (Graphics page)
    {
        super.paintComponent (page);

        page.setColor (Color.green);

        drawFractalTree(current, 200, 400, 200, 320, page, startAngle);
    }

    //-----------------------------------------------------------------
    //  Sets the fractal order to the value specified.
    //-----------------------------------------------------------------
    public void setOrder (int order)
    {
        current = order;
    }

    //-----------------------------------------------------------------
    //  Returns the current order.
    //-----------------------------------------------------------------
    public int getOrder ()
    {
        return current;
    }
}