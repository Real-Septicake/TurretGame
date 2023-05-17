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