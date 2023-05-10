import greenfoot.*;

public class WaveStart extends Actor {
    private static final GreenfootImage start = new GreenfootImage("WaveStart.png");
    private static final GreenfootImage going = new GreenfootImage("WaveGoing.png");
    private static boolean isGoing = false;
    public WaveStart(){
        isGoing = false;
        setImage(start);
    }
    public void act() {
        if(!isGoing && Greenfoot.mouseClicked(this)) isGoing = true;
        setImage((isGoing)?going:start);
    }

    public static void waveDone(){
        isGoing = false;
    }

    public static boolean isGoing(){
        return isGoing;
    }


}