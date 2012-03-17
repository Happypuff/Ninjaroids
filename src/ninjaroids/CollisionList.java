package ninjaroids;

import java.awt.Image;
import java.util.ArrayList;

public class CollisionList
{
static ArrayList<Body> collisionList = new ArrayList<Body>();
    public CollisionList()
    {
    }

    public void addToList(Body pBody)
    {
        collisionList.add(pBody);
    }
    
    public static ArrayList<Body> returning()
    {
        return collisionList;
    }
    
}




