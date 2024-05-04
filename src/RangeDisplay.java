import greenfoot.*;

/**
 * A class to display the range of the selected Turret
 */
public class RangeDisplay extends Actor {
    private final TurretGunBase TURRET;
    public RangeDisplay(TurretGunBase t){
        TURRET = t;
    }

    @Override
    protected void addedToWorld(World world) {
        setLocation(TURRET.getBase().getX(), TURRET.getBase().getY());
        updateImage();
    }

    public void act() {
        updateImage();
        setLocation(TURRET.getBase().getX(), TURRET.getBase().getY());
    }

    private void updateImage(){
        GreenfootImage i = new GreenfootImage(TURRET.getRange()*2, TURRET.getRange()*2);
        i.setColor((TURRET.getBase().canPlace())?Color.WHITE: Color.RED);
        i.fillOval(0, 0, i.getWidth(), i.getHeight());
        i.setTransparency(120);
        setImage(i);
    }
}