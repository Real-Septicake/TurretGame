import greenfoot.*;

public class Upgrade extends Actor {
    private final double RANGE;
    private final int DAMAGE;
    private final int COOLDOWN;
    private final GreenfootImage IMAGE;
    private final String NAME;
    private final int ANIMATION_LENGTH_CHANGE;
    public Upgrade(double r, int d, int cd, int a, GreenfootImage image, String n){
        RANGE = r;
        DAMAGE = d;
        COOLDOWN = cd;
        ANIMATION_LENGTH_CHANGE = a;
        NAME = n;
        IMAGE = image;
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

    public String getName() {
        return NAME;
    }
}