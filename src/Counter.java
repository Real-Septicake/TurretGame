import greenfoot.*;

public class Counter extends Actor {
    protected void updateImage(String prefix, String value){
        GreenfootImage gi = new GreenfootImage(prefix+value, 35, Color.LIGHT_GRAY, null);
        setImage(gi);
        setLocation(gi.getWidth()/2+5, getY());
    }
}