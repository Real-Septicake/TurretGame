import greenfoot.*;

public class Lives extends Counter {
    private static int lives;
    private static final String PREFIX = "Lives: ";
    public static final int START_X_POS = 64;
    public Lives(int l){
        setImage(new GreenfootImage(PREFIX+l, 35, Color.LIGHT_GRAY, null));
        lives = l;
    }
    public void act(){
        updateImage(PREFIX, String.valueOf(Math.max(lives, 0)));
    }
    public static void alterLives(int val){
        lives += val;
        lifeCheck();
    }
    private static void lifeCheck(){
        if(lives <= 0){
            System.out.println("Game Over!");
            Greenfoot.stop();
        }
    }
}