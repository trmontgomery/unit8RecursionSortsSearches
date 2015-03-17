import java.awt.event.*;
import javax.swing.*;

public class Viewer
{
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 400;
    
    JFrame frame;
    
    public Viewer()
    {
        JFrame frame = new JFrame();
        
        frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        LogSpiralPanel panel = new LogSpiralPanel();
        
        frame.add(panel);
        frame.setVisible(true);
    }
    
    public static void main (String[] args)
    {
        Viewer view = new Viewer();
    }
}
