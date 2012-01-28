package ninjaroids;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.*;

public class NinjaShip
{
    AffineTransform ninBodyTransform;
    private AffineTransform identity = new AffineTransform();
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int xShip = 20;
    private int yShip = 20;
    private int shipCourse = 0;
    private int shipSpeed = 0;
    private int acceleration = 1;
    private int turnSpeed = 15;
    private int deltaX;
    private int deltaY;
    private int headX = 0;
    private int headY = 0;
    private int faceX = headX + 13;
    private int faceY = headY + 25;
    private int eyeX = headX + 40;
    private int eyeY = headY + 42;
    private int eye2X = headX + 80;
    private int eye2Y = headY + 42;
    private Arc2D.Double head = new Arc2D.Double(headX, headY, 120, 100, -45, 270, Arc2D.OPEN);
    private Arc2D.Double hand1 = new Arc2D.Double(headX - 16, headY + 120, 28, 37, 120, 180, Arc2D.OPEN);
    private Arc2D.Double hand2 = new Arc2D.Double(headX + 120, headY + 123, 28, 37, -120, 180, Arc2D.OPEN);
    Ellipse2D.Double face = new Ellipse2D.Double(faceX, faceY, 100, 50);
    Ellipse2D.Double eye = new Ellipse2D.Double(eyeX, eyeY, 7, 14);
    Ellipse2D.Double eye2 = new Ellipse2D.Double(eye2X, eye2Y, 7, 14);
    Line2D.Double side1 = new Line2D.Double(headX + 25, headY + 120, headX + 25, headY + 200);
    Line2D.Double side2 = new Line2D.Double(headX + 100, headY + 120, headX + 100, headY + 200);
    Line2D.Double arm1 = new Line2D.Double(headX + 20, headY + 87, headX - 8, headY + 120);
    Line2D.Double bottomArm1 = new Line2D.Double(headX + 25, headY + 120, headX + 5, headY + 140);
    Line2D.Double arm2 = new Line2D.Double(headX + 102, headY + 87, headX + 142, headY + 125);
    Line2D.Double bottomArm2 = new Line2D.Double(headX + 102, headY + 120, headX + 122, headY + 140);
    Line2D.Double belly = new Line2D.Double(headX + 50, headY + 180, headX + 72, headY + 180);
    Line2D.Double innerLeg1 = new Line2D.Double(headX + 50, headY + 180, headX + 50, headY + 200);
    Line2D.Double innerLeg2 = new Line2D.Double(headX + 72, headY + 180, headX + 72, headY + 200);
    Line2D.Double palm1 = new Line2D.Double(headX + 5.5, headY + 140, headX + 5, headY + 155);
    Point2D.Double fingertip2 = new Point2D.Double(arm1.getX2(), arm1.getY2() + 30);
    Point2D.Double bottomArm2end = new Point2D.Double(arm1.getX2(), arm1.getY2());
    Line2D.Double palm2 = new Line2D.Double(bottomArm2.getX2(), headY + 140, bottomArm2.getX2() + 5, headY + 157);
    Rectangle2D.Double ninBodyRectangle = new Rectangle2D.Double(headX - 18, headY, xShip + 148, yShip + 200);
    private Area ninBodyArea = new Area(head);

    public void paintSelf(Graphics2D g2)
    {
        g2.setTransform(identity);
        deltaX = (int) (Math.sin(Math.toRadians(getShipCourse())) * getShipSpeed());
        deltaY = -(int) (Math.cos(Math.toRadians(shipCourse)) * shipSpeed);
        xShip += deltaX;
        yShip = yShip + deltaY;
        if (xShip > width)
        {
            xShip = 0;
        }
        if (xShip < 0)
        {
            xShip = width;
        }
        if (yShip > height)
        {
            yShip = 0;
        }
        if (yShip < 0)
        {
            yShip = height;
        }

        g2.setColor(Color.white);
        g2.translate(xShip, yShip);
        g2.rotate(Math.toRadians(shipCourse));
        ninBodyTransform = g2.getTransform();
        getNinBodyArea().createTransformedArea(ninBodyTransform);
        g2.setColor(new Color(0,255,255));//0,255,255
        g2.draw(getNinBodyArea());
        g2.setColor(Color.white);
        g2.draw(head);
        g2.setColor(Color.white);
        g2.draw(face);
        g2.setColor(Color.white);
        g2.fill(eye);
        g2.fill(eye2);
        g2.draw(side2);
        g2.draw(side1);
        g2.draw(arm1);
        g2.draw(bottomArm1);
        g2.draw(arm2);
        g2.draw(bottomArm2);
        g2.draw(belly);
        g2.draw(innerLeg1);
        g2.draw(innerLeg2);
        g2.draw(hand1);
        g2.draw(hand2);
        g2.draw(palm1);
        g2.draw(palm2);
        g2.setColor(Color.green);
        //g2.draw(ninBodyRectangle);
        ninBodyRectangle.x += deltaX;
        ninBodyRectangle.y += deltaY;
        double bodyCenterX = ninBodyRectangle.getCenterX();
        double bodyCenterY = ninBodyRectangle.getCenterY();
    }

    public int getxShip()
    {
        return xShip;
    }

    public void setxShip(int xShip)
    {
        this.xShip = xShip;
    }

    public int getyShip()
    {
        return yShip;
    }

    public void setyShip(int yShip)
    {
        this.yShip = yShip;
    }

    public int getShipCourse()
    {
        return shipCourse;
    }

    public void setShipCourse(int shipCourse)
    {
        this.shipCourse = shipCourse;
    }

    public int getShipSpeed()
    {
        return shipSpeed;
    }

    public void setShipSpeed(int shipSpeed)
    {
        this.shipSpeed = shipSpeed;
    }

    public void accelerate()
    {
        if (this.shipSpeed < 0)  // decelerate faster than accelerating
        {
            this.shipSpeed += acceleration * 2;
        } else
        {
            this.shipSpeed += acceleration;
        }
    }

    public void decelerate()
    {
        if (this.shipSpeed > 0)  // decelerate faster than accelerating
        {
            this.shipSpeed -= acceleration * 2;
        } else
        {
            this.shipSpeed -= acceleration;
        }
    }

    public void turnLeft()
    {
        // turn more slowly when fast
        setShipCourse(shipCourse - (turnSpeed / Math.max(Math.abs(shipSpeed), 1)));
    }

    public void turnRight()
    {
        // turn more slowly when fast
        setShipCourse(shipCourse + (turnSpeed / Math.max(Math.abs(shipSpeed), 1)));
    }

    public Star fire()
    {
        //TODO: fix star origin
//        Star star = new Star((int)palm1.getX1(),(int)palm1.getY1(),shipCourse,20);
        return new Star(xShip, yShip, shipCourse, 20);
    }

    /**
     * @return the ninBodyArea
     */
    public Area getNinBodyArea()
    {
        return ninBodyArea;
    }
}
