import greenfoot.*;

import java.text.DecimalFormat;

public class Cash extends Counter {
    private static int cash;
    private static final String PREFIX = "Cash: $";
    public static final int START_X_POS = 77;
    private static final DecimalFormat FORMATTER = new DecimalFormat("###,###");
    public Cash(int c){
        GreenfootImage gi = new GreenfootImage(PREFIX+c, 35, Color.LIGHT_GRAY, null);
        setImage(gi);
        cash = c;
    }
    public void act(){
        updateImage(PREFIX, FORMATTER.format(cash));
    }
    public static int getCash(){
        return cash;
    }
    public static void alterCash(int v){
        cash += v;
    }
}
