package ninjaroids;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Body
{
    private double rotation = Math.toRadians(Math.random() * 360);
    private double rotationSpeed = Math.random() * 180 - 90;
    private double deltaX;
    private double deltaY;
    private int bodySpeed;
    private double bodyCourse;
    private Image bodyImage;
    URL starAddress;
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    AffineTransform identityTransform = new AffineTransform();
    AffineTransform bodyTransform;
    Rectangle2D.Double bodyRectangle;
    Area bodyArea;

    public Body()
    {
    }

    public Body(Image bodyImage, int bodyXarg, int bodyYarg, int bodyCourse, int bodySpeed)
    {
        setBodyCourse(bodyCourse);
        setBodySpeed(bodySpeed);
        setBodyImage(bodyImage);
        bodyRectangle = new Rectangle2D.Double(bodyXarg, bodyYarg, bodyImage.getWidth(null), bodyImage.getHeight(null));
        bodyArea = new Area(bodyRectangle);
    }
//not painting stars,only bounding box

    public Body(int bodyXarg, int bodyYarg, int bodyCourse, int bodySpeed)//used for stars only
    {
        starAddress = getClass().getResource("ninStar.png");
        try
        {
            bodyImage = ImageIO.read(starAddress);
        } catch (IOException ex)
        {
            System.out.println("hiccup, trying to read");
        }
        setBodyCourse(bodyCourse);
        setBodySpeed(bodySpeed);
        bodyRectangle = new Rectangle2D.Double(bodyXarg, bodyYarg, bodyImage.getWidth(null), bodyImage.getHeight(null));
        bodyArea = new Area(bodyRectangle);
    }

    public double getDeltaX()
    {
        return deltaX;
    }

    public double getDeltaY()
    {
        return deltaY;
    }

    public double getBodyCourse()
    {
        return bodyCourse;
    }

    public Image getBodyImage()
    {
        return bodyImage;
    }

    public int getBodySpeed()
    {
        return bodySpeed;
    }

    public double getBodyX()
    {
        return bodyRectangle.getX();
    }

    public double getBodyY()
    {
        return bodyRectangle.getY();
    }

    public void paintSelf(Graphics2D g2)
    {
        rotation += rotationSpeed;
        g2.setTransform(identityTransform);
        deltaX = bodySpeed * Math.sin(Math.toRadians(bodyCourse));
        deltaY = (-1 * bodySpeed * Math.cos(Math.toRadians(bodyCourse)));
        bodyRectangle.x += deltaX;
        bodyRectangle.y += deltaY;
        double bodyCenterX = bodyRectangle.getCenterX();
        double bodyCenterY = bodyRectangle.getCenterY();
        g2.translate(bodyCenterX, bodyCenterY);
        g2.rotate(Math.toRadians(rotation));
        g2.translate(-bodyCenterX, -bodyCenterY);
        bodyTransform = g2.getTransform();
        bodyArea.transform(bodyTransform);
        g2.setColor(Color.pink);
        g2.draw(bodyArea);
        g2.setColor(Color.yellow);
        g2.draw(bodyRectangle);
        g2.drawImage(bodyImage, (int) bodyRectangle.getX(), (int) bodyRectangle.getY(), null);
    }

    public void setDeltaX(double deltaX)
    {
        this.deltaX = deltaX;
    }

    public void setDeltaY(double deltaY)
    {
        this.deltaY = deltaY;
    }

    public void setBodyCourse(double bodyCourse)
    {
        this.bodyCourse = bodyCourse;
    }

    public void setBodyImage(Image bodyImage)
    {
        this.bodyImage = bodyImage;
    }

    public void setBodyList(ArrayList bodyList)
    {
    }

    public void setBodySpeed(int bodySpeed)
    {
        this.bodySpeed = bodySpeed;
    }
}
