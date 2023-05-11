import greenfoot.*;

import java.awt.*;
import java.util.List;

public abstract class TurretGunBase extends Actor {
    private enum Targeting {
        FIRST,
        LAST,
        CLOSE,
        FAR
    }

    Targeting mode = Targeting.FIRST;

    private double range;
    private int damage;
    private final int cooldown;
    private int lastFireCheck = -1;
    protected int lastAnimCheck = -1;
    protected final int animLength;
    private final TurretBase BASE;
    private boolean baseMade = false;

    public TurretGunBase(double r, int d, int cool, GreenfootImage baseImage, int anim){
        BASE = new TurretBase(baseImage);
        range = r;
        damage = d;
        cooldown = cool;
        animLength = anim;
    }

    public void act(){
        if(!baseMade){
            getWorld().addObject(BASE, 0, 0);
            baseMade = true;
        }
        if(!BASE.buying){
            aim();
        }
        setLocation(BASE.getX(), BASE.getY());
        move(getOffset());
        if(BASE.buying && Greenfoot.mouseClicked(this) && BASE.canPlace()) {
            BASE.buying = false;
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
                }
            }
        }
        if(index != -1){
            turnTowards(e.get(index).getX(), e.get(index).getY());
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

    public abstract int getOffset();

    public abstract GreenfootImage getBaseImage();

    public abstract GreenfootImage getTurretImage();

    public abstract String getName();

    protected abstract void animate(boolean cool);
}