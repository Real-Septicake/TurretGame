import greenfoot.*;

import java.awt.*;
import java.util.List;

public abstract class TurretGunBase extends Actor {
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
    private TurretBase BASE;
    private boolean baseMade = false;
    private UpgradePath[] paths;
    private int value;
    private final int cost;

    public TurretGunBase(double r, int d, int cool, int anim, int c){
        range = r;
        damage = d;
        cooldown = cool;
        animLength = anim;
        cost = c;
        value = c;
    }

    public void act(){
        if(!baseMade){
            BASE = new TurretBase(getBaseImage());
            getWorld().addObject(BASE, 0, 0);
            baseMade = true;
            getWorld().addObject(new RangeDisplay(this), 0, 0);
        }
        if(BASE.buying && Greenfoot.isKeyDown("escape")){
            remove();
        }
        if(!BASE.buying) {
            aim();
            setLocation(BASE.getX(), BASE.getY());
            move(getOffset());
        }else if(Greenfoot.getMouseInfo() != null){
            setLocation(Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
            move(getOffset());
        }
        if(Greenfoot.mouseClicked(this) ){
            if(BASE.canPlace()){
                if (BASE.buying) {
                    BASE.buying = false;
                    getWorld().getObjects(ShopButton.class).get(0).openTurret();
                }else{
                    getWorld().getObjects(ShopButton.class).get(0).openUpgrade(this, paths);
                }
            }
        }
    }

    private double distanceTo(Point p){
        return Math.sqrt(Math.pow((getX()-p.x),2)+Math.pow((getY()-p.y),2));
    }

    protected void aim(){
        List<Enemy> e = getWorld().getObjects(Enemy.class);
        Enemy target = null;
        for (Enemy t : e) {
            if (distanceTo(t.locate()) <= range) {
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

    public abstract int getOffset();

    public abstract GreenfootImage getBaseImage();

    public abstract GreenfootImage getTurretImage();

    public abstract String getName();
    public int getValue(){
        return (int)(value*0.9);
    }

    public int getCost() {
        return cost;
    }

    protected abstract void animate(boolean cool);

    public TurretBase getBase(){
        return BASE;
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
    protected void setPaths(UpgradePath... upPaths){
        paths = upPaths;
    }
    public void applyUpgrade(Upgrade u){
        alterDamage(u.getDamage());
        alterCooldown(u.getCooldown());
        alterRange(u.getRange());
        alterAnimTime(u.getAnim());
        value += u.getCost();
    }
    public void remove(){
        getWorld().getObjects(ShopButton.class).get(0).close();
        Cash.alterCash((BASE.buying)?getCost():getValue());
        getWorld().removeObject(BASE);
        getWorld().removeObject(this);
    }
}