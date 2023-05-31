import greenfoot.*;

/**
 * A superclass for onscreen counters
 */
public class Counter extends Actor {
    /**
     * Sets the Image of the Counter based on the inputs
     * @param prefix Prefix of the Counter
     * @param value Value of the Counter
     */
    protected void updateImage(String prefix, String value){
        GreenfootImage gi = new GreenfootImage(prefix+value, 35, Color.LIGHT_GRAY, null);
        setImage(gi);
        setLocation(gi.getWidth()/2+5, getY());
    }
}