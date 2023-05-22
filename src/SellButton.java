import greenfoot.*;

import java.text.DecimalFormat;

public class SellButton extends ShopCloseRemoves {
    private final TurretGunBase TURRET;
    private static final DecimalFormat FORMATTER = new DecimalFormat("###,###");
    public SellButton(TurretGunBase t){
        TURRET = t;
    }

    public void act() {
        GreenfootImage button = new GreenfootImage("Sell", 30, Color.BLACK, Color.RED);
        GreenfootImage text = new GreenfootImage("$"+FORMATTER.format(TURRET.getValue()), 25, Color.DARK_GRAY,null);
        GreenfootImage gi = new GreenfootImage(button.getWidth()+text.getWidth()+10, Math.max(button.getHeight(), text.getHeight()));
        gi.drawImage(button, 0, 0);
        gi.drawImage(text, button.getWidth()+10, gi.getHeight()/2 - text.getHeight()/2);
        setImage(gi);
        if(Greenfoot.mouseClicked(this)){
            TURRET.remove();
        }
    }
}
