import greenfoot.*;

public class WaveStart extends Actor {
    private static final GreenfootImage start = new GreenfootImage("WaveStart.png");
    private static final GreenfootImage going = new GreenfootImage("WaveGoing.png");
    private static boolean isGoing = false;
    public WaveStart(){
        setImage(start);
    }
    public void act() {
        setImage((isGoing)?going:start);
    }

    public static void waveDone(){
        isGoing = false;
    }

    public static boolean isGoing(){
        return isGoing;
    }


}