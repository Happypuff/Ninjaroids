package ninjaroids;

import java.awt.Color;
import java.awt.Graphics2D;

public class Star extends Body
{
    public Star(int starXarg, int starYarg, int starCourse, int starSpeed)
    {
        super(null, starXarg, starYarg, starCourse, starSpeed);
    }

    /*
    @Override
    public void paintSelf(Graphics2D g2)
    {
        g2.setColor(Color.green);
        g2.drawRect(0, 0, 50, 50);
        g2.drawImage(null, bodyTransform, null);
        g2.setTransform(bodyTransform);
    }
    * 
    */
}
