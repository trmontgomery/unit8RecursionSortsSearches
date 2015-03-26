import java.awt.geom.*;
import javax.swing.JPanel;
import java.awt.*;

public class LogSpiralPanel extends JPanel
{
   private static final double GOLDEN_MEAN = (1 + Math.sqrt(5)) / 2;

   public void paintComponent(Graphics g)
   {
      /* Your code goes here.
         1. Compute the dimensions of the goldenRectangle (you can use getHeight() 
            to obtain the side size)
         2. Draw the golden rectangle
         3. Call the recursive helper method "recursiveDraw" which will draw 
            the spiral.
      */
     
      double height = getHeight()-100;
      double width = height*GOLDEN_MEAN;
      
      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(Color.BLACK);
      
      Rectangle2D.Double goldie = new Rectangle2D.Double(50, 50, width, height);
      g2.draw(goldie);
      
      recursiveDraw(g2, 50 + (height*(GOLDEN_MEAN-1)), 50 , height, 0);
      
   }
   
     private void recursiveDraw(Graphics2D g2, double x, double y, double side, int angle)
   {
      // Recursion ending condition: when the side is very small
      if (side < 5)
      {
          return;
        }

      // Draw the current square and arc
      Rectangle2D.Double currentSquare = new Rectangle2D.Double(x,y,side,side);
      g2.draw(currentSquare);
      
      drawArc(g2,x,y,side,angle);

      /* Continue drawing the spiral recursively: calculate the new side 
         size, calculate the new (x, y) coordinates and the new angle. Then, 
         call "recursiveDraw" again. */
      
      
      double newSide = side * (GOLDEN_MEAN-1);
      double newX, newY;
      if (angle == 0)
      {
          newX = x - newSide;
          newY = y;
        }
      else if (angle == 90)
      {
          newY = y + side;
          newX = x;
        }
      else if (angle == 180)
      {
          newX = x + side;
          newY = y + (GOLDEN_MEAN-1)*newSide;
        }
      else 
      {
          newX = x + (GOLDEN_MEAN-1)*newSide;
          newY = y - newSide;
        }

      int newAngle = (angle + 90) % 360;
      
      recursiveDraw(g2, newX, newY, newSide, newAngle);
   }
   
   /**
      Draws the arc of the current iteration.
      @param x The x-coordinate of the square's upper-left corner  
      @param y The y-coordinate of the square's upper-left corner
      @param side The size of the side of the square (or the arc's radius)
      @param angle The angle (0, 90, 180 or 270) where the top of the 
      current golden rectangle is located. For the outermost golden 
      rectangle, the angle is 90.
   */
   private void drawArc(Graphics g, double x, double y, double side, int angle)
   {
      double auxX = x;
      double auxY = y;
      if (angle == 0 || angle == 270 )
      {
         auxX = x - side;
      }
      if (angle == 270 || angle == 180)
      {
         auxY = y - side;
      }
      g.drawArc((int) auxX, (int) auxY, (int) side * 2, (int) side * 2, angle, 90);
   }   
   private double calculateNewX(double x, double angle, double side, double newSide)
   {
      if (angle == 0)
         x = x + side - newSide;
      else if (angle == 90)
         x = x + side;
      else if (angle == 270)
         x = x - newSide;
      return x;
   }

   private double calculateNewY(double y, double angle, double side, double newSide)
   {
      if (angle == 0)
         y = y + side;
      else if (angle == 180)
         y = y - newSide;
      else if (angle == 270)
         y = y + side - newSide;
      return y;
   }
}