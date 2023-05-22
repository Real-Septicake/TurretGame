import greenfoot.*;

public class Wave extends Counter {
    public static final int START_X_POS = 56;
    public Wave(){
        setImage(new GreenfootImage("Wave: " + 1, 35, Color.LIGHT_GRAY, null));
    }

    public void act() {
        updateImage("Wave: ", String.valueOf(Spawner.getWave()+1));
    }
}
