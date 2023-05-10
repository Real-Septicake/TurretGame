import greenfoot.*;

public class Cash extends Counter {
    private static int cash;
    private static final String PREFIX = "Cash: $";
    public static final int START_X_POS = 77;
    public Cash(int c){
        GreenfootImage gi = new GreenfootImage(PREFIX+c, 35, Color.LIGHT_GRAY, null);
        setImage(gi);
        cash = c;
    }
    public void act(){
        updateImage(PREFIX, String.valueOf(cash));
    }
    public static int getCash(){
        return cash;
    }
    public static void alterCash(int v){
        cash += v;
    }
}
