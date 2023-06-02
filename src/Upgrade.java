import greenfoot.*;

public class Upgrade extends Actor {
    private final static GreenfootImage PLACEHOLDER = new GreenfootImage("UpgradePlaceholderImage.png");
    private final double RANGE;
    private final int DAMAGE;
    private final int COOLDOWN;
    private final GreenfootImage IMAGE;
    private final String NAME;
    private final int ANIMATION_LENGTH_CHANGE;
    private final int COST;

    /**
     * Creates an Upgrade based on the inputted values
     * @param r Modification to the Turret's range
     * @param d Modification to the Turret's damage
     * @param cd Modification to the Turret's cooldown
     * @param a Modification to the Turret's animation time
     * @param image Image of the Upgrade (if null then placeholder image is used)
     * @param n Name of the Upgrade
     * @param c Cost of the Upgrade
     */
    public Upgrade(double r, int d, int cd, int a, GreenfootImage image, String n, int c){
        RANGE = r;
        DAMAGE = d;
        COOLDOWN = cd;
        ANIMATION_LENGTH_CHANGE = a;
        NAME = n;
        IMAGE = (image == null)?PLACEHOLDER:image;
        COST = c;
    }
    public double getRange(){
        return RANGE;
    }

    public int getDamage() {
        return DAMAGE;
    }

    public int getCooldown() {
        return COOLDOWN;
    }
    public int getAnim(){
        return ANIMATION_LENGTH_CHANGE;
    }
    public GreenfootImage getImage() {
        return IMAGE;
    }

    public int getCost() {
        return COST;
    }

    public String getName() {
        return NAME;
    }
}