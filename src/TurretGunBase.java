import greenfoot.*;

import java.awt.*;
import java.util.List;

public class TurretGunBase extends Actor {
    private enum Targeting {
        FIRST(0),
        LAST(1),
        CLOSE(2),
        FAR(3);

        private final int value;
        Targeting(int value){
            this.value = value;
        }
        public int getValue(){
            return value;
        }
    }

    Targeting mode = Targeting.FIRST;

    private double range;
    private int damage;
    private final int cooldown;
    private int lastCheck = -1;

    public TurretGunBase(double r, int d, int cool){
        range = r;
        damage = d;
        cooldown = cool;
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
                        if (index == -1 || t.progress() >= e.get(index).progress()) {
                            index = i;
                        }
                    case LAST:
                        if (index == -1 || t.progress() <= e.get(index).progress()) {
                            index = i;
                        }
                    case FAR:
                        if (index == -1 || distanceTo(t.locate()) >= distanceTo(e.get(index).locate())) {
                            index = i;
                        }
                    case CLOSE:
                        if (index == -1 || distanceTo(t.locate()) <= distanceTo(e.get(index).locate())) {
                            index = i;
                        }
                }
            }
        }
        if(index != -1 && MyWorld.Timer - lastCheck <= cooldown){
            e.get(index).damage(damage);
            lastCheck = MyWorld.Timer;
        }
    }

    public void alterDamage(int val){
        damage += val;
    }

    public void alterRange(double val){
        range += val;
    }
}