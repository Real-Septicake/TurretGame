import greenfoot.*;

import java.awt.*;
import java.util.List;

/**
 * This class contains the basic requirements for every Turret gun
 * <p>
 * All Turret gun classes must extend this class
 */
public abstract class TurretGunBase extends Actor {
    /**
     * Enum for the targeting types that each Turret uses
     */
    private enum Targeting {
        FIRST,
        LAST,
        CLOSE,
        FAR,
        STRONG
    }

    Targeting mode = Targeting.FIRST;

    private double range;
    private int damage;
    private int cooldown;
    private int lastFireCheck = -1;
    protected int lastAnimCheck = -1;
    protected int animLength;
    private TurretBase base;
    private UpgradePath[] paths;
    private int value;
    private final int cost;

    /**
     * Creates a Turret gun based on the specified values
     * @param r The range of the Turret
     * @param d The damage the Turret does to Enemies
     * @param cool The Cooldown between attacks for this Turret
     * @param anim Length of the Turret's animation
     * @param c The cost of the Turret
     */
    public TurretGunBase(double r, int d, int cool, int anim, int c){
        range = r;
        damage = d;
        cooldown = cool;
        animLength = anim;
        cost = c;
        value = c;
    }

    @Override
    protected void addedToWorld(World world) {
        base = new TurretBase(getBaseImage());
        world.addObject(base, 0, 0);
        world.addObject(new RangeDisplay(this), 0, 0);
    }

    public void act(){
        if(base.buying && Greenfoot.isKeyDown("escape")){
            remove();
        }
        if(!base.buying) {
            aim();
            setLocation(base.getX(), base.getY());
            move(getOffset());
        }else if(Greenfoot.getMouseInfo() != null){
            setLocation(Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
            move(getOffset());
        }
        if(Greenfoot.mouseClicked(this) ){
            if(base.canPlace()){
                if (base.buying) {
                    base.buying = false;
                    getWorld().getObjects(ShopButton.class).get(0).openTurret();
                }else{
                    getWorld().getObjects(ShopButton.class).get(0).openUpgrade(this, paths);
                }
            }
        }
    }

    /**
     * Method to check the distance between the Turret and the specified Point
     * @param p Point to check the distance to
     * @return The distance to the specified Point
     */
    private double distanceTo(Point p){
        return Math.sqrt(Math.pow((getX()-p.x),2)+Math.pow((getY()-p.y),2));
    }

    protected void aim(){
        List<Enemy> e = getObjectsInRange((int) range, Enemy.class);
        Enemy target = null;
        for (Enemy t : e) {
            switch (mode) {
                case FIRST:
                    if (target == null || t.progress() >= target.progress()) target = t;
                    break;
                case LAST:
                    if (target == null || t.progress() <= target.progress()) target = t;
                    break;
                case FAR:
                    if (target == null || distanceTo(t.locate()) >= distanceTo(target.locate())) target = t;
                    break;
                case CLOSE:
                    if (target == null || distanceTo(t.locate()) <= distanceTo(target.locate())) target = t;
                    break;
                case STRONG:
                    if(target == null || t.maxHealth() >= target.maxHealth()) target = t;
            }
        }
        if(target != null){
            if(MyWorld.Timer - lastAnimCheck >= animLength){
                turnTowards(target.getX(), target.getY());
            }
            if(MyWorld.Timer - lastFireCheck >= cooldown){
                target.damage(damage);
                lastFireCheck = MyWorld.Timer;
                lastAnimCheck = MyWorld.Timer;
            }
        }
        animate(MyWorld.Timer - lastAnimCheck >= animLength);
    }

    public void alterDamage(int val){
        damage += val;
    }

    public void alterRange(double val){
        range += val;
    }
    public int getRange(){
        return (int)range;
    }
    public void alterCooldown(int val){
        cooldown += val;
    }
    public void alterAnimTime(int val){
        animLength += val;
    }

    /**
     * @return The distance the Turret needs to move for it to appear centered
     */
    public abstract int getOffset();

    /**
     * @return The image used for the Turret's base
     */
    public abstract GreenfootImage getBaseImage();

    /**
     * @return The base image of the Turret
     */
    public abstract GreenfootImage getTurretImage();

    /**
     * @return The display name of the Turret
     */
    public abstract String getName();
    public int getValue(){
        return (int)(value*0.9);
    }

    public int getCost() {
        return cost;
    }

    /**
     * Set the image of the Turret based on if the animation cooldown is over
     * @param cool If the animation cooldown is over
     */
    protected abstract void animate(boolean cool);

    public TurretBase getBase(){
        return base;
    }

    public String getMode(){
        return mode.name();
    }
    public void cycleMode(){
        switch(mode){
            case FIRST: mode = Targeting.LAST; break;
            case LAST: mode = Targeting.CLOSE; break;
            case CLOSE: mode = Targeting.FAR; break;
            case FAR: mode = Targeting.STRONG; break;
            case STRONG: mode = Targeting.FIRST; break;
        }
    }

    /**
     * Sets the upgrade paths of the Turret
     * @param upPaths Upgrade Paths to give the Turret
     *
     * @implNote Should be called in Turret type constructor
     */
    protected void setPaths(UpgradePath... upPaths){
        paths = upPaths;
    }

    /**
     * Applies the upgrade properties to the Turret
     * @param u Upgrade to apply
     */
    public void applyUpgrade(Upgrade u){
        alterDamage(u.getDamage());
        alterCooldown(u.getCooldown());
        alterRange(u.getRange());
        alterAnimTime(u.getAnim());
        value += u.getCost();
    }

    /**
     * Sells the Turret, returning the full cost if it's still being bought, or returning 90% of the total value if not
     */
    public void remove(){
        getWorld().getObjects(ShopButton.class).get(0).close();
        Cash.alterCash((base.buying)?getCost():getValue());
        getWorld().removeObject(base);
        getWorld().removeObject(this);
    }
}