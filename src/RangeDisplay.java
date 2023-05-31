import greenfoot.*;

/**
 * A class to display the range of the selected Turret
 */
public class RangeDisplay extends ShopCloseRemoves {
    private final TurretGunBase TURRET;
    public RangeDisplay(TurretGunBase t){
        TURRET = t;
    }

    public void act() {
        GreenfootImage i = new GreenfootImage(TURRET.getRange()*2, TURRET.getRange()*2);
        i.setColor((TURRET.getBase().canPlace())?Color.WHITE: Color.RED);
        i.fillOval(0, 0, i.getWidth(), i.getHeight());
        i.setTransparency(120);
        setImage(i);
        setLocation(TURRET.getBase().getX(), TURRET.getBase().getY());
    }
}