package ninjaroids;

import java.awt.Image;
import java.awt.geom.Area;

public class Food extends Body
{
    Area foodArea = bodyArea;

    public Food(Image foodImage, int foodXarg, int foodYarg, int foodCourse, int foodSpeed)
    {
        super(foodImage, foodXarg, foodYarg, foodCourse, foodSpeed);
    }
}
