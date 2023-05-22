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
    private int value = 0;
    private final int cost;

    public TurretGunBase(double r, int d, int cool, int anim, int c){
        range = r;
        damage = d;
        cooldown = cool;
        animLength = anim;
        cost = c;
    }

    public void act(){
        if(!baseMade){
            BASE = new TurretBase(getBaseImage());
            getWorld().addObject(BASE, 0, 0);
            baseMade = true;
            getWorld().addObject(new RangeDisplay(this), 0, 0);
        }
        if(BASE.buying && Greenfoot.isKeyDown("escape")){

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
                    getWorld().getObjects(ShopButton.class).get(0).openUpgrade(this, paths);
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
        int index = -1;
        for (int i = 0; i < e.size(); i++) {
            Enemy t = e.get(i);
            if (distanceTo(t.locate()) <= range) {
                switch (mode) {
                    case FIRST:
                        if (index == -1 || t.progress() >= e.get(index).progress()) index = i;
                        break;
                    case LAST:
                        if (index == -1 || t.progress() <= e.get(index).progress()) index = i;
                        break;
                    case FAR:
                        if (index == -1 || distanceTo(t.locate()) >= distanceTo(e.get(index).locate())) index = i;
                        break;
                    case CLOSE:
                        if (index == -1 || distanceTo(t.locate()) <= distanceTo(e.get(index).locate())) index = i;
                        break;
                    case STRONG:
                        if(index == -1 || t.maxHealth() >= e.get(index).maxHealth()) index = i;
                }
            }
        }
        if(index != -1){
            if(MyWorld.Timer - lastAnimCheck >= animLength){
                turnTowards(e.get(index).getX(), e.get(index).getY());
            }
            if(MyWorld.Timer - lastFireCheck >= cooldown){
                e.get(index).damage(damage);
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
        return (BASE.buying)?cost:value;
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
    }
}