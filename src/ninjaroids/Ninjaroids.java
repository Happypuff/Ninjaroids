package ninjaroids;

/*******************************************************
 *                                                     *
 *            ~Copyright 2012 Heidi Erwin~             *
 *                     ~Version 1.9~                   *
 *                 ~March 10th, 2012~                *
 *                                                     *
 *******************************************************/
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.Timer;
import javax.imageio.ImageIO;

public class Ninjaroids extends JComponent implements KeyListener, ActionListener
{
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    JFrame playingField;
    
    Timer foodTicker;
    NinjaShip mainShip = new NinjaShip();
    Food food;
    Star ninjaStar;
    Timer paintTicker;
    URL duckAddress;
    Image duckPic;
    URL sushiAddress;
    Image sushiPic;
    URL grapefruitAddress;
    Image grapefruitPic;
    URL bananaAddress;
    Image bananaPic;
    URL cakeSliceAddress;
    Image cakeSlicePic;
    URL chickenAddress;
    Image chickenPic;
    URL doughnutAddress;
    Image doughnutPic;
    URL friesAddress;
    Image friesPic;
    URL radishAddress;
    Image radishPic;
    URL lettuceAddress;
    Image lettucePic;
    URL pastaAddress;
    Image pastaPic;
    URL pieAddress;
    Image piePic;
    ArrayList<Image> uncookedFoodList;
    ArrayList<Food> foodList;
    ArrayList<Star> starList;
    int foodCourse;
    String version = "1.9";

    public static void main(String[] args)
    {
        new Ninjaroids().getGoing();
    }
    
    private void getGoing()
    {
        starList = new ArrayList<Star>();
        foodList = new ArrayList<Food>();
        uncookedFoodList = new ArrayList<Image>();
        pieAddress = getClass().getResource("pie.gif");
        pastaAddress = getClass().getResource("pasta.png");
        chickenAddress = getClass().getResource("chicken.png");
        doughnutAddress = getClass().getResource("doughnut.png");
        friesAddress = getClass().getResource("fries.png");
        cakeSliceAddress = getClass().getResource("cakeSlice.png");
        bananaAddress = getClass().getResource("banana.png");
        duckAddress = getClass().getResource("duck.png");
        grapefruitAddress = getClass().getResource("grapefruit.png");
        sushiAddress = getClass().getResource("sushi.gif");
        radishAddress = getClass().getResource("radish.gif");
        lettuceAddress = getClass().getResource("lettuce.png");
        try
        {
            piePic = ImageIO.read(pieAddress);
            pastaPic = ImageIO.read(pastaAddress);
            radishPic = ImageIO.read(radishAddress);
            doughnutPic = ImageIO.read(doughnutAddress);
            chickenPic = ImageIO.read(chickenAddress);
            friesPic = ImageIO.read(friesAddress);
            cakeSlicePic = ImageIO.read(cakeSliceAddress);
            bananaPic = ImageIO.read(bananaAddress);
            duckPic = ImageIO.read(duckAddress);
            sushiPic = ImageIO.read(sushiAddress);
            grapefruitPic = ImageIO.read(grapefruitAddress);
            lettucePic = ImageIO.read(lettuceAddress);
            food = new Food(grapefruitPic, 500, 500, 45, 2);
        } catch (IOException ex)
        {
            System.out.println("hiccup");
        }
        uncookedFoodList.add(piePic);
        uncookedFoodList.add(pastaPic);
        uncookedFoodList.add(lettucePic);
        uncookedFoodList.add(radishPic);
        uncookedFoodList.add(doughnutPic);
        uncookedFoodList.add(friesPic);
        uncookedFoodList.add(chickenPic);
        uncookedFoodList.add(cakeSlicePic);
        uncookedFoodList.add(duckPic);
        uncookedFoodList.add(bananaPic);
        uncookedFoodList.add(sushiPic);
        uncookedFoodList.add(grapefruitPic);
        playingField = new JFrame("Ninjaroids Version " + version);
        playingField.setBackground(Color.black);
        // set background to black in Windows
        Container con = playingField.getContentPane();
        con.setBackground(Color.black);
        playingField.setSize(width, height);
        playingField.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playingField.add(this);
        playingField.setVisible(true);
        playingField.addKeyListener(this);
        paintTicker = new Timer(20, this);
        paintTicker.start();
        foodTicker = new Timer(2000, this);
        foodTicker.start();

    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        mainShip.paintSelf(g2);
        for (Food f : foodList)
        {
            f.paintSelf(g2);
        }
        if (starList != null)
        {
            for (Star s : starList)
            {
                s.paintSelf(g2);
            }
        }
    }

    public boolean bump(Area a1, Area a2)
    {
        Area tempArea = (Area)a1.clone();
        a1.intersect(a2);
        if (a1.isEmpty())
        {
            return false;
        }
        
        else
        {
                return true;
        }
    }
    @Override
    public void keyTyped(KeyEvent ke)
    {
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        if (ke.getKeyCode() == 40)//down
        {
            mainShip.decelerate();
        }
        if (ke.getKeyCode() == 38)//up
        {
            mainShip.accelerate();
        }
        if (ke.getKeyCode() == 39)//right
        {
            mainShip.turnRight();
        }
        if (ke.getKeyCode() == 37)//left
        {
            mainShip.turnLeft();
        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE)//fire
        {
            starList.add(mainShip.fire());
        }
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
//        if (bump())
//        {
//            System.out.println("bump");
//        }
        repaint();
        if (ae.getSource().equals(foodTicker))
        {
            food.setBodyList(foodList);
            switch ((int) (Math.random() * 4))
            {
                case 0://north
                    foodCourse = (int) ((Math.random() * 180) + 90);
                    foodList.add(new Food(uncookedFoodList.get((int) (Math.random() * uncookedFoodList.size())), (int) (Math.random() * width), 0, foodCourse, 5));//image, x, y, course, speed
                    break;
                case 1://south
                    foodCourse = (int) ((Math.random() * 180) + 270);
                    foodList.add(new Food(uncookedFoodList.get((int) (Math.random() * uncookedFoodList.size())), (int) (Math.random() * width), height - 200, foodCourse, 5));
                    break;
                case 2://east
                    foodCourse = (int) ((Math.random() * 180) + 0);
                    foodList.add(new Food(uncookedFoodList.get((int) (Math.random() * uncookedFoodList.size())), width - 100, (int) (Math.random() * height), foodCourse, 5));
                    break;
                case 3://west
                    foodCourse = (int) ((Math.random() * 90) + 270);
                    foodList.add(new Food(uncookedFoodList.get((int) (Math.random() * uncookedFoodList.size())), 0, (int) (Math.random() * height), foodCourse, 5));
                    break;
                default:
                    System.out.println("hiccup");

            }
        }
    }
}
